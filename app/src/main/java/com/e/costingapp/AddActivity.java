package com.e.costingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddActivity extends AppCompatActivity {

    LinearLayout back_icon;
    EditText item_name, cost, description;
    Button delete, update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        back_icon = findViewById(R.id.go_back);



        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        item_name = findViewById(R.id.ed_name);
        cost = findViewById(R.id.ed_cost);
        description = findViewById(R.id.ed_description);

        delete = findViewById(R.id.delete_btn);
        update = findViewById(R.id.update_btn);

        //update button onclick listener
        if(TextUtils.isEmpty(item_name.toString().trim())){
            Toast.makeText(AddActivity.this, "Enter item name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(cost.toString().trim())){
            Toast.makeText(AddActivity.this, "Enter item cost", Toast.LENGTH_SHORT).show();
        }
        else{

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDatabaseHelper1 myDB = new MyDatabaseHelper1(AddActivity.this);
                    myDB.addProject(item_name.getText().toString().trim(),
                            Integer.parseInt(cost.getText().toString().trim()),
                            description.getText().toString().trim());

                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.cancel){
            confirmDialog();
        }


        return super.onOptionsItemSelected(item);
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Discard Data!");
        builder.setMessage("Are you sure you want to discard this data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                item_name.getText().clear();
                cost.getText().clear();
                description.getText().clear();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}