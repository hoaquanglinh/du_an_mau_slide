package com.example.du_an_mau_slide.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.du_an_mau_slide.R;
import com.example.du_an_mau_slide.dao.LoaiSachDAO;
import com.example.du_an_mau_slide.fragment.frm_sach;
import com.example.du_an_mau_slide.model.LoaiSach;
import com.example.du_an_mau_slide.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachAdapter extends ArrayAdapter<Sach> {
    Context context;
    frm_sach fragment;
    ArrayList<Sach> list;
    ArrayList<LoaiSach> listLS;
    TextView tvMaSach, tvTenSach, tvGiaThue, tvLoai;

    public SachAdapter(@NonNull Context context, frm_sach fragment, ArrayList<Sach> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.layout_sach, null);
        }

        Sach item = list.get(position);

        if(item != null){
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(item.getMaLoai()));

            tvMaSach = convertView.findViewById(R.id.tvMaSach);
            tvMaSach.setText(String.valueOf(item.getMaSach()));

            tvTenSach = convertView.findViewById(R.id.tvTenSach);
            tvTenSach.setText(item.getTenSach());

            tvGiaThue = convertView.findViewById(R.id.tvGiaThue);
            tvGiaThue.setText(String.valueOf(item.getGiaThue()));

            tvLoai = convertView.findViewById(R.id.tvLoai);
            tvLoai.setText(loaiSach.getTenLoai());

            convertView.findViewById(R.id.btnDelSach).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.xoa(String.valueOf(item.getMaSach()));
                }
            });

            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });
        }
        return convertView;
    }
}
