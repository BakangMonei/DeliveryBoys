package com.neizatheedev.deliveryboysbw.Adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.neizatheedev.deliveryboysbw.Model.Users;
import com.neizatheedev.deliveryboysbw.R;

public class UsersAdapter extends FirebaseRecyclerAdapter<Users, UsersAdapter.personsViewholder> {
    public static final int MULTIPLE_PERMISSIONS = 101;

    public UsersAdapter(@NonNull FirebaseRecyclerOptions<Users> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull personsViewholder holder, int position, @NonNull Users model) {
        holder.textEmail.setText(model.getEmail()); // email
        holder.textPhoneNumber.setText(model.getPhone()); // Phone Number
        holder.textProducts.setText(model.getProduct()); // Grocery List
        holder.textDestination.setText(model.getTo()); // To
        holder.textShop.setText(model.getShop()); // Shop
        holder.textPay.setText(model.getPay()); // Payment Method
        holder.textFrom.setText(model.getFrom()); // Mall

        final String phoneNumber = model.getPhone();
        final String email = model.getEmail();

        holder.buttonApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS(phoneNumber, view);
            }
        });


        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRecord(view, email);
            }
        });


        holder.buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCall(phoneNumber, view);
            }
        });
    }

    private void sendSMS(final String phoneNumber, final View view) {
        // Request the SEND_SMS permission
        if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) view.getContext(), new String[]{Manifest.permission.SEND_SMS}, MULTIPLE_PERMISSIONS);
            return;
        }

        // Get the default SMS manager
        SmsManager smsManager = SmsManager.getDefault();

        try {
            // Define the SMS message content
            String message = "Hello this is Mr. Theo Ame Monageng";
            String email;

            // Send the SMS message
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);

            // If the SMS is sent successfully, delete the record
            deleteRecordSMS(view, phoneNumber);

            // Display a toast message
            Toast.makeText(view.getContext(), "SMS sent and record deleted successfully!", Toast.LENGTH_SHORT).show();
        } catch (SecurityException e) {
            // SMS sending permission is not granted
            e.printStackTrace();
            Toast.makeText(view.getContext(), "SMS sending permission denied!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // Failed to send the SMS
            e.printStackTrace();
            Toast.makeText(view.getContext(), "Failed to send SMS!", Toast.LENGTH_SHORT).show();
        }
    }


    // Delete Button
    private void deleteRecord(View view, String email) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("new_deliveries");
        Query query = databaseReference.orderByChild("email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    snapshot.getRef().removeValue();
                }
                Toast.makeText(view.getContext(), "Record deleted successfully!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(view.getContext(), "Failed to delete record!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Delete for 'APPROVED BUTTON'
    private void deleteRecordSMS(View view, String phone) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("new_deliveries");
        Query query = databaseReference.orderByChild("phone").equalTo(phone);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    snapshot.getRef().removeValue();
                }
                Toast.makeText(view.getContext(), "Record deleted successfully!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(view.getContext(), "Failed to delete record!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void makeCall(String phoneNumber, View view) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));

        if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            view.getContext().startActivity(intent);
        } else {
            ActivityCompat.requestPermissions((Activity) view.getContext(), new String[]{Manifest.permission.CALL_PHONE}, MULTIPLE_PERMISSIONS);
        }
    }

    @NonNull
    @Override
    public personsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userentry, parent, false);
        return new UsersAdapter.personsViewholder(view);
    }


    class personsViewholder extends RecyclerView.ViewHolder {
        TextView textEmail, textPhoneNumber, textProducts, textDestination,  textShop, textPay, textFrom;
        Button buttonApprove, buttonDelete, buttonCall;

        public personsViewholder(@NonNull View itemView) {
            super(itemView);
            textEmail = itemView.findViewById(R.id.textEmail); // email
            textPhoneNumber = itemView.findViewById(R.id.textPhoneNumber); // phone number
            textProducts = itemView.findViewById(R.id.textProducts); // Grocery List
            textDestination = itemView.findViewById(R.id.textDestination); // User stay at Location
            textShop = itemView.findViewById(R.id.textShop); // shop at the selected mall
            textPay = itemView.findViewById(R.id.textPay); // Payment method
            textFrom = itemView.findViewById(R.id.textFromPlace); //


            buttonApprove = itemView.findViewById(R.id.buttonApprove);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonCall = itemView.findViewById(R.id.buttonCall);
        }
    }
}


