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

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="com.funning";
    private EditText usernameInput;
    private EditText passwordInput;
    private Firebase funningRef;
    private Firebase userRef;
    private Firebase newUserRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);


        funningRef = new Firebase("https://funning.firebaseio.com/");

        userRef = funningRef.child("users");
        newUserRef = userRef.push();

        usernameInput = (EditText)findViewById(R.id.usernameInput);
        passwordInput = (EditText)findViewById(R.id.userPasswordInput);

        funningRef.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    // user is logged in
                    Log.i(TAG," user already login");
                    skipUserLogin();
                } else {
                    // user is not logged in
                    // continue sign in
                    Log.i(TAG," user need to login first");
                }
            }
        });
    }

    public void createNewUser(View view)
    {
        final String email = usernameInput.getText().toString();
        final String password = passwordInput.getText().toString();

        funningRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
                Log.i(TAG+"user created",result.get("uid").toString());
                userLoginAfterRegister(email,password);
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
                Log.i(TAG,firebaseError.toString());
            }
        });

        Intent i = new Intent(this,Running.class);
        i.putExtra("username",email);

        Map<String, Object> user = new HashMap<String, Object>();
        user.put("username", email);

        userRef.setValue(user);

        startActivity(i);
    }

    public void userLoginAfterRegister(String email,String password)
    {
        funningRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                Log.i(TAG+"authData",authData.getUid().toString());
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // there was an error
                Log.i(TAG,"user login error");
            }
        });
    }

    public void userLogin(View view)
    {
        final String email = usernameInput.getText().toString();
        final String password = passwordInput.getText().toString();

        funningRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                Log.i(TAG+"authData",authData.getUid().toString());
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // there was an error
                Log.i(TAG,"user login error");
            }
        });
    }

    public void skipUserLogin()
    {
        Intent i = new Intent(this,Running.class);
        startActivity(i);
    }

    public class User {
        private int age;
        private int height;
        private int weight;
        private String fullName;

        public User() {}

        public User(String fullName, int age,int height ,int weight) {

            this.fullName = fullName;
            this.age = age;
            this.height = height;
            this.weight = weight;
        }
        public long getAge() {
            return age;
        }
        public String getFullName() {
            return fullName;
        }
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
