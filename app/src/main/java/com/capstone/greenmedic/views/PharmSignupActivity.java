package com.capstone.greenmedic.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.capstone.greenmedic.R;
import com.capstone.greenmedic.models.Pharmacy;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PharmSignupActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    String mFullName,mEmail,mPharmacyName,mPharmacyAddress,mUsername,mPassword,mLicenceNo;

    @BindView(R.id.fullnat_et)
    TextInputEditText fullname;

    @BindView(R.id.email_et)
    TextInputEditText email;

    @BindView(R.id.pharm_name_et)
    TextInputEditText pharmName;

    @BindView(R.id.pharm_address_et)
    TextInputEditText pharmAddress;

    @BindView(R.id.username_et)
    TextInputEditText username;

    @BindView(R.id.password_et)
    TextInputEditText password;

    @BindView(R.id.licence_no)
    TextInputEditText licenceNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharm_signup);
        findDesination();
        ButterKnife.bind(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();




    }


    @OnClick(R.id.submit)
    public void signUp(){

        String nFullname =fullname.getText().toString();
        String nEmail =email.getText().toString();
        String nPharmName =pharmName.getText().toString();
        String nPharmAddress =pharmAddress.getText().toString();
        String nUsername =username.getText().toString();
        String nPassword =password.getText().toString();
        String nLicenceNo =licenceNo.getText().toString();

        if (nFullname.isEmpty()){
            fullname.setError("Enter code...");
            fullname.requestFocus();
            return;
        }else if (nEmail.isEmpty()){
            email.setError("Enter code...");
            email.requestFocus();
            return;
        }else if (nPharmName.isEmpty()){
            pharmName.setError("Enter code...");
            pharmName.requestFocus();
            return;
        }else if (nPharmAddress.isEmpty()){
            pharmAddress.setError("Enter code...");
            pharmAddress.requestFocus();
            return;
        }else if (nUsername.isEmpty()){
            username.setError("Enter code...");
            username.requestFocus();
            return;
        }else if (nPassword.isEmpty()){
            password.setError("Enter code...");
            password.requestFocus();
            return;
        }else if (nLicenceNo.isEmpty()){
            licenceNo.setError("Enter code...");
            licenceNo.requestFocus();
            return;
        }

        Pharmacy pharmacy =new Pharmacy(
                true,
                nFullname,
                nEmail,
                nPharmName,
                nPharmAddress,
                nUsername,
                nPassword,
                nLicenceNo,
                "000,000"
        );

        sendDataTotheFirebase(pharmacy);



    }

    private void sendDataTotheFirebase(Pharmacy pharmacy) {
        mDatabase.child("pharmacies").child(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()+"").setValue(pharmacy, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                assert databaseError != null;
                Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PharmSignupActivity.this, PharmMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        findDesination();

    }

    public void findDesination(){
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.child("pharmacies").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber() + "")) {
                    Intent intent = new Intent(PharmSignupActivity.this, PharmMainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    finish();
                    startActivity(intent);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
