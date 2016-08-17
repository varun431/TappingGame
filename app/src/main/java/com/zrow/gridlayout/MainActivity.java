package com.zrow.gridlayout;

import android.graphics.Color;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    int changes;
    int count = 14;
    int gameTimer = count;
    boolean dupClick = false;
    int p1Score = 0;
    int p2Score = 0;

    //Referencing buttons and textviews
    //Button timerButton = (Button) findViewById(R.id.countdown);


    @Override
    protected void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        setContentView(R.layout.activity_main);

        Button p1 = (Button) findViewById(R.id.p1);
        Button p2 = (Button) findViewById(R.id.p2);
        final TextView timerText = (TextView) findViewById(R.id.timer);

        //add listener to the buttons
        p1.setOnClickListener(this);
        p2.setOnClickListener(this);

        final CountDownTimer timer = new CountDownTimer(count * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                gameTimer = (int) (millisUntilFinished / 1000);
                if (gameTimer <= 10) {
                    if (gameTimer <= 3)
                        timerText.setTextColor(Color.RED);
                    timerText.setText(String.valueOf(millisUntilFinished / 1000) + "s...");
                } else {
                    timerText.setText("Starts in " + String.valueOf((millisUntilFinished / 1000) - 10) + "s...");
                }
            }

            @Override
            public void onFinish() {
                timerText.setText("Time Up!");
                gameTimer = 0;
            }
        }.start();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.zrow.gridlayout/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onClick(View v) {
        //initialization
        TextView timerText = (TextView) findViewById(R.id.timer);
        Button p1 = (Button) findViewById(R.id.p1);
        Button p2 = (Button) findViewById(R.id.p2);

        // switch() to identify which view is clicked then perform actions accordingly
        switch (v.getId()) {
            case R.id.p1:
                if (gameTimer <= 10 && gameTimer > 0) {
                    p1.setText(String.valueOf(++p1Score));
                }
                break;
            case R.id.p2:
                if (gameTimer <= 10 && gameTimer > 0) {
                    p2.setText(String.valueOf(++p2Score));
                }
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.zrow.gridlayout/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
