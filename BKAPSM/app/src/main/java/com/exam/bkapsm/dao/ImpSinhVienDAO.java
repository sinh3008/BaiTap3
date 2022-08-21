package com.exam.bkapsm.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.exam.bkapsm.db.DatabaseHelper;
import com.exam.bkapsm.entities.Lop;
import com.exam.bkapsm.entities.SinhVien;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImpSinhVienDAO implements ISinhVienDAO {
    private Context mCtx;
    private SQLiteDatabase mDB;

    public ImpSinhVienDAO(Context mCtx) {
        this.mCtx = mCtx;
        DatabaseHelper helper = new DatabaseHelper(this.mCtx);
        mDB = helper.getWritableDatabase();
    }

    @Override
    public List<SinhVien> getAll() {
        String sql = "SELECT s.*, l.tenlop FROM TblSinhVien s JOIN TblLop l ON l.id = s.idlop;";
        Cursor c = mDB.rawQuery(sql, null);
        List<SinhVien> lst = new ArrayList<>();
        while (c.moveToNext()) {
            @SuppressLint("Range") int id = c.getInt(c.getColumnIndex("id"));
            @SuppressLint("Range") String tensv = c.getString(c.getColumnIndex("tensv"));
            @SuppressLint("Range") int idlop = c.getInt(c.getColumnIndex("idlop"));
            @SuppressLint("Range") String tenlop = c.getString(c.getColumnIndex("tenlop"));
            @SuppressLint("Range") boolean gioitinh = c.getInt(c.getColumnIndex("gioitinh")) > 0;
            @SuppressLint("Range") String email = c.getString(c.getColumnIndex("email"));
            @SuppressLint("Range") String ngaysinh = c.getString(c.getColumnIndex("ngaysinh"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date ngaySinh = null;
            try {
                ngaySinh = sdf.parse(ngaysinh);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            @SuppressLint("Range") String sdt = c.getString(c.getColumnIndex("sdt"));

            SinhVien s = new SinhVien(id, tensv, idlop, gioitinh, email, ngaySinh, sdt, tenlop);
            lst.add(s);
        }
        return lst;
    }

    @Override
    public boolean insert(SinhVien s) {
        ContentValues cv = new ContentValues();
        cv.put("tensv", s.getTensv());
        cv.put("idlop", s.getIdlop());
        cv.put("gioitinh", s.isGioitinh());
        cv.put("email", s.getEmail());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        cv.put("ngaysinh", fmt.format(s.getNgaysinh()));
        cv.put("sdt", s.getSdt());

        long id = mDB.insert("TblSinhVien", null, cv);
        if (id > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public SinhVien selectById(int id) {
        String sql = "SELECT s.*, l.tenlop FROM TblSinhVien s JOIN TblLop l ON l.id = s.idlop WHERE s.id = ?;";
        Cursor c = mDB.rawQuery(sql, new String[]{String.valueOf(id)});
        List<SinhVien> lst = new ArrayList<>();
        while (c.moveToNext()) {
            @SuppressLint("Range") int idS = c.getInt(c.getColumnIndex("id"));
            @SuppressLint("Range") String tensv = c.getString(c.getColumnIndex("tensv"));
            @SuppressLint("Range") int idlop = c.getInt(c.getColumnIndex("idlop"));
            @SuppressLint("Range") String tenlop = c.getString(c.getColumnIndex("tenlop"));
            @SuppressLint("Range") boolean gioitinh = c.getInt(c.getColumnIndex("gioitinh")) > 0;
            @SuppressLint("Range") String email = c.getString(c.getColumnIndex("email"));
            @SuppressLint("Range") String ngaysinh = c.getString(c.getColumnIndex("ngaysinh"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date ngaySinh = null;
            try {
                ngaySinh = sdf.parse(ngaysinh);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            @SuppressLint("Range") String sdt = c.getString(c.getColumnIndex("sdt"));

            SinhVien s = new SinhVien(id, tensv, idlop, gioitinh, email, ngaySinh, sdt, tenlop);
            return s;
        }
        return null;
    }

    @Override
    public boolean update(SinhVien s) {
        ContentValues cv = new ContentValues();
        cv.put("tensv", s.getTensv());
        cv.put("idlop", s.getIdlop());
        cv.put("gioitinh", s.isGioitinh());
        cv.put("email", s.getEmail());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        cv.put("ngaysinh", fmt.format(s.getNgaysinh()));
        cv.put("sdt", s.getSdt());

        int row = mDB.update("TblSinhVien", cv, "id = ?", new String[]{String.valueOf(s.getId())});
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        int row = mDB.delete("TblSinhVien", "id = ?", new String[]{String.valueOf(id)});
        if (row > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<SinhVien> searchAll(String name) {
        String sql = "SELECT s.*, l.tenlop FROM TblSinhVien s JOIN TblLop l ON l.id = s.idlop WHERE s.tensv LIKE ?";
        String param = "%" + name + "%";
        String args[] = {param};
        Cursor c = mDB.rawQuery(sql, args);
        List<SinhVien> lst = new ArrayList<>();
        while (c.moveToNext()) {
            @SuppressLint("Range") int id = c.getInt(c.getColumnIndex("id"));
            @SuppressLint("Range") String tensv = c.getString(c.getColumnIndex("tensv"));
            @SuppressLint("Range") int idlop = c.getInt(c.getColumnIndex("idlop"));
            @SuppressLint("Range") String tenlop = c.getString(c.getColumnIndex("tenlop"));
            @SuppressLint("Range") boolean gioitinh = c.getInt(c.getColumnIndex("gioitinh")) > 0;
            @SuppressLint("Range") String email = c.getString(c.getColumnIndex("email"));
            @SuppressLint("Range") String ngaysinh = c.getString(c.getColumnIndex("ngaysinh"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date ngaySinh = null;
            try {
                ngaySinh = sdf.parse(ngaysinh);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            @SuppressLint("Range") String sdt = c.getString(c.getColumnIndex("sdt"));

            SinhVien s = new SinhVien(id, tensv, idlop, gioitinh, email, ngaySinh, sdt, tenlop);
            lst.add(s);
        }
        return lst;
    }
}
