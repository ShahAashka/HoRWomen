package com.horwomen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txt_feed;
    private EditText edit_FeedDesc;
    private Button btn_feed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        edit_FeedDesc = (EditText)findViewById(R.id.edit_FeedDesc);
        btn_feed = (Button)findViewById(R.id.btn_feed);

        btn_feed.setOnClickListener(this);
    }

    public void giveFeedback(){
        String feed = edit_FeedDesc.getText().toString().trim();

        if(TextUtils.isEmpty(feed)){
            edit_FeedDesc.setError("Required");
            return;
        }
        else{
            finish();
            startActivity(new Intent(this, NavActivity.class));
        }
    }

    @Override
    public void onClick(View view) {
        if(view == btn_feed){
            giveFeedback();
        }
    }
}
