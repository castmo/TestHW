package com.example.ecastho.testhw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnHitIt;
    private TextView textArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textArea = (TextView)findViewById(R.id.textarea);
        btnHitIt = (Button)findViewById(R.id.button_hitit);
        btnHitIt.setOnClickListener(this);
    }

    public void onClick(View v) {
        textArea.setText("hej");
    }

}
