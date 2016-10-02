package com.example.ecastho.testhw;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ecastho.testhw.timesetter.TimeSetter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener, TimeSetter.OnTimeSetterFinishedListener {

    private Button btnHitIt;
    private TextView textArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        textArea = (TextView) findViewById(R.id.textarea);
        btnHitIt = (Button) findViewById(R.id.button_hitit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_hitit:
                Date d = new Date();
                showSetTimeDialog();
                textArea.setText("hej");
                break;
            default:
                break;
        }
    }

    private void showSetTimeDialog() {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        try {
            date = sdf.parse("2016-08-22T22:11:23");
            date = new Date();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (date != null) {
            FragmentManager fm = getSupportFragmentManager();
            TimeSetter timeSet = TimeSetter.newInstance(date);
            timeSet.SetOnTimeSetterFinishedListener(this);
            timeSet.show(fm, "fragment_time_setter");
        }
    }

    @Override
    public void OnTimeSetterFinished(Date date) {
        textArea.setText(date.toString());
    }

}
