package com.example.ecastho.testhw;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ecastho.testhw.timesetter.TimeSetter;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener, TimeSetter.OnTimeSetterFinishedListener {

    private Button btnHitIt;
    private TextView textArea;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textArea = (TextView) findViewById(R.id.textarea);
        btnHitIt = (Button) findViewById(R.id.button_hitit);
        //btnHitIt.setOnClickListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onClick(View v) {
        Date d = new Date();

        showSetTimeDialog();

        textArea.setText("hej");
        //Intent intent = new Intent(this, setTime.class);
        //startActivity(intent);
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

        // test

    }

    @Override
    public void OnTimeSetterFinished(Date date) {
        textArea.setText(date.toString());
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
