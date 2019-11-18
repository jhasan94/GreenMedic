package com.capstone.greenmedic.viewholders;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.capstone.greenmedic.R;
import com.capstone.greenmedic.models.OrderRequest;
import com.capstone.greenmedic.views.MedicineListActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OpenOrderAdapter extends RecyclerView.Adapter<OpenOrderViewHolder> {

    private List<OrderRequest> orderList = new ArrayList<>();
    private Context context;


    public OpenOrderAdapter(List<OrderRequest> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @NonNull
    @Override
    public OpenOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_row_order_for_pharmacy,parent,false);

        return new OpenOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OpenOrderViewHolder holder, int position) {
        holder.costOfOrder.setText(orderList.get(position).getOrderTotalCost());
        holder.useraddress.setText(orderList.get(position).getUserAddress());
        holder.username.setText(orderList.get(position).getUserName());
        holder.userPhoneNo.setText(orderList.get(position).getUserPhone());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(context,orderList.get(position).getOrderID(),Toast.LENGTH_SHORT).show();*/

                Intent intent = new Intent(context, MedicineListActivity.class);
                intent.putExtra("order_id",orderList.get(position).getOrderID());
                intent.putExtra("total_cost",orderList.get(position).getOrderTotalCost());
                intent.putExtra("status",orderList.get(position).getAcceptedPharmacyPhoneNo());
                intent.putExtra("OrderStatus",orderList.get(position).getOrderStatus());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
