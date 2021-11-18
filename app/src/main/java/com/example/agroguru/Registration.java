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

public class Registration extends AppCompatActivity {

    EditText name,mob,adharno,place,pin,pass;
    Button register;
    AGdatabase mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);

        name = (EditText) findViewById(R.id.editText1);
        mob = (EditText) findViewById(R.id.editText2);
        adharno = (EditText) findViewById(R.id.editText3);
        place = (EditText) findViewById(R.id.editText4);
        pin = (EditText) findViewById(R.id.editText5);
        pass = (EditText) findViewById(R.id.editText6);
        register = findViewById(R.id.btnreg);

        mDatabaseHelper = new AGdatabase(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameEntry = name.getText().toString();
                String mobEntry = mob.getText().toString();
                String adharnoEntry = adharno.getText().toString();
                String placeEntry = place.getText().toString();
                String pinEntry = pin.getText().toString();
                String passEntry = pass.getText().toString();

                if (nameEntry.length() != 0 && mobEntry.length() == 10 && adharnoEntry.length() == 12 && placeEntry.length() != 0 && pinEntry.length() == 6 && passEntry.length() != 0){
                    AddData(nameEntry,mobEntry,adharnoEntry,placeEntry,pinEntry,passEntry);
                    name.setText("");
                    mob.setText("");
                    adharno.setText("");
                    place.setText("");
                    pin.setText("");
                    pass.setText("");
                } else {
                    Toast.makeText(Registration.this,"Plz enter valid credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void AddData(String nameEntry,String mobEntry,String adharnoEntry,String placeEntry,String pinEntry,String passEntry){
        boolean insertData = mDatabaseHelper.addData(nameEntry,mobEntry,adharnoEntry,placeEntry,pinEntry,passEntry);
        if (insertData == true){
            Toast.makeText(Registration.this,"Registration successful 👍",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(Registration.this,"Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }
    //==AGROPANEL
    public void backtologin(View view) {
        Intent i = new Intent(Registration.this, Login.class);
        startActivity(i);
    }
}