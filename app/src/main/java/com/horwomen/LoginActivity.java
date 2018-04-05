package com.horwomen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText uId;
    private EditText pass;
    private TextView text_signin;
    private Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uId = (EditText)findViewById(R.id.uId);
        pass = (EditText)findViewById(R.id.pass);
        text_signin = (TextView)findViewById(R.id.text_signin);
        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
    }

    public void loginUser(){
        String id = uId.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if(TextUtils.isEmpty(id)){
            uId.setError("Required");
            return;
        }
        else if(TextUtils.isEmpty(password)){
            pass.setError("Required");
            return;
        }
        else{
            finish();
            startActivity(new Intent(this, NavActivity.class));
        }
    }


    @Override
    public void onClick(View view) {
        if(view == btn_login){
            loginUser();

        }
        if(view == text_signin){
            finish();
            startActivity(new Intent(this, SignUpActivity.class));
        }
    }
}
