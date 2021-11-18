package com.example.agroguru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button login;
    AGdatabase mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.ed1);
        password = findViewById(R.id.ed2);
        login = findViewById(R.id.btnlogin);
        mDatabaseHelper = new AGdatabase(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEntry = username.getText().toString();
                String passwordEntry = password.getText().toString();
                if (userEntry.length() != 0 && passwordEntry.length() != 0){
                    valid(userEntry,passwordEntry);
                    username.setText("");
                    password.setText("");
                }else {
                    Toast.makeText(Login.this,"Plz enter the details",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void valid(String userEntry,String passwordEntry){
        boolean insertData = mDatabaseHelper.valid(userEntry,passwordEntry);
        if (insertData == true){
            Toast.makeText(Login.this,"Welcome 👌",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Login.this, Agropanel.class);
            startActivity(i);
        }else {
            Toast.makeText(Login.this,"You have not registered yet !!",Toast.LENGTH_SHORT).show();
        }
    }
    //==Registration
    public void reg(View view) {
        Intent i = new Intent(Login.this, Registration.class);
        startActivity(i);
    }
}