package com.example.ecastho.testhw;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;


public class TimeSetter extends DialogFragment {

    private EditText mEditText;
    private TimePicker mTimePicker;

    public TimeSetter() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_setter, container);
        mTimePicker = (TimePicker)view.findViewById(R.id.timePicker);
        view.findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked("cancel");
            }
        });
        view.findViewById(R.id.buttonNow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked("Now");
            }
        });
        view.findViewById(R.id.buttonOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked("Ok");
            }
        });
        //btn.setOnClickListener((View.OnClickListener) this);

        //mEditText = (EditText) view.findViewById(R.id.txt_your_name);
        getDialog().setTitle("Hello");

        return view;
    }

    private void buttonClicked(String s) {
        if(s=="") {
            return;
        }
    }

}