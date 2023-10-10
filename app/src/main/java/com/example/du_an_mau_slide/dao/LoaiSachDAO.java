package com.example.du_an_mau_slide.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_mau_slide.dbhelper.DBHelper;
import com.example.du_an_mau_slide.model.LoaiSach;
import com.example.du_an_mau_slide.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    SQLiteDatabase db;

    public LoaiSachDAO(Context context){
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(LoaiSach obj){
        ContentValues values = new ContentValues();

        values.put("tenLoai", obj.getTenLoai());

        return db.insert("LoaiSach", null, values);
    }

    public int update(LoaiSach obj){
        ContentValues values = new ContentValues();

        values.put("tenLoai", obj.getTenLoai());

        return db.update("LoaiSach", values, "maLoai = ?", new String[]{String.valueOf(obj.getMaLoai())});
    }

    public int delete(String id){
        ContentValues values = new ContentValues();

        return db.delete("LoaiSach", "maLoai = ?", new String[]{String.valueOf(id)});
    }
    @SuppressLint("Range")
    public List<LoaiSach> getData(String sql, String...selectionArgs){
        List<LoaiSach> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);

        while (c.moveToNext()){
            LoaiSach obj = new LoaiSach();
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            obj.setTenLoai(c.getString(c.getColumnIndex("tenLoai")));
            list.add(obj);
        }
        return list;
    }

    public List<LoaiSach> getAll(){
        String sql = "select * from LoaiSach";
        return getData(sql);
    }

    public LoaiSach getID(String id){
        String sql = "select * from LoaiSach where maLoai=?";
        List<LoaiSach> list = getData(sql, id);
        return list.get(0);
    }
}
