package com.neizatheedev.deliveryboysbw.Activities;

/**
 * @author Monei Bakang Motshegwe
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.neizatheedev.deliveryboysbw.R;

public class MainActivity extends AppCompatActivity {

    //Declarations of components to be used
    Button button;
    TextView txt;
    FirebaseAuth fireAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.loginLink);
        button = (Button) findViewById(R.id.nextbtn);


        //Check if user has already logged in and redirect user to dashboard.
        fireAuth = FirebaseAuth.getInstance();
        if (fireAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }

        //onClickListner to take user to the Login Activity.
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginActivity();
            }
        });

        //onClickListner to take user to the next onboarding screen.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToOnBoardScreenActivity();
            }
        });

    }

    public void goToOnBoardScreenActivity() {
        Intent intent = new Intent(this, onBoardScreenTwo.class);
        startActivity(intent);
    }

    public void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivtity.class);
        startActivity(intent);
    }
}