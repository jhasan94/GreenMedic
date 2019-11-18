package com.capstone.greenmedic.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.capstone.greenmedic.models.Pharmacy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.greenmedic.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class PharmMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;

    private NavigationView navigationView;
    private TextView nav_header_name;
    private TextView nav_header_email;
    private ImageView profilePic;


    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseUser mAuth = FirebaseAuth.getInstance().getCurrentUser();
    Pharmacy userDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharm_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setUserDetails();

        setupDrawer();
        navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

/*        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }


    private void setupDrawer() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


    private void setUserDetails(){
        mDatabase.child("pharmacies").child(mAuth.getPhoneNumber()+"").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot !=null){
                    userDetails =dataSnapshot.getValue(Pharmacy.class);
                    setNaveBarValues();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "canceled", Toast.LENGTH_SHORT).show();

            }
        });


    }


    private void setNaveBarValues() {

        View view = navigationView.getHeaderView(0);

        nav_header_name = (TextView)view.findViewById(R.id.profile_name);
        nav_header_email =(TextView)view.findViewById(R.id.email);
        profilePic = (ImageView) view.findViewById(R.id.profile_pic);

        if (mAuth !=null){
            nav_header_name.setText(userDetails.getFullName().toString());
            nav_header_email.setText(userDetails.getEmail().toString());
            Picasso.get().load(mAuth.getPhotoUrl()).into(profilePic);

        }



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){

            case R.id.support:
                mailToSupport();
                return true;

            case R.id.orderHistory:
                openOrderHistory();

                return true;
            case R.id.sign_out:
                signOut();
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void mailToSupport() {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"jahidnsu142@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Pharmacy Support:Green Medic Pharmacy");
        email.putExtra(Intent.EXTRA_TEXT, "");
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }

    private void openOrderHistory() {
        Intent intent =  new Intent(getApplicationContext(),OrderHistory.class);
        startActivity(intent);
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
    }



}
