package com.horwomen;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ComplainActivity extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    private Spinner spinner_complaint;
    private Button btn_sub;
    private EditText edit_compDesc;
    String text;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_complain,container,false);

        getActivity().setTitle("Complain");
        btn_sub = (Button)view.findViewById(R.id.btn_sub);
        edit_compDesc = (EditText)view.findViewById(R.id.edit_compDesc);
        spinner_complaint = (Spinner)view.findViewById(R.id.spinner_complaint);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(), R.array.complaint_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_complaint.setAdapter(adapter);

        spinner_complaint.setOnItemSelectedListener(this);
        btn_sub.setOnClickListener(this);


        return view;
    }




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        text = adapterView.getItemAtPosition(i).toString();

       Toast.makeText(getActivity().getBaseContext(), text, Toast.LENGTH_SHORT).show();

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
            Toast.makeText(getActivity().getBaseContext(), "Complain Registered", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        if(view == btn_sub){
            registerComplaint();
        }
    }
}
