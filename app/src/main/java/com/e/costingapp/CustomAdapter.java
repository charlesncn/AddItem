package com.e.costingapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList item_id, item_name, item_cost, item_desc;
    Activity activity;


    CustomAdapter(Activity activity, Context context, ArrayList item_id, ArrayList item_name, ArrayList item_cost, ArrayList item_desc){
        this.activity = activity;
        this.context = context;
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_cost= item_cost;
        this.item_desc= item_desc;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.item_id_txt.setText(String.valueOf(item_id.get(position)));
        holder.item_name_txt.setText(String.valueOf(item_name.get(position)));
        holder.item_cost_txt.setText(String.valueOf(item_cost.get(position)));
        holder.item_desc_txt.setText(String.valueOf(item_desc.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(item_id.get(position)));
                intent.putExtra("name", String.valueOf(item_name.get(position)));
                intent.putExtra("cost", String.valueOf(item_cost.get(position)));
                intent.putExtra("description", String.valueOf(item_desc.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return item_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_id_txt, item_name_txt, item_cost_txt, item_desc_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_id_txt = itemView.findViewById(R.id.tv_id);
            item_name_txt = itemView.findViewById(R.id.tv_name);
            item_cost_txt = itemView.findViewById(R.id.tv_cost);
            item_desc_txt = itemView.findViewById(R.id.tv_desc);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
