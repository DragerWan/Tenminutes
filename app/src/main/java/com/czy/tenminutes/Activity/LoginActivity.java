package com.czy.tenminutes.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.czy.tenminutes.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText nameEdit;
    private EditText passwordEdit;
    private TextView forgetPassword;
    private TextView register;
    private TextView skip;
    private Button login;
    public SharedPreferences userPref;
    private SharedPreferences.Editor editor;
    private boolean result = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        nameEdit = (EditText) findViewById(R.id.name);

        passwordEdit = (EditText) findViewById(R.id.password);
        userPref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = userPref.edit();
        boolean isRemember = userPref.getBoolean("isRemember",false);
        if(isRemember){
            if(userPref.getBoolean("skip", false)){
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            String name = userPref.getString("name", null);
            String password = userPref.getString("password", null);
            if(attemptLogin(name, password)) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if(attemptLogin(name, password)){
                    Log.e("attemptlogin======", "结果正确");
                    editor.putString("name",name);
                    editor.putString("password",password);
                    editor.putBoolean("isRemember",true);
                    Log.e("Editor", "before intent");
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    editor.clear();
                }
                editor.apply();
            }
        });

        forgetPassword = (TextView) findViewById(R.id.forget);
        register = (TextView) findViewById(R.id.register);
        skip=(TextView) findViewById(R.id.skipLogin);
        skip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putBoolean("skip", true);
                editor.apply();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }



    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private boolean attemptLogin(String name, String password) {
        // Store values at the time of xthe login attempt.
        /*NetUtil.getSSH();
        NetUtil.getConnection();
        if(NetUtil.getPassword(name).equals(password))
            return true;
        return false;*/
        Connect connect = new Connect(name, password);
        connect.start();
        try {
            connect.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("attemptlogin", "=====end");
        return result;

    }
    private class Connect extends Thread {
        String name;
        String password;

        public Connect(String name, String password){
            this.name = name;
            this.password = password;

        }

        @Override
        public void run() {
            NetUtil.getSSH();
            NetUtil.getConnection();
            String truePassword = NetUtil.getPassword(name);
            if(truePassword == null){
                result = false;
                Log.e("登录", "======登录结果=======" + result);
                return;
            }

            if(truePassword.equals(password))
                result = true;
            else
                result = false;
            Log.e("登录", "======登录结果=======" + result);
        }
    }
}

