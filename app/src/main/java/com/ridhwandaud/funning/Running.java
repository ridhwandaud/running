package com.ridhwandaud.funning;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Running extends AppCompatActivity {

    private static final String TAG ="com.funning";

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    private Button startButton;
    private Button stopButton;
    private TextView runningTextView;
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    private Firebase userRef;
    private Firebase funningRef;

    private Runnable updateTimerThread = new Runnable() {

        public void run()
        {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;

            int milliseconds = (int) (updatedTime % 1000);

            runningTextView.setText("" + mins + ":"

                            + String.format("%02d", secs)
            );

            customHandler.postDelayed(this, 0);

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        funningRef = new Firebase("https://funning.firebaseio.com/");
        userRef = funningRef.child("users").child("username");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                TextView welcomeText = (TextView) findViewById(R.id.welcomeText);
                welcomeText.setText("Welcome " + snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });


        runningTextView = (TextView) findViewById(R.id.runningTextView);

        startButton = (Button) findViewById(R.id.startRunningBtn);
        stopButton = (Button) findViewById(R.id.stopRunningBtn);
        stopButton.setVisibility(View.GONE);

        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startButton.setVisibility(View.GONE);
                stopButton.setVisibility(View.VISIBLE);
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);
            }
        });

        stopButton = (Button) findViewById(R.id.stopRunningBtn);
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);
            }
        });

    }

    public void userLogOut(View view)
    {
        funningRef.unauth();

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void onBackPressed() {
        Log.i(TAG, "onBackPressed Called");
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }
}