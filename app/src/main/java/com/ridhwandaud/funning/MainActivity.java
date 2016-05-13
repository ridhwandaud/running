package com.ridhwandaud.funning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.graphics.Color;
import android.widget.EditText;
import android.content.res.Resources;
import android.util.TypedValue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        //Layout
//        RelativeLayout mainLayout = new RelativeLayout(this);
//        mainLayout.setBackgroundColor(Color.GREEN);
//
//        //Button
//        Button signInButton = new Button(this);
//        signInButton.setText("Log In");
//        signInButton.setBackgroundColor(Color.RED);
//
//
//
//        //Username Input
//        EditText username = new EditText(this);
//        username.setHint("Enter Username");
//
//        signInButton.setId(1);
//        username.setId(2);
//
//        RelativeLayout.LayoutParams buttonDetails = new RelativeLayout.LayoutParams(
//
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT
//        );
//
//        RelativeLayout.LayoutParams usernameDetails = new RelativeLayout.LayoutParams(
//
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT
//        );
//
//        //Give rules to position widgets
//        usernameDetails.addRule(RelativeLayout.ABOVE,signInButton.getId());
//        usernameDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
//        usernameDetails.setMargins(0,0,0,50);
//
//        buttonDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
//        buttonDetails.addRule(RelativeLayout.CENTER_VERTICAL);
//
//        Resources r = getResources();
//        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,r.getDisplayMetrics());
//        username.setWidth(px);
//
//        //Add widget to layout(button is now a child of layout)
//        mainLayout.addView(signInButton,buttonDetails);
//        mainLayout.addView(username,usernameDetails);

        //Set this activities content/display to this view
       // setContentView(mainLayout);

        setContentView(R.layout.activity_main);
    }
}
