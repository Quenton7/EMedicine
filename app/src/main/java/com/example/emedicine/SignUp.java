package com.example.emedicine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaCodec;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    EditText name,contact,username,pwd;
    Button signup;
    String regex = "(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!])[A-Za-z\\d@$!]{8,}$";
    FirebaseDatabase database;
    DatabaseReference dref = database.getInstance("https://meditech-b320a-default-rtdb.firebaseio.com").getReference().child("account1");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = (EditText) findViewById(R.id.sign_name);
        contact = (EditText) findViewById(R.id.sign_contact);
        username = (EditText) findViewById(R.id.sign_user);
        pwd = (EditText) findViewById(R.id.sign_pass);
        signup = (Button) findViewById(R.id.SignIn);
        signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.equals(signup))
        {
            String name1 = name.getText().toString();
            String con = contact.getText().toString();
            String user = username.getText().toString();
            String pass = pwd.getText().toString();

            DBUsers users = new DBUsers(user,pass,name1,con);
            dref.child(user).setValue(users);
            Intent loginit = new Intent(this,MainActivity.class);
            startActivity(loginit);
            finish();

        }
    }

    private boolean Validate(String pwd) {
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(pwd);
        return match.matches();
    }
}