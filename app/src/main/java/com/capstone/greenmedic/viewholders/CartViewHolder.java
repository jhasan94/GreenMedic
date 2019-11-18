package com.capstone.greenmedic.viewholders;

import android.view.View;
import android.widget.TextView;

import com.capstone.greenmedic.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @BindView(R.id.name)
    TextView mediName;

    @BindView(R.id.dosage_form)
    TextView mediDosage;

    @BindView(R.id.element)
    TextView mediElement;

    @BindView(R.id.strength)
    TextView mediStrength;

    @BindView(R.id.company)
    TextView mediCompany;

    @BindView(R.id.cost)
    TextView mediCost;
    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }


    @Override
    public void onClick(View v) {

    }
}
