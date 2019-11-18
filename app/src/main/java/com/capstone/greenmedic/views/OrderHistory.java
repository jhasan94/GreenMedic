package com.capstone.greenmedic.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.capstone.greenmedic.R;
import com.capstone.greenmedic.models.OrderRequest;
import com.capstone.greenmedic.viewholders.OpenOrderAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory extends AppCompatActivity {
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;


    FirebaseUser mAuth = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database;
    DatabaseReference requests;
    String userPhoneNo = mAuth.getPhoneNumber();

    OpenOrderAdapter orderAdapter;


    List<OrderRequest> orderRequestList = new ArrayList<OrderRequest>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        database = FirebaseDatabase.getInstance();
        requests = database.getReference().child("OrderRequests");

        recyclerView =(RecyclerView)findViewById(R.id.recyclerOrderHistoryView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        loadOrders();

    }
    private void loadOrders() {

        requests.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orderRequestList.clear();
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child:children){
                    OrderRequest orderRequest = child.getValue(OrderRequest.class);
                    if (orderRequest.getAcceptedPharmacyPhoneNo().equals(userPhoneNo) && orderRequest.getOrderStatus().equals("1")){
                        orderRequestList.add(orderRequest);
                    }

                }
                setOrderList(orderRequestList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    private void setOrderList(List<OrderRequest> orderRequestList) {
        orderAdapter = new OpenOrderAdapter(orderRequestList,getApplicationContext());

        recyclerView.setAdapter(orderAdapter);


    }
}
