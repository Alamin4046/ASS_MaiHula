package com.example.ass_maihula;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
public class Dashboard2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);

        final CardView inventory = findViewById(R.id.inventorybtn);
        final CardView product = findViewById(R.id.product);
        final CardView Customer = findViewById(R.id.customer);
        final CardView AddUSer = findViewById(R.id.addUser);

        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard2.this,MainActivity.class));

            }
        });

    }
}