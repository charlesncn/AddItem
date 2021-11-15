package com.e.costingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Objects;

import static java.lang.Integer.getInteger;
import static java.lang.Integer.parseInt;

public class UpdateActivity extends AppCompatActivity {

    EditText item_name2, item_cost2, item_description2;
    Button btn_update2, btn_delete2;
    String id;
    String name;
    String cost;
    String description;
    LinearLayout l_l_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        item_name2 = findViewById(R.id.ed_name2);
        item_cost2 = findViewById(R.id.ed_cost2);
        item_description2 = findViewById(R.id.ed_description2);

        btn_update2 = findViewById(R.id.update_btn2);
        btn_delete2 = findViewById(R.id.delete_btn2);
        l_l_back = findViewById(R.id.go_back);
//      call method
        getAndSetIntentData();


//        go back
        l_l_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                MainActivity mainActivity = new MainActivity();
                mainActivity.finish();
            }
        });
        btn_update2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper1 myDb = new MyDatabaseHelper1(UpdateActivity.this);
                name = item_name2.getText().toString().trim();
                cost = item_cost2.getText().toString().trim();
                description = item_description2.getText().toString().trim();
                myDb.updateData(id, name, cost, description);


            }
        });

        btn_delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("cost")
                && getIntent().hasExtra("description")){

//          getting data from intent
            id= getIntent().getStringExtra("id");
            name= getIntent().getStringExtra("name");
            cost = getIntent().getStringExtra("cost");
            description= getIntent().getStringExtra("description");
            //setting data to the intent
            item_name2.setText(name);
            item_cost2.setText((cost));
            item_description2.setText(description);
        }
        else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete "+name+"?");
        builder.setMessage("Are you sure you want to delete '"+name+"' permanently?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper1 myDb =new MyDatabaseHelper1(UpdateActivity.this);
                myDb.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}