package com.exam.bkapsm.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.exam.bkapsm.R;
import com.exam.bkapsm.activity.SinhVienActivity;
import com.exam.bkapsm.entities.SinhVien;

import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterSinhVien extends ArrayAdapter<SinhVien> {
    private Context mCtx;
    private List<SinhVien> mLst;
    public AdapterSinhVien(@NonNull Context context, @NonNull List<SinhVien> objects) {
        super(context, R.layout.item_sinhvien,objects);
        this.mCtx = context;
        this.mLst = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        if (item == null){
            item = LayoutInflater.from(mCtx).inflate(R.layout.item_sinhvien, null);
        }

        TextView idlop = item.findViewById(R.id.lbLop);
        TextView lbTensv = item.findViewById(R.id.lbTensv);
        ImageView imgGioitinh = item.findViewById(R.id.imgGioitinh);
        TextView lbNgaysinh = item.findViewById(R.id.lbNgaysinh);
        TextView lbEmail = item.findViewById(R.id.lbEmail);
        TextView lbSdt = item.findViewById(R.id.lbSdt);
        ListView listView = item.findViewById(R.id.lstDanhSach);

        SinhVien s = mLst.get(position);

        lbTensv.setText(s.getTensv());
        lbEmail.setText(s.getEmail());
        lbSdt.setText(s.getSdt());
        idlop.setText(s.getTenlop());

        if (s.isGioitinh() == true){
            imgGioitinh.setImageResource(R.drawable.icon_male_64);
        }else {
            imgGioitinh.setImageResource(R.drawable.icon_female_64);
        }

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        lbNgaysinh.setText(fmt.format(s.getNgaysinh()));

//        listView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mCtx, SinhVienActivity.class);
//                intent.putExtra("idS", s.getId());
//                mCtx.startActivity(intent);
//            }
//        });

        return item;
    }
}
