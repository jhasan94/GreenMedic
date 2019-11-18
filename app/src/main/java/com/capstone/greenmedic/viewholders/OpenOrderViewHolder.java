package com.capstone.greenmedic.viewholders;

import android.view.View;
import android.widget.TextView;

import com.capstone.greenmedic.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OpenOrderViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.userName)
    TextView username;
    @BindView(R.id.userAddress)
    TextView useraddress;

    @BindView(R.id.cost)
    TextView costOfOrder;

    @BindView(R.id.user_phone_no)
    TextView userPhoneNo;

    public OpenOrderViewHolder(@NonNull View itemView) {
        super(itemView);

        ButterKnife.bind(this,itemView);
    }
}
