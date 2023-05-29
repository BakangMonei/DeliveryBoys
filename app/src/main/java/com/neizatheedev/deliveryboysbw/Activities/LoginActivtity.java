package com.neizatheedev.deliveryboysbw.Activities;

/**
 * @author Monei Bakang Motshegwe
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.neizatheedev.deliveryboysbw.Administrators.AdministratorActivity;
import com.neizatheedev.deliveryboysbw.MenuActivities.Customer;
import com.neizatheedev.deliveryboysbw.R;

import android.Manifest;

public class LoginActivtity extends AppCompatActivity {

    //Variable Declarations
    EditText emailTxt, passwordTxt;
    String email, password;
    FirebaseAuth fireAuth;
    ProgressBar progressBar;
    TextView txt, forgotPassword;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activtity);
        checkLocationPermission();

        //User Login Components or fields

        emailTxt = (EditText) findViewById(R.id.loguseremail);
        passwordTxt = (EditText) findViewById(R.id.loguserpassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        txt = (TextView) findViewById(R.id.toregisterlink);
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        loginbtn = (Button) findViewById(R.id.loginbtn);


        //Instantiate Database and authentication Services
        fireAuth = FirebaseAuth.getInstance();


        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(LoginActivtity.this, ForgotActivity.class);
                startActivity(x);
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fetch input data from Edit text views
                email = emailTxt.getText().toString().trim();
                password = passwordTxt.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // To be changed
                if (emailTxt.getText().toString().equals("Admin@gmail.com") && passwordTxt.getText().toString().equals("admin1234")){
                    Toast.makeText(LoginActivtity.this, "LOGIN Successful!!!!", Toast.LENGTH_SHORT).show();
                    Intent intentLogIn = new Intent(LoginActivtity.this, CustomerUserActivity.class);
                    startActivity(intentLogIn);
                }
                if (emailTxt.getText().toString().equals("user@gmail.com") && passwordTxt.getText().toString().equals("user1234")){
                    Toast.makeText(LoginActivtity.this, "LOGIN Successful!!!!", Toast.LENGTH_SHORT).show();
                    Intent intentLogIn = new Intent(LoginActivtity.this, Customer.class);
                    startActivity(intentLogIn);
                }

                progressBar.setVisibility(View.VISIBLE);
                fireAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivtity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.INVISIBLE);
                            FirebaseUser user = fireAuth.getCurrentUser();
                            finish();
                            goToDashboard();

                        } else if (task.isSuccessful()) {
                            progressBar.setVisibility(View.INVISIBLE);
                            FirebaseUser admin = fireAuth.getCurrentUser();
                            finish();
                            goToAdmin();
                        } else {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(LoginActivtity.this, "Sign in failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterActivity();
            }
        });
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {


                new AlertDialog.Builder(this)
                        .setTitle("Permission Request")
                        .setMessage("This App requires your location to work. Activate Your Location please.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                ActivityCompat.requestPermissions(LoginActivtity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                    }

                } else {
                    Toast.makeText(this, "Permission", Toast.LENGTH_SHORT).show();

                }
                return;
            }
        }
    }

    public void goToAdmin(){
        Intent intent = new Intent(this, AdministratorActivity.class);
        startActivity(intent);
    }
    public void goToRegisterActivity() {
        Intent intent = new Intent(this, RegisterUserActivity.class);
        startActivity(intent);
    }

    public void goToDashboard() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}