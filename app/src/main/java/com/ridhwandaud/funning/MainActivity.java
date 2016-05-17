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

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="com.ridhwandaud.funning";
    private EditText usernameInput;
    private Firebase userRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        Firebase funningRef = new Firebase("https://funning.firebaseio.com/");
        funningRef.child("message").setValue("Do you have data? You'll love Firebase.");

        userRef = funningRef.child("users");

        User alan = new User("Alan Taring", 1912);
        userRef.setValue(alan);

        usernameInput = (EditText)findViewById(R.id.usernameInput);


        funningRef.child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
                Log.i(TAG,snapshot.getValue().toString());
            }
            @Override public void onCancelled(FirebaseError error) { }
        });
    }

    public void userLogin(View view)
    {
        String username = usernameInput.getText().toString();

        Intent i = new Intent(this,Running.class);
        i.putExtra("username",username);

        Map<String, Object> nickname = new HashMap<String, Object>();
        nickname.put("fullName", username);

        userRef.updateChildren(nickname);

        startActivity(i);
    }

    public class User {
        private int birthYear;
        private String fullName;
        public User() {}
        public User(String fullName, int birthYear) {
            this.fullName = fullName;
            this.birthYear = birthYear;
        }
        public long getBirthYear() {
            return birthYear;
        }
        public String getFullName() {
            return fullName;
        }
    }
}
