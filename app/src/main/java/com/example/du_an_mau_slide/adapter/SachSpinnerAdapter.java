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
import com.example.du_an_mau_slide.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachSpinnerAdapter extends ArrayAdapter<Sach> {
    Context context;
    ArrayList<Sach> list;

    TextView tvMaSach, tvTenSach;

    public SachSpinnerAdapter(@NonNull Context context, ArrayList<Sach> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_spinner_sach, null);
        }

        Sach sach = list.get(position);
        if (sach != null){
            tvMaSach = convertView.findViewById(R.id.tvMaSachSp);
            tvMaSach.setText(sach.getMaSach() + ". ");

            tvTenSach = convertView.findViewById(R.id.tvTenSachSp);
            tvTenSach.setText(sach.getTenSach());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_spinner_sach, null);
        }

        Sach sach = list.get(position);
        if (sach != null){
            tvMaSach = convertView.findViewById(R.id.tvMaSachSp);
            tvMaSach.setText(sach.getMaSach() + ". ");

            tvTenSach = convertView.findViewById(R.id.tvTenSachSp);
            tvTenSach.setText(sach.getTenSach());
        }

        return convertView;
    }
}
