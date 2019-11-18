package com.capstone.greenmedic.views;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.capstone.greenmedic.R;
import com.capstone.greenmedic.directionhelpers.FetchURL;
import com.capstone.greenmedic.models.GpsCoordinate;
import com.capstone.greenmedic.models.Order;
import com.capstone.greenmedic.models.OrderRequest;
import com.capstone.greenmedic.models.Pharmacy;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.capstone.greenmedic.directionhelpers.TaskLoadedCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowOrderOnMapsActivity extends FragmentActivity implements OnMapReadyCallback, TaskLoadedCallback{

    private GoogleMap mMap;

    private MarkerOptions place1, place2;
    private Polyline currentPolyline;
    String orderID;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseUser mAuth = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference mRequests = mDatabase.child("OrderRequests");
    Pharmacy userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        orderID = getIntent().getStringExtra("orderID");
        setUserDetails();
        findLocation(orderID);super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order_on_maps);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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
                Toast.makeText(getApplicationContext(), "canceled", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void findLocation(String orderID) {
        mRequests.child(orderID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot!=null){
                    OrderRequest orderRequest = dataSnapshot.getValue(OrderRequest.class);
                    setLoaction(orderRequest);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void setLoaction(OrderRequest orderRequest) {
        GpsCoordinate pharmacyCo = new GpsCoordinate(userDetails.getGeoLocation());
        GpsCoordinate userCo = new GpsCoordinate(orderRequest.getUserCurrentGeoLocation());

        place1 = new MarkerOptions().position(new LatLng(pharmacyCo.getLatitude(), pharmacyCo.getLongitude())).title("Your Pharmacy");
        place2 = new MarkerOptions().position(new LatLng(userCo.getLatitude(), userCo.getLongitude())).title("Order Location");

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
       // LatLng sydney = new LatLng(18.015365, -77.499382);
        //mMap.addMarker(place1);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place1.getPosition(),16));

        mMap.addMarker(place1);
        mMap.addMarker(place2);
        new FetchURL(ShowOrderOnMapsActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");


    }
    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }


    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }
}
