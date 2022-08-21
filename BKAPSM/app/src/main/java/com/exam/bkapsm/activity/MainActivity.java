package com.exam.bkapsm.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import com.exam.bkapsm.R;
import com.exam.bkapsm.adapter.AdapterSinhVien;
import com.exam.bkapsm.dao.ISinhVienDAO;
import com.exam.bkapsm.dao.ImpSinhVienDAO;
import com.exam.bkapsm.dialog.DialogSearch;
import com.exam.bkapsm.entities.SinhVien;

import java.text.SimpleDateFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogSearch.ICallback {
    private List<SinhVien> mLst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadSinhVien();

    }
    public void LoadSinhVien() {
        ISinhVienDAO dao = new ImpSinhVienDAO(this);
        mLst = dao.getAll();

        AdapterSinhVien adapterStudent = new AdapterSinhVien(this, mLst);
        ListView listView = findViewById(R.id.lstDanhSach);
        listView.setAdapter(adapterStudent);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long l) {
                Intent intent = new Intent(MainActivity.this, SinhVienActivity.class);
                SinhVien s = mLst.get(pos);
                intent.putExtra("idb", s.getId());
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menuDangKy:
                Intent intent = new Intent(this, AddStudentActivity.class);
                startActivity(intent);
                break;
            case R.id.menuTim:
                DialogSearch ds = new DialogSearch(MainActivity.this);
                ds.show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        LoadSinhVien();
        super.onResume();
    }



    @Override
    public void search(String prdName) {
        searchSinhvien(prdName);
    }

    private void searchSinhvien(String search) {
        ISinhVienDAO dao = new ImpSinhVienDAO(this);
        mLst = dao.searchAll(search);
        AdapterSinhVien adaptersv = new AdapterSinhVien(this, mLst);
        ListView listView = findViewById(R.id.lstDanhSach);
        listView.setAdapter(adaptersv);
    }

}