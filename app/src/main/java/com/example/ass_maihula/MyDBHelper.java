package com.example.ass_maihula;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class MyDBHelper extends SQLiteOpenHelper {
    Context context;
    public static final String DATABASE_NAME = "Customers.db";
    public static final int DATABASE_VERSION = 1;

    public static final String Table_name ="My_customers";

    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table myCustomer (id INTEGER primary key autoincrement,name TEXT,phone TEXT,address TEXT)");
        sqLiteDatabase.execSQL("create table AddItem (id INTEGER primary key autoincrement,cid TEXT,iname TEXT,quantity INTEGER, price INTEGER,tprice INTEGER,date INTEGER ,status INTEGER,description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("drop table if exists myCustomer");
            sqLiteDatabase.execSQL("drop table if exists AddItem");
            onCreate(sqLiteDatabase);
    }
    public boolean addcustomer(String name, String phone, String address){
        SQLiteDatabase sql =this.getWritableDatabase();
        ContentValues values = new ContentValues();

            values.put("name", name);
            values.put("phone", phone);
            values.put("address", address);

        long result = sql.insert("myCustomer",null,values);
        if(result==-1) return false;
        else
            return true;
    }
    public boolean additems(String uid, String iname, int qty,int price,int status,String date,String desc){
        SQLiteDatabase sql =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        int tprice;
        tprice = qty*price;
            values.put("cid",uid);
            values.put("iname", iname);
            values.put("quantity", qty);
            values.put("price", price);
            values.put("tprice", tprice);
            values.put("date", date);
            values.put("status", status);
            values.put("description", desc);

        long result = sql.insert("AddItem",null,values);
        if(result==-1) return false;
        else
            return true;
    }
    public Cursor readAllData(){
            SQLiteDatabase sql = this.getReadableDatabase();
            return sql.rawQuery("SELECT * FROM myCustomer ",null);
        }
        public Cursor readAllItems(String cid){
            SQLiteDatabase sql = this.getReadableDatabase();
            return sql.rawQuery("SELECT * FROM AddItem where cid = ? and status = 0 ", new String[]{cid});
        }
       public boolean updateData(String id,String items,int qty,int price,int status,String descr){
        SQLiteDatabase sql = this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put("iname",items);
        values.put("quantity",qty);
        values.put("price",price);
        values.put("status",status);
        values.put("description",descr);
        long result = sql.update("AddItem",values,"id=?",new String[] {(id)});
        if (result==-1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;

        }
        }
        public boolean deteleRow(String id){
        SQLiteDatabase sql = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status",1);
        long result = sql.update("AddItem",values,"id=?" ,new String[] {id});
         if (result==-1){
             Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
             return false;
         }else {
             return true;
         }
        }

    }

