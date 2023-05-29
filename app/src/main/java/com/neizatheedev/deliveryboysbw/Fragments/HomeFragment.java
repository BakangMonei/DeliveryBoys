package com.neizatheedev.deliveryboysbw.Fragments;

/**
 * @author Monei Bakang Motshegwe
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.neizatheedev.deliveryboysbw.Activities.LoginActivtity;
import com.neizatheedev.deliveryboysbw.MenuActivities.CartActivity;
import com.neizatheedev.deliveryboysbw.MenuActivities.FastFoodActivity;
import com.neizatheedev.deliveryboysbw.MenuActivities.Customer;
import com.neizatheedev.deliveryboysbw.Model.MyModel;
import com.neizatheedev.deliveryboysbw.MenuActivities.PharmacyActivity;
import com.neizatheedev.deliveryboysbw.MenuActivities.PickAndDrop;
import com.neizatheedev.deliveryboysbw.R;
import com.neizatheedev.deliveryboysbw.MenuActivities.VulaActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {

    //Decelerations of variables and components to be used in the class
    private static final Object TAG = null;
    TextView usernameView;
    ImageView logout;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseFirestore mFirestore;
    String UserId;
    TextView numberOfProducts, numberOfdeletedProducts, purchasedItems;
    ImageView groceryImageView, pharmacyImageView, fastFoodImageView, pickImageView, vulaImageView, cartImageView;
    TextView groceryTextView, pharmacyTextView, fastFoodTextView, pickTextView, vulaTextView, cartTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.fragment_dashboard, container, false);

        /************** GROCERY **************************************************************************************************************************************************/

        groceryImageView = (ImageView) view1.findViewById(R.id.groceryImageView);
        groceryTextView = (TextView) view1.findViewById(R.id.groceryTextView);
        groceryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGrocery();
            }
        });

        groceryImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGrocery();
            }
        });

        /************* PHARMACY ***************************************************************************************************************************************************/

        pharmacyImageView = (ImageView) view1.findViewById(R.id.pharmacyImageView);
        pharmacyTextView = (TextView) view1.findViewById(R.id.pharmacyTextView);

        pharmacyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // goToPharmacy();
            }
        });
        pharmacyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // goToPharmacy();
            }
        });
        /******************* FAST-FOOD *********************************************************************************************************************************************/

        fastFoodImageView = (ImageView) view1.findViewById(R.id.fastFoodImageView);
        fastFoodTextView = (TextView) view1.findViewById(R.id.fastFoodTextView);

        fastFoodImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // goToFastFood();
            }
        });
        fastFoodTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // goToFastFood();
            }
        });
        /***************** PICK AND DROP ***********************************************************************************************************************************************/
        pickImageView = (ImageView) view1.findViewById(R.id.pickImageView);
        pickTextView = (TextView) view1.findViewById(R.id.pickTextView);

        pickImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // goToPick();
            }
        });

        pickTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // goToPick();
            }
        });
        /*****************ALCOHOL BEVERAGES***********************************************************************************************************************************************/

        vulaImageView = (ImageView) view1.findViewById(R.id.vulaImageView);
        vulaTextView = (TextView) view1.findViewById(R.id.vulaTextView);

        vulaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // goToVula();
            }
        });

        vulaImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // goToVula();
            }
        });

        /********************* CART *******************************************************************************************************************************************/

        cartTextView = (TextView) view1.findViewById(R.id.cartTextView);
        cartImageView = (ImageView) view1.findViewById(R.id.cartImageView);

        cartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // goToCart();
            }
        });

        cartTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // goToCart();
            }
        });
        /****************************************************************************************************************************************************************/
        //Firebase Instance
        firebaseAuth = FirebaseAuth.getInstance();
        UserId = firebaseAuth.getCurrentUser().getUid();
        mFirestore = FirebaseFirestore.getInstance();
        usernameView = view1.findViewById(R.id.displayusername);

        //Components Instances
        logout = view1.findViewById(R.id.logoutAction);
        // numberOfProducts = view1.findViewById(R.id.addedTxtView);
        // numberOfdeletedProducts = view1.findViewById(R.id.deletedTxtView);
        // purchasedItems = view1.findViewById(R.id.item_purchased);


        //Check If User is logged In
        if (UserId == null) {
            Intent intent = new Intent(HomeFragment.this.getActivity(), LoginActivtity.class);
            startActivity(intent);
        }


        //Fetch Deleted Products according to product owner from database
        mFirestore.collection("deleted_products").whereEqualTo("owner", firebaseAuth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count = 0;
                            for (DocumentSnapshot document : task.getResult()) {
                                count++;
                                System.out.println("The size" + count);
                                String total = String.valueOf(count);
                                numberOfdeletedProducts.setText(total);
                            }
                        } else {
                            Log.d(String.valueOf(TAG), "Error getting documents: ", task.getException());
                        }
                    }
                });


        //Fetch Purchased Products for the user currently logged in
        mFirestore.collection("purchasedItems").whereEqualTo("owner", firebaseAuth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count = 0;
                            for (DocumentSnapshot document : task.getResult()) {
                                count++;
                                System.out.println("The size" + count);
                                String total = String.valueOf(count);
                                purchasedItems.setText(total);
                            }
                        } else {
                            Log.d(String.valueOf(TAG), "Error getting documents: ", task.getException());
                        }
                    }
                });

        //Fetch users from database and display their user name in a textview
        mFirestore.collection("user").document(UserId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String user_name = documentSnapshot.getString("user_name");
                usernameView.setText(user_name);
            }
        });

        //Log user out from system
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(HomeFragment.this.getActivity(), LoginActivtity.class);
                startActivity(intent);

            }
        });

        //Fetch the number of product items & Load array and get firestore instance.
        ArrayList<MyModel> modelArrayList;
        modelArrayList = new ArrayList<>();
        mFirestore = FirebaseFirestore.getInstance();

        Query query = mFirestore.collection("products").whereEqualTo("product_owner", firebaseAuth.getCurrentUser().getEmail());
        query.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {


                        //Fetch from firebase all the documents
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            Log.d(String.valueOf(TAG), document.getId() + " => " + document.getData());
                            String prodId = document.getId().toString();
                            String date = document.getString("date_added");
                            String productName = document.getString("product_name");
                            String owner = document.getString("product_owner");
                            String product_description = document.getString("product_description");
                            String product_site = document.getString("product_site");
                            String product_price = document.getString("product_price");
                            String user_id = document.getString("user_id");
                            String product_address = document.getString("product_address");
                            int lat = document.getLong("latitude").intValue();
                            String latitude = String.valueOf(lat);
                            int longi = document.getLong("longitude").intValue();
                            String longitude = String.valueOf(longi);
                            String image_url = document.getString("image_url");
                            String status = document.getString("status");
                            MyModel products = new MyModel(prodId, productName, product_description, date, product_address, owner, user_id, product_site, image_url, latitude, longitude, product_price, status);

                            //Add products to the list
                            modelArrayList.add(products);

                            //Display Number of total Products
                            String number = String.valueOf(modelArrayList.size());
                            numberOfProducts.setText(number);


                        }
                        Log.d(String.valueOf(TAG), "Array Items => " + modelArrayList.size());
                    } else {
                        Log.d(String.valueOf(TAG), "Error getting documents: ", task.getException());
                    }
                });
        return view1;
    }

    /***************************** METHODS ***********************************/
    public void goToGrocery() {
        Intent a = new Intent(getActivity(), Customer.class);
        startActivity(a);
    }
    public void goToPharmacy(){
        Intent b = new Intent(getActivity(), PharmacyActivity.class);
        startActivity(b);
    }

    public void goToFastFood(){
        Intent c = new Intent(getActivity(), FastFoodActivity.class);
        startActivity(c);
    }

    public void goToPick(){
        Intent d = new Intent(getActivity(), PickAndDrop.class);
        startActivity(d);
    }

    public void goToVula(){
        Intent e = new Intent(getActivity(), VulaActivity.class);
        startActivity(e);
    }
    public void goToCart(){
        Intent f = new Intent(getActivity(), CartActivity.class);
        startActivity(f);
    }
    /****************************************************************/

    public void getAllData() throws IOException {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    }

}


