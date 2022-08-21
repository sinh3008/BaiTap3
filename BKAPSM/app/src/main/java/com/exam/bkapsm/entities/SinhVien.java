package com.exam.bkapsm.entities;

import java.util.Date;

public class SinhVien {
    private int id;
    private String tensv;
    private int idlop;
    private boolean gioitinh;
    private String email;
    private Date ngaysinh;
    private String sdt;
    private String tenlop;

    public SinhVien() {
    }

    public SinhVien(int id, String tensv, int idlop, boolean gioitinh, String email, Date ngaysinh, String sdt, String tenlop) {
        this.id = id;
        this.tensv = tensv;
        this.idlop = idlop;
        this.gioitinh = gioitinh;
        this.email = email;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.tenlop = tenlop;
    }

    public SinhVien(String tensv, int idlop, boolean gioitinh, String email, Date ngaysinh, String sdt) {
        this.tensv = tensv;
        this.idlop = idlop;
        this.gioitinh = gioitinh;
        this.email = email;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
    }

    public SinhVien(int id, String tensv, int idlop, boolean gioitinh, String email, Date ngaysinh, String sdt) {
        this.id = id;
        this.tensv = tensv;
        this.idlop = idlop;
        this.gioitinh = gioitinh;
        this.email = email;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public int getIdlop() {
        return idlop;
    }

    public void setIdlop(int idlop) {
        this.idlop = idlop;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTenlop() {
        return tenlop;
    }

    public void setTenlop(String tenlop) {
        this.tenlop = tenlop;
    }

}
