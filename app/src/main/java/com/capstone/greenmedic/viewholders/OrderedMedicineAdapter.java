package com.capstone.greenmedic.viewholders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.capstone.greenmedic.R;
import com.capstone.greenmedic.models.Order;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class OrderedMedicineAdapter  extends RecyclerView.Adapter<CartViewHolder> {

    private List<Order> medicineList = new ArrayList<Order>();
    private Context context;

    public OrderedMedicineAdapter(List<Order> medicineList, Context context) {
        this.medicineList = medicineList;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_layout,parent,false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.mediName.setText(medicineList.get(position).getMedicineName());
        holder.mediDosage.setText(medicineList.get(position).getDosageForm());
        holder.mediElement.setText(medicineList.get(position).getElement());
        holder.mediStrength.setText(medicineList.get(position).getStrength());
        holder.mediCompany.setText(medicineList.get(position).getCompany());
        holder.mediCost.setText(medicineList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }
}
