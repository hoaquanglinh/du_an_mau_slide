package com.example.du_an_mau_slide.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    static final String dbName = "PNLIB2";
    static final int dbVersion = 6;

    public DBHelper(Context context){
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableThuThu = "create table ThuThu (" +
                "maTT text primary key, " +
                "hoTen text not null, " +
                "matKhau text not null)";
        db.execSQL(createTableThuThu);
        db.execSQL("insert into ThuThu values('admin', 'Linh', 'admin')");

        String createTableThanhVien = "create table ThanhVien (" +
                "maTV integer primary key autoincrement, " +
                "hoTen text not null, " +
                "namSinh text not null)";
        db.execSQL(createTableThanhVien);
        db.execSQL("insert into ThanhVien values(1, 'Chiến', 2004), (2, 'Phúc', 2004)");

        String createTableLoaiSach = "create table LoaiSach (" +
                "maLoai integer primary key autoincrement," +
                "tenLoai text not null)";
        db.execSQL(createTableLoaiSach);
        db.execSQL("insert into LoaiSach values(1, 'CNTT'), (2, 'TKDH'), (3, 'Truyện'), (4, 'Lịch sử')");

        String createTableSach = "create table Sach (" +
                "maSach integer primary key autoincrement," +
                "tenSach text not null," +
                "giaThue integer not null," +
                "maLoai integer references LoaiSach(maLoai))";
        db.execSQL(createTableSach);
        db.execSQL("insert into Sach values(1, 'Java', 10000, 1), (2, 'Vẽ', 3000, 4), (3, 'Doraemon', 10000, 3), (4, 'Conana', 10000, 3)");

        String createTablePhieuMuon =" create table PhieuMuon(" +
                "maPM integer primary key autoincrement," +
                "maTT text references ThuThu(maTT)," +
                "maTV integer references ThanhVien(maTV)," +
                "maSach integer references Sach(maSach)," +
                "tienThue integer not null," +
                "ngay date not null," +
                "traSach integer not null)";
        db.execSQL(createTablePhieuMuon);
        db.execSQL("insert into PhieuMuon values(1, 'admin', 1, 2, 10000, '2023/10/10', 1), (2, 'admin', 2, 1, 10000, '2023/10/10', 0), (3, 'admin', 1, 1, 10000, '2023/10/10', 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion!= oldVersion){
            // Xóa bảng cũ (nếu cần)
            db.execSQL("DROP TABLE IF EXISTS PhieuMuon");
            db.execSQL("DROP TABLE IF EXISTS LoaiSach");
            db.execSQL("DROP TABLE IF EXISTS Sach");
            db.execSQL("DROP TABLE IF EXISTS ThuThu");
            db.execSQL("DROP TABLE IF EXISTS ThanhVien");

            // Tạo lại các bảng đã sửa đổi
            onCreate(db);
        }
    }
}
