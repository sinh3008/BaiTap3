package com.exam.bkapsm.entities;

public class Lop {
    private int id;
    private String tenlop;

    public Lop() {
    }

    public Lop(int id, String tenlop) {
        this.id = id;
        this.tenlop = tenlop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenlop() {
        return tenlop;
    }

    public void setTenlop(String tenlop) {
        this.tenlop = tenlop;
    }

    @Override
    public String toString() {
        return this.tenlop;
    }
}
