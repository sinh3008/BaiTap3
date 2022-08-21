package com.exam.bkapsm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        loadSpiner();
        EditText txtNgaysinh = findViewById(R.id.addtxtNgaysinh);
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
                DatePickerDialog dialog = new DatePickerDialog(AddStudentActivity.this, listener, cld.get(Calendar.YEAR), cld.get(Calendar.MONTH), cld.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });




        Button btnAdd = findViewById(R.id.addbtnThem);
        btnAdd.setOnClickListener(listenerAdd);

    }

    public void loadSpiner() {
        ILopDAO dao = new ImpLopDAO(this);
        List<Lop> lst = dao.getAllLop();

        ArrayAdapter<Lop> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lst);

        Spinner spLop = findViewById(R.id.addspLop);
        spLop.setAdapter(adapter);

    }

    private View.OnClickListener listenerAdd = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Spinner spLop = findViewById(R.id.addspLop);
            String Tensv = ((EditText) findViewById(R.id.addtxtTensv)).getText().toString();
            Lop lop = (Lop) spLop.getSelectedItem();
            RadioButton radNam = findViewById(R.id.addradNam);
            RadioButton radNu = findViewById(R.id.addradNu);
            String Email = ((EditText) findViewById(R.id.addtxtEmail)).getText().toString();
            EditText Ngaysinh = findViewById(R.id.addtxtNgaysinh);
            String Sdt = ((EditText) findViewById(R.id.addtxtSdt)).getText().toString();
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

            ISinhVienDAO dao = new ImpSinhVienDAO(AddStudentActivity.this);

            boolean isOk = dao.insert(s);
            if (isOk) {
                Toast.makeText(AddStudentActivity.this, "Thêm mới thành công !", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AddStudentActivity.this, "Thất bại !", Toast.LENGTH_SHORT).show();
            }
        }
    };
}