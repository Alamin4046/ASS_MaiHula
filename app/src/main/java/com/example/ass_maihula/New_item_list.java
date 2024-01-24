package com.example.ass_maihula;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class New_item_list extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> itemList;
    Adapter adapter;
    FloatingActionButton add_items;
    MyDBHelper db;
    ArrayList<Integer> id,qty,price,tprice;
    ArrayList<String> iname,date;
    String name,cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item_list);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        cid = getIntent().getStringExtra("cid");
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("cid",cid);
        editor.apply();
        add_items = findViewById(R.id.add_item);
        add_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(New_item_list.this,UpdateActivity.class);
                intent.putExtra("cid",cid);
                startActivity(intent);
                finish();
            }
        });

        db = new MyDBHelper(this);
        id = new ArrayList<>();
        iname = new ArrayList<>();
        qty = new ArrayList<>();
        price = new ArrayList<>();
        tprice = new ArrayList<>();
        date = new ArrayList<>();
        itemList = new ArrayList<>();

        initData();
        initRecylerview();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    private void initData() {
        Cursor cursor = db.readAllItems(cid);
        if (cursor.getCount() == 0){
            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()) {
                id.add(cursor.getInt(0));
                iname.add(cursor.getString(2));
                qty.add(cursor.getInt(3));
                price.add(cursor.getInt(4));
                tprice.add(cursor.getInt(5));
                date.add(cursor.getString(6));
            }
        }

        db.close();
        cursor.close();


    }
    public void removeItem(int position){
        id.remove(position);
        adapter.notifyItemRemoved(position);
    }

    private void initRecylerview() {
        recyclerView = findViewById(R.id.recyclerView12);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(this,this,id,iname,qty,price,tprice,date);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemListener(new Adapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
                db.deteleRow( cid);
            }
        });
        adapter.notifyDataSetChanged();

    }
}