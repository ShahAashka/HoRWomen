package com.horwomen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_rId;
    private EditText edit_uId;
    private EditText edit_pass;
    private EditText edit_rePass;
    private Button btn_submit;
    private TextView text_log;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edit_rId = (EditText)findViewById(R.id.edit_rId);
        edit_uId = (EditText)findViewById(R.id.edit_uId);
        edit_pass = (EditText)findViewById(R.id.edit_pass);
        edit_rePass = (EditText)findViewById(R.id.edit_rePass);
        btn_submit = (Button)findViewById(R.id.btn_submit);
        text_log = (TextView)findViewById(R.id.text_log);

        btn_submit.setOnClickListener(this);
    }

    public void registerUser(){
        String rid = edit_rId.getText().toString().trim();
        String uid = edit_uId.getText().toString().trim();
        String pass = edit_pass.getText().toString().trim();
        String rePass = edit_rePass.getText().toString().trim();
        String numRegex   = ".*[0-9].*";
        String alphaRegex = ".*[a-zA-Z].*";
        String charRegex = ".*[@#$&*!_].*";

        if(TextUtils.isEmpty(rid)){
            edit_rId.setError("Required");
            return;
        }
        else if(TextUtils.isEmpty(uid)){
            edit_uId.setError("Required");
            return;
        }
        else if(TextUtils.isEmpty(pass)){
            edit_pass.setError("Required");
            return;
        }
        else if(TextUtils.isEmpty(rePass)){
            edit_rePass.setError("Required");
            return;
        }
        else if(pass.length()<6){
            edit_pass.setError("Minimum password length must be 6");
        }
        else if(!(pass.matches(numRegex))){
            edit_pass.setError("Atleast one numeric character required");

        }
        else if(!(pass.matches(alphaRegex))){
            edit_pass.setError("Atleast one alphabet required");
        }
        else if(!(pass.matches(charRegex))){
            edit_pass.setError("Atleast one special character required");
        }
        else {


            String method = "register";
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method,rid,uid,pass);
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        if(!(pass.equals(rePass))){
            edit_pass.setText("");
            edit_rePass.setText("");
            edit_rePass.setError("Passwords do not match");
        }


    }

    @Override
    public void onClick(View view) {
        if(view == btn_submit){
            finish();
            registerUser();

        }

        if(view == text_log){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
