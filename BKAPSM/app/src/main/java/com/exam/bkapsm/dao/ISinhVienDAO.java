package com.exam.bkapsm.dao;

import com.exam.bkapsm.entities.SinhVien;

import java.util.List;

public interface ISinhVienDAO {
    public List<SinhVien> getAll();

    public boolean insert(SinhVien s);

    public SinhVien selectById(int id);

    public boolean update(SinhVien s);

    public boolean delete(int id);

    public List<SinhVien> searchAll(String name);
}
