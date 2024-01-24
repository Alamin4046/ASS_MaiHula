package com.example.ass_maihula;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class AddCustomer extends AppCompatActivity {

    View view;
    EditText name_input,phone_input,address_input;
    Button add_button;
    MyDBHelper db;
    int id;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        name_input = findViewById(R.id.txtname);
       phone_input = findViewById(R.id.phone);
       address_input = findViewById(R.id.address);
        add_button = findViewById(R.id.add_button);


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name_input.length() ==0){
                    name_input.setError("Enter name");
                }
                else {
                    MyDBHelper db = new MyDBHelper(AddCustomer.this);
                    db.addcustomer(name_input.getText().toString().trim(), phone_input.getText().toString().trim(), address_input.getText().toString().trim());
                    Intent intent = new Intent(AddCustomer.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


}