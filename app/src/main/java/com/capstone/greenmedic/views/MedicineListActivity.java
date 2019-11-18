package com.capstone.greenmedic.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.greenmedic.R;
import com.capstone.greenmedic.models.Order;
import com.capstone.greenmedic.models.Pharmacy;
import com.capstone.greenmedic.viewholders.OrderedMedicineAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MedicineListActivity extends AppCompatActivity {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("OrderRequests");
    @BindView(R.id.total_cost_medi)
    TextView totalCostMedi;

    @BindView(R.id.accept_order)
    Button acceptOrderBt;

    @BindView(R.id.order_delivery_bt)
            Button orderDelivert;




    List<Order> medicineList = new ArrayList<Order>();

    String totalCOST;
    boolean orderStatus=true;

    String orderID;
    OrderedMedicineAdapter orderedMedicineAdapter;

    private DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseUser mAuth = FirebaseAuth.getInstance().getCurrentUser();
    Pharmacy userDetails;

    String orderStatusOrderRequest;



    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_list);


        ButterKnife.bind(this);

        recyclerView =(RecyclerView)findViewById(R.id.recyclerOrderMedicineView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        if (getIntent().hasExtra("order_id") && getIntent().hasExtra("total_cost") && getIntent().hasExtra("status")){
            orderID =getIntent().getStringExtra("order_id");
            totalCOST = getIntent().getStringExtra("total_cost");
            if (getIntent().getStringExtra("status").equals("na")){
                orderStatus=false;
            }
        }

        if (orderStatus){
            acceptOrderBt.setVisibility(View.GONE);
            orderDelivert.setVisibility(View.VISIBLE);

        }
        if (getIntent().hasExtra("OrderStatus")){
            orderStatusOrderRequest = getIntent().getStringExtra("OrderStatus");
            if (orderStatusOrderRequest.equals("1")){
                orderDelivert.setVisibility(View.GONE);
            }
        }

        totalCostMedi.setText(totalCOST);
        findMedicineList(orderID);



    }


    private void findMedicineList(String orderID) {
        mDatabase.child(orderID).child("medicines").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot!=null){
                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                    for (DataSnapshot child:
                            children) {
                        Order order = child.getValue(Order.class);
                        medicineList.add(order);

                    }
                    loadMedicineList(medicineList);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadMedicineList(List<Order> medicineList) {
        orderedMedicineAdapter = new OrderedMedicineAdapter(medicineList,getApplicationContext());

        recyclerView.setAdapter(orderedMedicineAdapter);

    }

    @OnClick(R.id.accept_order)
    public void acceptOrder(){
        mdatabase.child("pharmacies").child(mAuth.getPhoneNumber()+"").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot !=null){
                    userDetails =dataSnapshot.getValue(Pharmacy.class);
                    setOrderPharmacyDetails(userDetails);
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "canceled", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setOrderPharmacyDetails(Pharmacy userDetails) {
        try {
            mDatabase.child(orderID).child("acceptedPharmacyAddress").setValue(userDetails.getPharmacyAddress());
            mDatabase.child(orderID).child("acceptedPharmacyGeoLocation").setValue(userDetails.getGeoLocation());
            mDatabase.child(orderID).child("acceptedPharmacyPhoneNo").setValue(mAuth.getPhoneNumber());
            mDatabase.child(orderID).child("orderStatus").setValue("99");

            finish();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @OnClick(R.id.showOrderLocation_bt)
    public void showOrderLocation(){
        Intent intent = new Intent(getApplicationContext(),ShowOrderOnMapsActivity.class);
        intent.putExtra("orderID",orderID);
        startActivity(intent);

    }

    @OnClick(R.id.order_delivery_bt)
    public void setOrderDelivertStatus(){
        try {
            new AlertDialog.Builder(this)
                    .setTitle("Green Medic Pharmacy")
                    .setMessage("Do you really deliver order ?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            mDatabase.child(orderID).child("orderStatus").setValue("1");
                            finish();
                            Toast.makeText(MedicineListActivity.this, "Yaay", Toast.LENGTH_SHORT).show();
                        }})
                    .setNegativeButton(android.R.string.no, null).show();



        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
