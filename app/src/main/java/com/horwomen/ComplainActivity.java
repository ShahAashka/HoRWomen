package com.horwomen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ComplainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    private Spinner spinner_complaint;
    private Button btn_sub;
    private EditText edit_compDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);

        btn_sub = (Button)findViewById(R.id.btn_sub);
        edit_compDesc = (EditText)findViewById(R.id.edit_compDesc);
        spinner_complaint = (Spinner)findViewById(R.id.spinner_complaint);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.complaint_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_complaint.setAdapter(adapter);
        spinner_complaint.setOnItemSelectedListener(this);
        btn_sub.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void registerComplaint(){
        String comp = edit_compDesc.getText().toString().trim();

        if(TextUtils.isEmpty(comp)){
            edit_compDesc.setError("Required");
            return;
        }
        else{
            finish();
            startActivity(new Intent(this, NavActivity.class));
        }
    }

    @Override
    public void onClick(View view) {
        if(view == btn_sub){
            registerComplaint();
        }
    }
}
