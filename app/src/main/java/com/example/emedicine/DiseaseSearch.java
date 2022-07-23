package com.example.emedicine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class DiseaseSearch extends AppCompatActivity implements View.OnClickListener {
    EditText disease;
    Button search;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_search);
        welcome = (TextView) findViewById(R.id.WelcText);
        disease = (EditText) findViewById(R.id.disease);
        search = (Button) findViewById(R.id.Search);
        search.setOnClickListener(this);

        Bundle bundle = getIntent().getBundleExtra("data");
        String wel = bundle.getString("Welcome");
        welcome.setText(wel);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(search))
        {
            String dis = disease.getText().toString();
            Bundle bundle = new Bundle();
            bundle.putString("disease",dis);
            Intent symit = new Intent(this,Symptoms.class);
            symit.putExtra("data",bundle);
            startActivity(symit);

        }

    }
}