package com.example.emedicine;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Locale;

public class Symptoms extends AppCompatActivity {
    TextView symptoms, medicine;
    TextView disname;
    String name;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);
        symptoms = (TextView) findViewById(R.id.symptoms);
        medicine = (TextView) findViewById(R.id.medicine);
        disname = (TextView) findViewById(R.id.disText);

        Bundle bundle = getIntent().getBundleExtra("data");
        name = bundle.getString("disease");
        disname.setText(name);

        DocumentReference dRef = db.collection("Account").document(name);
        dRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.exists()) {
                    symptoms.setText(value.getString("Symptom"));
                    medicine.setText(value.getString("Medicine"));
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Disease Not Found", Toast.LENGTH_LONG).show();
                    disname.setText("No Disease found");
                    symptoms.setText("Not available");
                    medicine.setText("Not available");
                }
            }
        });
    }
}