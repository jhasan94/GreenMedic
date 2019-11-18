package com.capstone.greenmedic.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.capstone.greenmedic.R;

public class SelectUserActivity extends AppCompatActivity  implements View.OnClickListener{


    ImageButton userBt,pharmBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        pharmBt = (ImageButton)findViewById(R.id.pharmstore_bt);
        userBt = (ImageButton)findViewById(R.id.user_bt);


        userBt.setOnClickListener(this);
        pharmBt.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_bt:
                Toast.makeText(getApplicationContext(),"user",Toast.LENGTH_LONG).show();

                break;
            case R.id.pharmstore_bt:
                Intent intent = new Intent(SelectUserActivity.this,PharmSignupActivity.class);
                startActivity(intent);

                break;
        }


    }
}
