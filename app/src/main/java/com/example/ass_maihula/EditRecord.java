package com.example.ass_maihula;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EditRecord extends AppCompatActivity {
    TextView cid2;
EditText itemesName,itemsqty,itemsPrice,itemsdest;
RadioGroup radioGroup;
RadioButton credit,paid;
Button btnEdit;
String cid,iname,qty,price,tprice,status,desc;
String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);
        cid2 = findViewById(R.id.cid2);
        //cid2.setText(getIntent().getStringExtra("id"));
        itemesName = findViewById(R.id.itemsName);
        itemsqty = findViewById(R.id.itemsqty);
        itemsPrice = findViewById(R.id.itemsPrice);
        itemsdest = findViewById(R.id.itemsdes);
        radioGroup = findViewById(R.id.status);
        credit = findViewById(R.id.scredit);
        paid = findViewById(R.id.spaid);
        btnEdit = findViewById(R.id.updateRecord);

        getAndSetIntentData();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHelper db = new MyDBHelper(EditRecord.this);
                int status;
                if (credit.isChecked()){
                    status =0;
                }else {
                    status = 1;
                }
                id = getIntent().getStringExtra("id");
                cid = getIntent().getStringExtra("cid");

                boolean update = db.updateData(id,itemesName.getText().toString().trim(),Integer.valueOf(itemsqty.getText().toString().trim()),
                        Integer.valueOf(itemsPrice.getText().toString().trim()),status,itemsdest.getText().toString().trim());
                //db.notify();
                db.close();
                if (update==true){
                    Toast.makeText(EditRecord.this, "Data Updated!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditRecord.this,New_item_list.class);
                    intent.putExtra("cid",cid);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(EditRecord.this, "There is an error!!", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }

    private void getAndSetIntentData() {
        if(getIntent().hasExtra("tprice") && getIntent().hasExtra("iname") && getIntent().hasExtra("qty") && getIntent().hasExtra("price")){
            //get data from intent
           cid = getIntent().getStringExtra("id");
//           id = Integer.parseInt(getIntent().getStringExtra("id"));
            iname = getIntent().getStringExtra("iname");
            qty = getIntent().getStringExtra("qty");
            price = getIntent().getStringExtra("price");
            tprice = getIntent().getStringExtra("tprice");
            // status = getIntent().getStringExtra("staus");
            desc = getIntent().getStringExtra("description");

            //setting  intent data
            cid2.setText(cid);
            itemesName.setText(iname);
            itemsqty.setText(qty);
            itemsPrice.setText(price);
            itemsdest.setText(desc);
        }else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }

    }

}