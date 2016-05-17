package com.ridhwandaud.funning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void userLogin(View view)
    {
        EditText usernameInput = (EditText)findViewById(R.id.usernameInput);
        String username = usernameInput.getText().toString();

        Intent i = new Intent(this,Running.class);
        i.putExtra("username",username);
        startActivity(i);
    }
}
