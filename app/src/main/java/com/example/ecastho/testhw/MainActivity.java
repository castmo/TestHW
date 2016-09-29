package com.example.ecastho.testhw;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnHitIt;
    private TextView textArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textArea = (TextView)findViewById(R.id.textarea);
        btnHitIt = (Button)findViewById(R.id.button_hitit);
        //btnHitIt.setOnClickListener(this);
    }

    public void onClick(View v) {
        Date d = new Date();

        showSetTimeDialog();

        textArea.setText("hej");
        //Intent intent = new Intent(this, setTime.class);
        //startActivity(intent);
    }

    private Date showSetTimeDialog() {
        FragmentManager fm = getSupportFragmentManager();
        TimeSetter timeSet = new TimeSetter();
        timeSet.show(fm, "fragment_time_setter");

        // test
        return new Date();
    }

}
