package com.horwomen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Session {

    // Shared Preferences
    public static SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context context;

    // Shared pref mode
    int PRIVATE_MODE = 0;


    // Sharedpref file name
    //public static final String PREF_NAME = "myPref";

    // All Shared Preferences Keys
    public static final String IS_LOGIN = "IsLoggedIn";



    public static Boolean bool = false;

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    // Email address (make variable public to access from outside)
    // public static final String KEY_EMAIL = "email";


    // Constructor
    public Session(Context context){
        this.context = context;

        pref = context.getSharedPreferences(LoginActivity.MYPREF, PRIVATE_MODE);


        // editor = pref.edit();

    }



    public void createLoginSession(String name){
        // Storing login value as TRUE
        pref.edit().putBoolean(IS_LOGIN, true).apply();

        bool = true;

        // Storing name in pref
        pref.edit().putString(KEY_NAME, name).apply();

        // Storing email in pref
        //editor.putString(KEY_EMAIL, email);
        //   editor.apply();
        // commit changes
        pref.edit().commit();

        Log.i("Session","Started");
        Intent i = new Intent(context,NavActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        context.startActivity(i);

    }

    /*public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user email id
       // user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // return user
        return user;
    }*/


    public void checkLogin(){


        // Check login status
        if(!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(context, NavActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            context.startActivity(i);

        }

        //return pref.getBoolean(IS_LOGIN,false);

    }


    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        pref.edit().clear().apply();
        pref.edit().commit();

        bool = false;


        // After logout redirect user to Loing Activity
        Intent i = new Intent(context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Toast.makeText(context, IS_LOGIN, Toast.LENGTH_SHORT).show();
        // Staring Login Activity
        context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn()
    {
        //return bool;

        return pref.getBoolean(IS_LOGIN, false);
    }




}
