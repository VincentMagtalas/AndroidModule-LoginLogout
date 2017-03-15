/*
 * Copyright (c) 2017.
 *
 * Developed By: Joseph Vincent Lazado Magtalas
 */

package com.joseph.module.loginlogout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Activity_Main extends AppCompatActivity {

    Misc_Utility utility = new Misc_Utility();
    SharedPreferences sharedpreferences;
    TextView tvUsername,tvPassword,tvTime;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(utility.LoginPreferences, Context.MODE_PRIVATE);
        String loginUsername = sharedpreferences.getString(utility.loginUsername, "");
        String loginUPassword = sharedpreferences.getString(utility.loginPassword, "");
        String loginTime = sharedpreferences.getString(utility.loginTime, "");

        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvPassword = (TextView) findViewById(R.id.tvPassword);
        tvTime = (TextView) findViewById(R.id.tvTime);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        tvUsername.setText(loginUsername);
        tvPassword.setText(loginUPassword);
        tvTime.setText(loginTime);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedpreferences = getSharedPreferences(utility.LoginPreferences, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(utility.loginUsername, "");
                editor.putString(utility.loginPassword, "");
                editor.putString(utility.loginTime, "");
                editor.commit();

                Intent intent = new Intent(getApplicationContext(),Activity_Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

    }
}
