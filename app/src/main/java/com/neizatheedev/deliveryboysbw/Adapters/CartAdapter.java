package com.neizatheedev.deliveryboysbw.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

public class CartAdapter extends FirebaseRecyclerAdapter<Users, CartAdapter.personsViewholder> {
    public static final int MULTIPLE_PERMISSIONS = 101;
    private String email;

    public CartAdapter(@NonNull FirebaseRecyclerOptions<Users> options, String email) {
        super(options);
        this.email = email;
    }

    @Override
    protected void onBindViewHolder(@NonNull CartAdapter.personsViewholder holder, int position, @NonNull Users model) {
        if (model.getEmail().equals(email)) {
            holder.textEmail.setText(model.getEmail());
            holder.textPhoneNumber.setText(model.getPhone());
            holder.textProducts.setText(model.getProduct());
            holder.textDestination.setText(model.getTo());
            holder.textShop.setText(model.getShop());
            holder.textPay.setText(model.getPay());
            holder.textFrom.setText(model.getFrom());

            final String phoneNumber = model.getPhone();
            final String email = model.getEmail();

            holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteRecord(view, email);
                }
            });
        } else {
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }
    }

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

    @NonNull
    @Override
    public CartAdapter.personsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.usercart, parent, false);
        return new CartAdapter.personsViewholder(view);
    }

    class personsViewholder extends RecyclerView.ViewHolder {
        TextView textEmail, textPhoneNumber, textProducts, textDestination, textShop, textPay, textFrom;
        Button buttonApprove, buttonDelete, buttonCall;

        public personsViewholder(@NonNull View itemView) {
            super(itemView);
            textEmail = itemView.findViewById(R.id.textEmail);
            textPhoneNumber = itemView.findViewById(R.id.textPhoneNumber);
            textProducts = itemView.findViewById(R.id.textProducts);
            textDestination = itemView.findViewById(R.id.textDestination);
            textShop = itemView.findViewById(R.id.textShop);
            textPay = itemView.findViewById(R.id.textPay);
            textFrom = itemView.findViewById(R.id.textFromPlace);

            buttonApprove = itemView.findViewById(R.id.buttonApprove);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonCall = itemView.findViewById(R.id.buttonCall);
        }
    }
}

