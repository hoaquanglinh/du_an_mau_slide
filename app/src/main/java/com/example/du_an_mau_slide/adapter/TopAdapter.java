package com.example.du_an_mau_slide.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.du_an_mau_slide.R;
import com.example.du_an_mau_slide.fragment.frm_top10;
import com.example.du_an_mau_slide.model.Top;

import java.util.ArrayList;
import java.util.List;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    private ArrayList<Top> list;
    frm_top10 fragment;
    TextView tvSach, tvSoLuong;

    public TopAdapter(@NonNull Context context, ArrayList<Top> list, frm_top10 fragment) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_top, null);
        }

        Top top = list.get(position);
        if (top != null){
            tvSach = convertView.findViewById(R.id.tvSach);
            tvSach.setText("Sách: " + top.getTenSach());

            tvSoLuong = convertView.findViewById(R.id.tvSL);
            tvSoLuong.setText("Số lượng: "+top.getSoLuong());
        }
        return convertView;
    }
}
