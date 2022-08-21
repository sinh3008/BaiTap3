package com.exam.bkapsm.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "bkapsvien.sqlite";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE TblLop (\n" +
                "    id     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    tenlop TEXT\n" +
                ");\n";
        db.execSQL(sql);
        sql = "INSERT INTO TblLop (tenlop) VALUES ('Java');";
        db.execSQL(sql);
        sql = "INSERT INTO TblLop (tenlop) VALUES ('PHP');";
        db.execSQL(sql);
        sql = "INSERT INTO TblLop (tenlop) VALUES ('Android');";
        db.execSQL(sql);
        sql = "INSERT INTO TblLop (tenlop) VALUES ('C#');";
        db.execSQL(sql);
        sql = "INSERT INTO TblLop (tenlop) VALUES ('Java Web');";
        db.execSQL(sql);

        sql = "CREATE TABLE TblSinhVien (\n" +
                "    id       INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    tensv    TEXT,\n" +
                "    idlop    INTEGER REFERENCES TblLop (id),\n" +
                "    gioitinh BOOLEAN DEFAULT (1),\n" +
                "    email    TEXT,\n" +
                "    ngaysinh DATE,\n" +
                "    sdt      TEXT\n" +
                ");\n";
        db.execSQL(sql);
        sql = "INSERT INTO TblSinhVien(tensv,idlop,gioitinh,email,ngaysinh,sdt) VALUES ('Lê Xuân Sinh',3,1,'Sinhle2001@gmail.com','2001-01-20','0989478073');";
        db.execSQL(sql);
        sql = "INSERT INTO TblSinhVien(tensv,idlop,gioitinh,email,ngaysinh,sdt) VALUES ('Nguyen Van A',1,1,'nguyenvana@gmail.com','2001-09-03','09686969333');";
        db.execSQL(sql);
        sql = "INSERT INTO TblSinhVien(tensv,idlop,gioitinh,email,ngaysinh,sdt) VALUES ('Nguyen Van B',2,0,'nguyenvanb@gmail.com','2001-09-03','09686969333');";
        db.execSQL(sql);
        sql = "INSERT INTO TblSinhVien(tensv,idlop,gioitinh,email,ngaysinh,sdt) VALUES ('Nguyen Van C',3,0,'Nguyenvanc@gmail.com','2001-09-03','09686969333');";
        db.execSQL(sql);
        sql = "INSERT INTO TblSinhVien(tensv,idlop,gioitinh,email,ngaysinh,sdt) VALUES ('Nguyen Van D',4,1,'Nguyenvand@gmail.com','2001-09-03','09686969333');";
        db.execSQL(sql);
        sql = "INSERT INTO TblSinhVien(tensv,idlop,gioitinh,email,ngaysinh,sdt) VALUES ('Nguyen Van E',5,0,'Nguyenvane@gmail.com','2001-09-03','09686969333');";
        db.execSQL(sql);
        sql = "INSERT INTO TblSinhVien(tensv,idlop,gioitinh,email,ngaysinh,sdt) VALUES ('Nguyen Van G',3,1,'Nguyenvang@gmail.com','2001-09-03','09686969333');";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
