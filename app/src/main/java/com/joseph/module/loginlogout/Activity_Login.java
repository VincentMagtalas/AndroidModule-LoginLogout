/*
 * Copyright (c) 2017.
 *
 * Developed By: Joseph Vincent Lazado Magtalas
 */

package com.joseph.module.loginlogout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Activity_Login extends AppCompatActivity {

    Misc_Utility utility = new Misc_Utility();
    EditText etUsername,etPassword;
    Button btnLogin;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedpreferences = getSharedPreferences(utility.LoginPreferences, Context.MODE_PRIVATE);
        String loginUsername = sharedpreferences.getString(utility.loginUsername, "");
        String loginPassword = sharedpreferences.getString(utility.loginPassword, "");
        String loginTime = sharedpreferences.getString(utility.loginTime, "");

        if(loginUsername.equals("")||loginPassword.equals("")||loginTime.equals("")){
            etUsername = (EditText) findViewById(R.id.etUsername);
            etPassword = (EditText) findViewById(R.id.etPassword);
            btnLogin = (Button) findViewById(R.id.btnLogin);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //here lies your validation
                    if(etUsername.getText().toString().equals("")){
                        alertCancel("Login","Please Enter Username");
                    }else if(etPassword.getText().toString().equals("")){
                        alertCancel("Login","Please Enter Password");
                    }else{
                        Calendar c = Calendar.getInstance();
                        System.out.println("Current time => "+c.getTime());

                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String formattedDate = df.format(c.getTime());


                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString(utility.loginUsername, etUsername.getText().toString());
                        editor.putString(utility.loginPassword, etPassword.getText().toString());
                        editor.putString(utility.loginTime, formattedDate);
                        editor.commit();

                        Intent intent = new Intent(getApplicationContext(),Activity_Main.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }else{
            Intent intent = new Intent(getApplicationContext(),Activity_Main.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }




    }

    //UI Alert Function
    private void alertCancel(String title,String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

}
