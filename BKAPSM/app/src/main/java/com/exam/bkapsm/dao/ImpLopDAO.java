package com.exam.bkapsm.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.exam.bkapsm.db.DatabaseHelper;
import com.exam.bkapsm.entities.Lop;

import java.util.ArrayList;
import java.util.List;

public class ImpLopDAO implements ILopDAO{
    private Context mCtx;
    private SQLiteDatabase mDB;

    public ImpLopDAO(Context mCtx) {
        this.mCtx = mCtx;
        DatabaseHelper helper = new DatabaseHelper(this.mCtx);
        mDB = helper.getWritableDatabase();
    }
    @Override
    public List<Lop> getAllLop() {
        String sql = "SELECT * FROM TblLop;";
        Cursor c = mDB.rawQuery(sql, null);
        List<Lop> lst = new ArrayList<>();
        while (c.moveToNext()) {
            @SuppressLint("Range") int id = c.getInt(c.getColumnIndex("id"));
            @SuppressLint("Range") String tenlop = c.getString(c.getColumnIndex("tenlop"));

            Lop l = new Lop(id,tenlop);
            lst.add(l);
        }
        return lst;
    }
}
