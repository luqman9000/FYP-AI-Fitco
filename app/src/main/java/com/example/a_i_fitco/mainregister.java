package com.example.a_i_fitco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class mainregister extends AppCompatActivity {
    EditText username,password,email;
    Button signup;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainregister);

        username=findViewById(R.id.usernameinput);
        password=findViewById(R.id.inputpassword);
        email=findViewById(R.id.emailinput);

        signup=findViewById(R.id.sregister);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String em=email.getText().toString();
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String stat = "0";

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(em) )
                    Toast.makeText(mainregister.this,"All fields Required",Toast.LENGTH_LONG).show();
                else{
                        Boolean checkhuser = DB.checkusername(user);
                        if(checkhuser==false){
                            Boolean insert = DB.insertData(user,pass,em,stat);
                            if(insert==true){
                                Intent intent = new Intent(getApplicationContext(), mainlogin.class);
                                startActivity(intent);

                            }
                            else
                            {
                                Toast.makeText(mainregister.this,"Registration failed", Toast.LENGTH_SHORT).show();
                            }


                        }
                        else
                        {
                            Toast.makeText(mainregister.this,"User already exist", Toast.LENGTH_SHORT).show();
                        }

                }



            }
        });
    }
}