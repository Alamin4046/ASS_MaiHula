package com.example.ass_maihula;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UpdateActivity extends AppCompatActivity {
    TextView edit,cidi,tprice_input;
    EditText item_input,qty_input,price_input,descr;
    RadioGroup radioGroup;
    RadioButton credit,paid;
    Button update_btn;
    String iname,qty,price,tprice,status,desc;
    int id;
    String cid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        cidi = findViewById(R.id.cid1);
        cidi.setText(getIntent().getStringExtra("cid"));
        item_input = findViewById(R.id.itemsE);
        qty_input = findViewById(R.id.qttyE);
        price_input = findViewById(R.id.priceE);
      //  tprice_input = findViewById(R.id.tpriceE);
        descr = findViewById(R.id.desc);
        radioGroup = findViewById(R.id.radiogroup);
        credit = findViewById(R.id.rcredit);
        paid = findViewById(R.id.rpaid);
        edit = findViewById(R.id.edit);
        update_btn = findViewById(R.id.update_button);


        getAndSetIntentData();


        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHelper db = new MyDBHelper(UpdateActivity.this);
                int status;
                if (credit.isChecked()){
                    status =0;
                }else {
                    status = 1;
                }
                String datei = getdate();
                cid = getIntent().getStringExtra("cid");
               boolean insert = db.additems(cid,item_input.getText().toString().trim(),Integer.valueOf(qty_input.getText().toString().trim()),
                       Integer.valueOf(price_input.getText().toString().trim()),status,datei,descr.getText().toString().trim());
               if (insert==true){
                   Toast.makeText(UpdateActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateActivity.this,New_item_list.class);
                    intent.putExtra("cid",cid);
                   startActivity(intent);
                   finish();
               } else {
                   Toast.makeText(UpdateActivity.this, "There is error in the inserting!!", Toast.LENGTH_SHORT).show();
               }

            }

        });


    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("tprice") && getIntent().hasExtra("iname") && getIntent().hasExtra("qty") && getIntent().hasExtra("price")){
            //get data from intent
            cid = getIntent().getStringExtra("cid");
//            id = Integer.parseInt(getIntent().getStringExtra("id"));
            iname = getIntent().getStringExtra("iname");
            qty = getIntent().getStringExtra("qty");
            price = getIntent().getStringExtra("price");
            tprice = getIntent().getStringExtra("tprice");
           // status = getIntent().getStringExtra("staus");
            desc = getIntent().getStringExtra("description");

            //setting  intent data
            item_input.setText(iname);
            qty_input.setText(qty);
            price_input.setText(price);
//            tprice_input.setText(tprice);
            descr.setText(desc);
        }else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
    public String getdate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-m-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}