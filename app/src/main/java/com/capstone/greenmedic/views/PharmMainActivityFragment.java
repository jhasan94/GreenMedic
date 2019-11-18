package com.capstone.greenmedic.views;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.capstone.greenmedic.R;
import com.capstone.greenmedic.models.OrderRequest;
import com.capstone.greenmedic.models.Pharmacy;
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

/**
 * A placeholder fragment containing a simple view.
 */
public class PharmMainActivityFragment extends Fragment {

    public RecyclerView recyclerView,accptRecyclerView;
    public RecyclerView.LayoutManager layoutManager,accptlayoutManager;


    private FirebaseUser mAuth = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseDatabase database;
    private DatabaseReference requests;
    String userPhoneNo = mAuth.getPhoneNumber();

    private OpenOrderAdapter orderAdapter,accptorderAdapter;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    private List<OrderRequest> orderRequestList = new ArrayList<OrderRequest>();
    private List<OrderRequest> acceptedOrderRequestList = new ArrayList<OrderRequest>();
    Pharmacy userDetails;

    public PharmMainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pharm_main, container, false);
        setUserDetails();
        //        firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference().child("OrderRequests");

        recyclerView =(RecyclerView)view.findViewById(R.id.open_order_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        accptRecyclerView =(RecyclerView)view.findViewById(R.id.accepted_order_view);
        accptRecyclerView.setHasFixedSize(true);
        accptlayoutManager = new LinearLayoutManager(getContext());
        accptRecyclerView.setLayoutManager(accptlayoutManager);

        loadOpenOrders();
        return view;
    }

    private void setUserDetails(){
        mDatabase.child("pharmacies").child(mAuth.getPhoneNumber()+"").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot !=null){
                    userDetails =dataSnapshot.getValue(Pharmacy.class);

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "canceled", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void loadOpenOrders() {
        requests.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orderRequestList.clear();
                acceptedOrderRequestList.clear();
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child: children
                     ) {
                    OrderRequest orderRequest = child.getValue(OrderRequest.class);
                    if (orderRequest.getOrderStatus().equals("0") || (orderRequest.getOrderStatus().equals("71") && orderRequest.getAcceptedPharmacyAddress().equals(userDetails.getPharmacyAddress()))  ){
                        orderRequestList.add(orderRequest);

                    }
                    if (orderRequest.getOrderStatus().equals("99") && orderRequest.getAcceptedPharmacyPhoneNo().equals(userPhoneNo)){
                        acceptedOrderRequestList.add(orderRequest);

                    }


                }
                setOrderList(orderRequestList);
                setAcceptOrderList(acceptedOrderRequestList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setAcceptOrderList(List<OrderRequest> acceptedOrderRequestList) {
        accptorderAdapter = new OpenOrderAdapter(acceptedOrderRequestList,getView().getContext());
        accptRecyclerView.setAdapter(accptorderAdapter);
    }


    private void setOrderList(List<OrderRequest> orderRequestList) {
        orderAdapter = new OpenOrderAdapter(orderRequestList,getView().getContext());

        recyclerView.setAdapter(orderAdapter);


    }


}
