package com.example.du_an_mau_slide.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.du_an_mau_slide.R;
import com.example.du_an_mau_slide.dao.SachDAO;
import com.example.du_an_mau_slide.dao.ThanhVienDAO;
import com.example.du_an_mau_slide.fragment.frm_phieu_muon;
import com.example.du_an_mau_slide.model.PhieuMuon;
import com.example.du_an_mau_slide.model.Sach;
import com.example.du_an_mau_slide.model.ThanhVien;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    Context context;
    frm_phieu_muon fragment;
    ArrayList<PhieuMuon> list;

    TextView tvMaPm, tvMaTV, tvMaSach, tvNgay, tvTrangThai, tvTienThue;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public PhieuMuonAdapter(@NonNull Context context, frm_phieu_muon fragment, ArrayList<PhieuMuon> list) {
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
            convertView = inflater.inflate(R.layout.layout_phieu_muon, null);
        }

        PhieuMuon pm = list.get(position);
        if (pm != null){
            tvMaPm = convertView.findViewById(R.id.tvMaPM);
            tvMaPm.setText(String.valueOf(pm.getMaPM()));

            ThanhVienDAO thanhVienDAO = new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(pm.getMaTV()));
            tvMaTV = convertView.findViewById(R.id.tvMaTVFK);
            tvMaTV.setText(thanhVien.getHoTen());

            SachDAO sachDAO = new SachDAO(context);
            Sach sach = sachDAO.getID(String.valueOf(pm.getMaSach()));
//            Log.d("PhieuMuonAdapter", "MaSach: " + pm.getMaSach());
            tvMaSach = convertView.findViewById(R.id.tvMaSachFK);
            tvMaSach.setText(String.valueOf(sach.getTenSach()));

            tvTienThue = convertView.findViewById(R.id.tvTienThue);
            tvTienThue.setText(String.valueOf(pm.getTienThue()));

            tvNgay = convertView.findViewById(R.id.tvNgayThue);
            tvNgay.setText(sdf.format(pm.getNgay()));

            tvTrangThai = convertView.findViewById(R.id.tvTrangThai);
            if (pm.getTraSach() == 1){
                tvTrangThai.setTextColor(Color.BLUE);
                tvTrangThai.setText("Đã trả sách");
            }else{
                tvTrangThai.setTextColor(Color.RED);
                tvTrangThai.setText("Chưa trả sách");
            }

            convertView.findViewById(R.id.btnDelPM).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.xoa(String.valueOf(pm.getMaPM()));
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
