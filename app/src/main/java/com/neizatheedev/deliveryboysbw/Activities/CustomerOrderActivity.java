package com.neizatheedev.deliveryboysbw.Activities;

// No Longer used
// Customer writing to database
// Table name: Deliveries


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import com.neizatheedev.deliveryboysbw.R;

import java.util.HashMap;
import java.util.Map;

public class CustomerOrderActivity extends AppCompatActivity {

    EditText textEmail1, textNumber1, textFirstName1, textLastName1, textProduct1, textFrom1, textTo1, textDateTime1, textShopName1, textPayMethod1, textAmount1;
    Button btnFindGuy;
    public static final String TAG = "TAG";
    FirebaseAuth fireAuth;
    FirebaseFirestore fireStore;
    String userId;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);


        textEmail1 = (EditText) findViewById(R.id.textEmail1);
        textNumber1 = (EditText) findViewById(R.id.textNumber1);
        textFirstName1 = (EditText) findViewById(R.id.textFirstName1);
        textLastName1 = (EditText) findViewById(R.id.textLastName1);
        textProduct1 = (EditText) findViewById(R.id.textProduct1);
        textFrom1 = (EditText) findViewById(R.id.textFrom1);
        textTo1 = (EditText) findViewById(R.id.textTo1);
        textDateTime1 = (EditText) findViewById(R.id.textDateTime1);
        textShopName1 = (EditText) findViewById(R.id.textShopName1);
        textPayMethod1 = (EditText) findViewById(R.id.textPayMethod1);
        textAmount1 = (EditText) findViewById(R.id.textAmount1);

        btnFindGuy = (Button) findViewById(R.id.btnFindGuy);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("deliveries");

        btnFindGuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // username = usernameTxt.getText().toString().trim();
                String email = textEmail1.getText().toString().trim();
                String phone = textNumber1.getText().toString().trim();
                String firstName = textFirstName1.getText().toString().trim();
                String lastName = textLastName1.getText().toString().trim();
                String Product = textProduct1.getText().toString().trim();
                String from = textFrom1.getText().toString().trim();
                String to = textTo1.getText().toString().trim();
                String time = textDateTime1.getText().toString().trim();
                String shop = textShopName1.getText().toString().trim();
                String pay = textPayMethod1.getText().toString().trim();
                String amount = textAmount1.getText().toString().trim();

                // Validation
                if (email.isEmpty()) {
                    Toast.makeText(CustomerOrderActivity.this, "Please write your email", Toast.LENGTH_SHORT).show();
                }
                if (phone.isEmpty()) {
                    Toast.makeText(CustomerOrderActivity.this, "Please write your Phone Number", Toast.LENGTH_SHORT).show();
                }
                if (firstName.isEmpty() || lastName.isEmpty()) {
                    Toast.makeText(CustomerOrderActivity.this, "Please write your names", Toast.LENGTH_SHORT).show();
                }
                if (Product.isEmpty()) {
                    Toast.makeText(CustomerOrderActivity.this, "Please write your parcel", Toast.LENGTH_SHORT).show();
                }
                if (from.isEmpty() || to.isEmpty()) {
                    Toast.makeText(CustomerOrderActivity.this, "Please write your locations", Toast.LENGTH_SHORT).show();
                }
                if (time.isEmpty()) {
                    Toast.makeText(CustomerOrderActivity.this, "Please write your time of pickup!", Toast.LENGTH_SHORT).show();
                }
                if (shop.isEmpty()) {
                    Toast.makeText(CustomerOrderActivity.this, "Please write shop name", Toast.LENGTH_SHORT).show();
                }
                if (pay.isEmpty()) {
                    Toast.makeText(CustomerOrderActivity.this, "Please enter payment method", Toast.LENGTH_SHORT).show();
                }
                if (amount.isEmpty()) {
                    Toast.makeText(CustomerOrderActivity.this, "Please enter total amount", Toast.LENGTH_SHORT).show();
                }else {
                    writeDataToFirebase(email, phone, firstName, lastName, Product, to, from, time, shop, pay, amount);
                }
            }
        });
    }

    public void writeDataToFirebase(String email, String phone, String firstName, String lastName, String product, String to, String from, String time, String shop, String pay, String amount) {
        Map<String, Object> delivery = new HashMap<>();
        delivery.put("email", email);
        delivery.put("phone", phone);
        delivery.put("firstName", firstName);
        delivery.put("lastName", lastName);
        delivery.put("product", product);
        delivery.put("to", to);
        delivery.put("from", from);
        delivery.put("time", time);
        delivery.put("shop", shop);
        delivery.put("pay", pay);
        delivery.put("amount", amount);

        DatabaseReference deliveriesRef = FirebaseDatabase.getInstance().getReference("deliveries");
        deliveriesRef.push().setValue(delivery).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(CustomerOrderActivity.this, "Order Successfully made, wait for response for a few minutes.", Toast.LENGTH_SHORT).show();
                Intent x = new Intent(CustomerOrderActivity.this, HomeActivity.class);
                startActivity(x);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CustomerOrderActivity.this, "Error making delivery: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}