package com.example.a_i_fitco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class mainlogin extends AppCompatActivity {

    EditText username,password;
    TextView registerz;
    Button login;
    DBHelper DB;
    private static String username_value;
    public static String getUsername_value(){

        return username_value;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlogin);
        username=findViewById(R.id.l_username);
        password=findViewById(R.id.l_password);
        login=findViewById(R.id.login_button);
        registerz=findViewById(R.id.textView17);
        DB= new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String stat = "0";

                if(TextUtils.isEmpty(user)||TextUtils.isEmpty(pass))
                    Toast.makeText(mainlogin.this,"All fields Required", Toast.LENGTH_SHORT).show();
                else{
                    Boolean chechkuserpass=DB.checkusernameandpassword(user,pass);
                    if(chechkuserpass==true){
                        username_value=user;
                        //check status
                                    Toast.makeText(mainlogin.this,"Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getApplicationContext(),MainMenuActivity.class);
                                    startActivity(intent);


                    }else{
                        Toast.makeText(mainlogin.this,"Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registerz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), mainregister.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}