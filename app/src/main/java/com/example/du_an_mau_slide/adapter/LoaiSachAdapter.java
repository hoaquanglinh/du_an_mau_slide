package com.example.du_an_mau_slide.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.du_an_mau_slide.R;
import com.example.du_an_mau_slide.fragment.frm_loai_sach;
import com.example.du_an_mau_slide.model.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    Context context;

    ArrayList<LoaiSach> list;
    frm_loai_sach fragment;

    TextView tvMaLoai, tvTenLoai;
    Button btnDel;

    public LoaiSachAdapter(@NonNull Context context, ArrayList<LoaiSach> list, frm_loai_sach fragment) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.layout_loai_sach, null);
        }
        LoaiSach item = list.get(position);
        if (item != null){
            tvMaLoai = convertView.findViewById(R.id.tvMaLoai);
            tvTenLoai = convertView.findViewById(R.id.tvTenLoai);

            tvMaLoai.setText(String.valueOf(item.getMaLoai()));
            tvTenLoai.setText(item.getTenLoai());

            btnDel = convertView.findViewById(R.id.btnDelLS);
            btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.xoa(String.valueOf(item.getMaLoai()));
                }
            });
        }

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

        return convertView;
    }
}
