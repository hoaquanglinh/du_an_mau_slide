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
import com.example.du_an_mau_slide.model.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach> {
    Context context;
    ArrayList<LoaiSach> list;

    TextView tvMaLoai, tvTenLoai;

    public LoaiSachSpinnerAdapter(@NonNull Context context, ArrayList<LoaiSach> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_spn_loai_sach, null);
        }

        LoaiSach loaiSach = list.get(position);
        if (loaiSach != null){
            tvMaLoai = convertView.findViewById(R.id.tvMaLoaiSachSp);
            tvMaLoai.setText(loaiSach.getMaLoai() + ". ");

            tvTenLoai = convertView.findViewById(R.id.tvTenLoaiSachSp);
            tvTenLoai.setText(loaiSach.getTenLoai());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_spn_loai_sach, null);
        }

        LoaiSach loaiSach = list.get(position);
        if (loaiSach != null){
            tvMaLoai = convertView.findViewById(R.id.tvMaLoaiSachSp);
            tvMaLoai.setText(loaiSach.getMaLoai() + ". ");

            tvTenLoai = convertView.findViewById(R.id.tvTenLoaiSachSp);
            tvTenLoai.setText(loaiSach.getTenLoai());
        }

        return convertView;
    }
}
