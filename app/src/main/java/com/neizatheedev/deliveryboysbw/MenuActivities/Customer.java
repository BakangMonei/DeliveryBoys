package com.neizatheedev.deliveryboysbw.MenuActivities;
// We are using this one
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.neizatheedev.deliveryboysbw.Activities.HomeActivity;
import com.neizatheedev.deliveryboysbw.R;

import java.util.HashMap;
import java.util.Map;

public class Customer extends AppCompatActivity {

    private Spinner spinner, spinner2Shop, spinner3Pay;
    private EditText textEmail2, textNumber2, textProduct2, textTo2;
    private Button btnCheckOut;
    private DatabaseReference databaseReference;
    public static final String TAG = "TAG";
    FirebaseAuth fireAuth;
    FirebaseFirestore fireStore;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("new_deliveries");
        textEmail2 = (EditText) findViewById(R.id.textEmail2);
        textNumber2 = (EditText) findViewById(R.id.textNumber2);
        textProduct2 = (EditText) findViewById(R.id.textProduct2);
        textTo2 = (EditText) findViewById(R.id.textTo2);
        btnCheckOut = (Button) findViewById(R.id.btnCheckOut);
        /*****************************************************************************************/
        spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dropdown_options, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        // Set item selected listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedOption = adapterView.getItemAtPosition(position).toString();
                // Write selected option to Firebase database
                // databaseReference.setValue(selectedOption);
                Toast.makeText(Customer.this, "Selected: " + selectedOption, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });
        /*****************************************************************************************/
        spinner2Shop = (Spinner) findViewById(R.id.spinner2Shop);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.shopname_options, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner2Shop.setAdapter(adapter1);
        // Set item selected listener
        spinner2Shop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedOption = adapterView.getItemAtPosition(position).toString();
                // Write selected option to Firebase database
                // databaseReference.setValue(selectedOption);
                Toast.makeText(Customer.this, "Selected: " + selectedOption, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });
        /*****************************************************************************************/
        spinner3Pay = (Spinner) findViewById(R.id.spinner3Pay);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.payment_options, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner3Pay.setAdapter(adapter3);
        // Set item selected listener
        spinner3Pay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedOption = adapterView.getItemAtPosition(position).toString();
                // Write selected option to Firebase database
                // databaseReference.setValue(selectedOption);
                Toast.makeText(Customer.this, "Selected: " + selectedOption, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });
        /*****************************************************************************************/



        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = textEmail2.getText().toString().trim(); // email
                String phone = textNumber2.getText().toString().trim(); // phone
                String Product = textProduct2.getText().toString().trim(); // Grocery List
                String to = textTo2.getText().toString().trim(); // Destination
                String from = spinner.getSelectedItem().toString().trim(); // Mall
                String shop = spinner2Shop.getSelectedItem().toString().trim(); // shop in mall
                String pay = spinner3Pay.getSelectedItem().toString().trim(); // Payment method


                // Validation
                if (email.isEmpty()) {
                    Toast.makeText(Customer.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                }
                if (phone.isEmpty()) {
                    Toast.makeText(Customer.this, "Please enter your number", Toast.LENGTH_SHORT).show();
                }
                if (Product.isEmpty()) {
                    Toast.makeText(Customer.this, "Please enter your grocery list here", Toast.LENGTH_SHORT).show();
                }
                if (to.isEmpty()) {
                    Toast.makeText(Customer.this, "Please enter your location", Toast.LENGTH_SHORT).show();
                }
                if (from.isEmpty()) {
                    Toast.makeText(Customer.this, "Please choose the mall of your choice", Toast.LENGTH_SHORT).show();
                }
                if (shop.isEmpty()) {
                    Toast.makeText(Customer.this, "Please choose the shop name", Toast.LENGTH_SHORT).show();
                }
                if (pay.isEmpty()) {
                    Toast.makeText(Customer.this, "Please select payment method", Toast.LENGTH_SHORT).show();
                }
                else {
                    writeDataToFirebase(email, phone, Product, to, shop, pay, from);
                    // Pass the email value to CartActivity using an intent
                    // Intent intent = new Intent(Customer.this, CartActivity.class);
                    // intent.putExtra("email", email);
                    // startActivity(intent);
                }
            }
        });
    }
    public void writeDataToFirebase(String email, String phone, String Product, String to, String shop, String pay, String from){

        Map<String, Object> delivery = new HashMap<>();
        delivery.put("email", email); // email
        delivery.put("phone", phone); // phoneNumber
        delivery.put("product", Product); // Grocery List
        delivery.put("to", to); // Destination
        delivery.put("from", from); // Mall
        delivery.put("shop", shop); // shop in mall
        delivery.put("pay", pay); // payment method

        DatabaseReference deliveriesRef = FirebaseDatabase.getInstance().getReference("new_deliveries");
        deliveriesRef.push().setValue(delivery).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Customer.this, "Order Successfully made, wait for response for a few minutes.", Toast.LENGTH_SHORT).show();
                Intent x = new Intent(Customer.this, HomeActivity.class);
                startActivity(x);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Customer.this, "Error making delivery: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}