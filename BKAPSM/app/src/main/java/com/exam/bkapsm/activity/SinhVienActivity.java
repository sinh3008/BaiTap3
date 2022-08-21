package com.exam.bkapsm.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.exam.bkapsm.R;
import com.exam.bkapsm.dao.ILopDAO;
import com.exam.bkapsm.dao.ISinhVienDAO;
import com.exam.bkapsm.dao.ImpLopDAO;
import com.exam.bkapsm.dao.ImpSinhVienDAO;
import com.exam.bkapsm.entities.Lop;
import com.exam.bkapsm.entities.SinhVien;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SinhVienActivity extends AppCompatActivity {
    private int mIdS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinh_vien);
        loadSpiner();

        EditText txtNgaysinh = findViewById(R.id.txtNgaysinh);
        txtNgaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cld = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar cld = Calendar.getInstance();
                        cld.set(year, month, dayOfMonth);
                        txtNgaysinh.setText(fmt.format(cld.getTime()));
                    }
                };
                DatePickerDialog dialog = new DatePickerDialog(SinhVienActivity.this, listener, cld.get(Calendar.YEAR), cld.get(Calendar.MONTH), cld.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        Button btnThem = findViewById(R.id.btnThem);
        btnThem.setOnClickListener(listenerAdd);
        Button btnSua = findViewById(R.id.btnSua);
        btnSua.setOnClickListener(listenerUpdate);
        Button btnXoa = findViewById(R.id.btnXoa);
        btnXoa.setOnClickListener(listenerDel);

        reloadData();
    }


    public void loadSpiner() {
        ILopDAO dao = new ImpLopDAO(this);
        List<Lop> lst = dao.getAllLop();

        ArrayAdapter<Lop> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lst);

        Spinner spLop = findViewById(R.id.spLop);
        spLop.setAdapter(adapter);

    }

    public void reloadData() {


        mIdS = getIntent().getExtras().getInt("idb");

        ISinhVienDAO dao = new ImpSinhVienDAO(this);
        SinhVien s = dao.selectById(mIdS);

        EditText Tensv = findViewById(R.id.txtTensv);
        EditText Email = findViewById(R.id.txtEmail);
        EditText Ngaysinh = findViewById(R.id.txtNgaysinh);
        EditText Sdt = findViewById(R.id.txtSdt);
        Spinner spinner = findViewById(R.id.spLop);

        RadioButton radNam = findViewById(R.id.radNam);
        RadioButton radNu = findViewById(R.id.radNu);
        Tensv.setText(s.getTensv());
        if (s.isGioitinh() == true) {
            radNam.setChecked(true);
        } else {
            radNu.setChecked(true);
        }
        Email.setText(s.getEmail());
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Ngaysinh.setText(fmt.format(s.getNgaysinh()));
        Sdt.setText(s.getSdt());

        Button them = findViewById(R.id.btnThem);
        Button sua = findViewById(R.id.btnSua);
        Button xoa = findViewById(R.id.btnXoa);

        them.setEnabled(true);
        sua.setEnabled(true);
        xoa.setEnabled(true);
    }


    private View.OnClickListener listenerAdd = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Spinner spLop = findViewById(R.id.spLop);
            String Tensv = ((EditText) findViewById(R.id.txtTensv)).getText().toString();
            Lop lop = (Lop) spLop.getSelectedItem();
            RadioButton radNam = findViewById(R.id.radNam);
            RadioButton radNu = findViewById(R.id.radNu);
            String Email = ((EditText) findViewById(R.id.txtEmail)).getText().toString();
            EditText Ngaysinh = findViewById(R.id.txtNgaysinh);
            String Sdt = ((EditText) findViewById(R.id.txtSdt)).getText().toString();
            Boolean gioiTinh = true;
            if (radNam.isChecked()) {
                gioiTinh = true;
            } else if (radNu.isChecked()) {
                gioiTinh = false;
            }
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date ngaySinh = null;
            try {
                ngaySinh = fmt.parse(Ngaysinh.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            SinhVien s = new SinhVien(Tensv, lop.getId(), gioiTinh, Email, ngaySinh, Sdt);

            ISinhVienDAO dao = new ImpSinhVienDAO(SinhVienActivity.this);

            boolean isOk = dao.insert(s);
            if (isOk) {
                Toast.makeText(SinhVienActivity.this, "Thêm mới thành công !", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SinhVienActivity.this, "Thất bại !", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private View.OnClickListener listenerUpdate = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Spinner spLop = findViewById(R.id.spLop);
            String Tensv = ((EditText) findViewById(R.id.txtTensv)).getText().toString();
            Lop lop = (Lop) spLop.getSelectedItem();
            RadioButton radNam = findViewById(R.id.radNam);
            RadioButton radNu = findViewById(R.id.radNu);
            String Email = ((EditText) findViewById(R.id.txtEmail)).getText().toString();
            EditText Ngaysinh = findViewById(R.id.txtNgaysinh);
            String Sdt = ((EditText) findViewById(R.id.txtSdt)).getText().toString();

            Boolean gioiTinh = true;
            if (radNam.isChecked()) {
                gioiTinh = true;
            } else if (radNu.isChecked()) {
                gioiTinh = false;
            }

            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date ngaySinh = null;
            try {
                ngaySinh = fmt.parse(Ngaysinh.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SinhVien s = new SinhVien(mIdS, Tensv, lop.getId(), gioiTinh, Email, ngaySinh, Sdt);
            ISinhVienDAO dao = new ImpSinhVienDAO(SinhVienActivity.this);
            boolean isOk = dao.update(s);
            if (isOk) {
                Toast.makeText(SinhVienActivity.this, "Đã sửa thành công !", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SinhVienActivity.this, "Thất bại !", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private View.OnClickListener listenerDel = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mIdS = getIntent().getExtras().getInt("idb");
            ISinhVienDAO dao = new ImpSinhVienDAO(SinhVienActivity.this);
            SinhVien s = dao.selectById(mIdS);
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(SinhVienActivity.this);
            builder.setMessage("Bạn có chắc chắn muốn xóa dữ liệu sinh viên " + s.getTensv() + " ?")
                    .setCancelable(false)
                    .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            boolean isOk = dao.delete(s.getId());
                            if (isOk) {
                                Toast.makeText(SinhVienActivity.this, "Xóa thành công !", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SinhVienActivity.this, "Thất bại !", Toast.LENGTH_SHORT).show();
                            }
                            Intent intent = new Intent(SinhVienActivity.this, MainActivity.class);
                            intent.putExtra("idb", s.getId());
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(SinhVienActivity.this, "Huỷ xoá thành công", Toast.LENGTH_SHORT).show();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    };


}