package com.example.du_an_mau_slide.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.du_an_mau_slide.R;
import com.example.du_an_mau_slide.dao.ThanhVienDAO;
import com.example.du_an_mau_slide.fragment.frm_thanh_vien;
import com.example.du_an_mau_slide.model.ThanhVien;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    Context context;
    frm_thanh_vien fragment;
    ArrayList<ThanhVien> lists;

    TextView tvMaTV, tvTenTV, tvNamSinh;
    Button btnDel;

    public ThanhVienAdapter(@NonNull Context context, frm_thanh_vien fragment, ArrayList<ThanhVien> lists) {
        super(context, 0, lists);
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.layout_thanhvien, null);
        }
        ThanhVien item = lists.get(position);
        if (item != null){
            tvMaTV = convertView.findViewById(R.id.tvMaTV);
            tvTenTV = convertView.findViewById(R.id.tvTenTV);
            tvNamSinh = convertView.findViewById(R.id.tvNamSinh);
            btnDel = convertView.findViewById(R.id.btnDelTV);

            tvMaTV.setText(String.valueOf(item.getMaTV()));
            tvTenTV.setText(item.getHoTen());
            tvNamSinh.setText(item.getNamSinh());

            btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // xoa
                    fragment.xoa(String.valueOf(item.getMaTV()));
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
