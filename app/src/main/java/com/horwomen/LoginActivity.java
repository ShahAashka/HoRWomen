package com.horwomen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity{
    public static final String MYPREF = "myRef";
    private EditText uId;
    private EditText pass;
    private TextView text_signin;
    private Button btn_login;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new Session(this);
        if(session.isLoggedIn())
        {
            //Toast.makeText(this, "isLoggedIn Error", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this,NavActivity.class));

        }

        pref = getSharedPreferences(LoginActivity.MYPREF,MODE_PRIVATE);;
        editor  = pref.edit();



        Boolean bool = session.isLoggedIn();

        Toast.makeText(this, bool.toString(), Toast.LENGTH_SHORT).show();


        /*SharedPreferences pref = getApplicationContext().getSharedPreferences("User", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();*/


        uId = (EditText)findViewById(R.id.uId);
        pass = (EditText)findViewById(R.id.pass);
        text_signin = (TextView)findViewById(R.id.text_signin);
        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                    String method = "login";

                    BackgroundTask backgroundTask = new BackgroundTask(getApplicationContext());
                    //    backgroundTask.execute(method,id,password);

                    String s = null;
                    try {
                        s = backgroundTask.execute(method,id,password).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }


                    //         session.LogIn(id,password);

                    finish();

 /*                  Toast.makeText(getApplicationContext(), pref.getString("reply","No Reply"), Toast.LENGTH_SHORT).show();

                    if(pref.getString("result","sry").equals("Exist"))
                    {
                        //startActivity(new Intent(getApplicationContext(), NavActivity.class));

                        Log.i("Login","started");

                        // user is not logged in redirect him to Login Activity
                        Intent i = new Intent(getApplicationContext(), NavActivity.class);
                        // Closing all the Activities
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        // Add new Flag to start new Activity
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        // Staring Login Activity
                        startActivity(i);

                        finish();
                    }
                    else
                       Toast.makeText(getApplicationContext(), "Enter Valid User", Toast.LENGTH_SHORT).show();
*/
                }
            }
        });

        //btn_login.setOnClickListener(this);
    }

/*
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

            String method = "login";

           BackgroundTask backgroundTask = new BackgroundTask(this);
        //    backgroundTask.execute(method,id,password);

            String s = null;
            try {
                s = backgroundTask.execute(method,id,password).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


            //         session.LogIn(id,password);

            finish();

            Toast.makeText(this, pref.getString("reply","No Reply"), Toast.LENGTH_SHORT).show();

            if(pref.getString("result","sry").equals("Exist"))
            {
                startActivity(new Intent(getApplicationContext(), NavActivity.class));
            }
            else
              Toast.makeText(this, "Enter Valid User", Toast.LENGTH_SHORT).show();

        }
    }
*/


    public void onClick(View view) {
        /*if(view == btn_login){
            finish();
            loginUser();

        }*/
       /* if(view == text_signin){
            finish();
            startActivity(new Intent(this, SignUpActivity.class));
        }*/

       startActivity(new Intent(this,SignUpActivity.class));

    }



}
