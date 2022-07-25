package com.example.a_i_fitco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class mainlogin extends AppCompatActivity {

    EditText username,password;
    Button login,register;
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
        register=findViewById(R.id.register_button);
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
                        Cursor cursor = DB.checkStatus(user);
                        if (cursor.getCount() == 0) {
                            Toast.makeText(mainlogin.this, "No data generate", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (cursor.moveToFirst()) {
                            do {
                                String status = cursor.getString(0);
                                if(status.equals("0")){
                                    Toast.makeText(mainlogin.this,"Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getApplicationContext(),BMIActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(mainlogin.this,status, Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getApplicationContext(),mainregister.class);
                                    startActivity(intent);
                                }

                            } while (cursor.moveToNext());
                        }
                    }else{
                        Toast.makeText(mainlogin.this,"Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), mainregister.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}