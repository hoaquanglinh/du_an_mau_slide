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
import com.example.du_an_mau_slide.model.Sach;
import com.example.du_an_mau_slide.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVien> {
    Context context;
    ArrayList<ThanhVien> list;
    TextView tvMaTv, tvTenTV;
    public ThanhVienSpinnerAdapter(@NonNull Context context, ArrayList<ThanhVien> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_spinner_thanh_vien, null);
        }

        ThanhVien thanhVien = list.get(position);
        if (thanhVien != null){
            tvMaTv = convertView.findViewById(R.id.tvMaTVSp);
            tvMaTv.setText(thanhVien.getMaTV() + ". ");

            tvTenTV = convertView.findViewById(R.id.tvTenTVSp);
            tvTenTV.setText(thanhVien.getHoTen());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_spinner_thanh_vien, null);
        }

        ThanhVien thanhVien = list.get(position);
        if (thanhVien != null){
            tvMaTv = convertView.findViewById(R.id.tvMaTVSp);
            tvMaTv.setText(thanhVien.getMaTV() + ". ");

            tvTenTV = convertView.findViewById(R.id.tvTenTVSp);
            tvTenTV.setText(thanhVien.getHoTen());
        }

        return convertView;
    }

}
