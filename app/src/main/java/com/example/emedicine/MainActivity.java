package com.example.emedicine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username,password;
    Button login,signup;
    String user,pwd;
    String name;
    FirebaseDatabase database;
    DatabaseReference dref = database.getInstance("https://meditech-b320a-default-rtdb.firebaseio.com").getReference().child("account1");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.login_name);
        password = (EditText) findViewById(R.id.login_pass);
        login = (Button) findViewById(R.id.btnLogin);
        login.setOnClickListener(this);
        signup = (Button) findViewById(R.id.btnSignup);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(signup))
        {
            Intent signit = new Intent(this,SignUp.class);
            startActivity(signit);
        }
        if(view.equals(login))
        {
            user = username.getText().toString();
            pwd = password.getText().toString();

            isUser();
        }
    }

    private void isUser() {
        Query checkUser = dref.orderByChild("username").equalTo(user);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    String passdB = dataSnapshot.child(user).child("pass").getValue(String.class);
                    if(passdB.equals(pwd))
                    {
                        name = dataSnapshot.child(user).child("name").getValue(String.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Welcome","Welcome "+ name);
                        Intent it = new Intent(getApplicationContext(),DiseaseSearch.class);
                        it.putExtra("data",bundle);
                        startActivity(it);
                    }
                    else{
                        Toast.makeText(getBaseContext(),"Incorrect Password",Toast.LENGTH_LONG).show();
                    }

                }
                else {
                    Toast.makeText(getBaseContext(),"No User found",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}