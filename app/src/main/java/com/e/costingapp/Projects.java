package com.e.costingapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class Projects extends Fragment {

    RecyclerView recyclerView;
//    FloatingActionButton addbutton;
//    private int STORAGE_PERMISSION_CODE= 1;
    MyDatabaseHelper1 myDB;
    ArrayList<String> item_id, item_name, item_cost, item_desc;
    CustomAdapter customAdapter;

    //ImageView empty_image;
    //TextView empty_text;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Projects() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_projects, container, false);

//        addbutton = rootView.findViewById(R.id.actionBtn);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerview1);
        //empty_image = rootView.findViewById(R.id.img_no_data);
        //empty_text = rootView.findViewById(R.id.tv_no_data);

        myDB =new MyDatabaseHelper1(getContext());
        item_id =new ArrayList<>();
        item_name =new ArrayList<>();
        item_cost =new ArrayList<>();
        item_desc =new ArrayList<>();

        storeDataInArray();

        customAdapter = new CustomAdapter(getActivity(), getContext(), item_id, item_name, item_cost, item_desc);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return rootView;
    }

    void storeDataInArray(){
        Cursor cursor =myDB.readAllData();
        if(cursor!=null && cursor.getCount() > 0) {
            if (cursor.getCount() == 0) {
                //empty_image.setVisibility(View.VISIBLE);
               // empty_text.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    item_id.add(cursor.getString(0));
                    item_name.add(cursor.getString(1));
                    item_cost.add(cursor.getString(2));
                    item_desc.add(cursor.getString(3));
                }
               // empty_image.setVisibility(View.GONE);
               // empty_text.setVisibility(View.GONE);
            }
        }
    }



}