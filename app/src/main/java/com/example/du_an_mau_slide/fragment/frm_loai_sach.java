package com.example.du_an_mau_slide.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.du_an_mau_slide.R;
import com.example.du_an_mau_slide.adapter.LoaiSachAdapter;
import com.example.du_an_mau_slide.dao.LoaiSachDAO;
import com.example.du_an_mau_slide.model.LoaiSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class frm_loai_sach extends Fragment {
    ArrayList<LoaiSach> list;
    LoaiSachDAO dao;
    LoaiSachAdapter adapter;
    LoaiSach item;

    FloatingActionButton fabLS;
    ListView lvLoaiSach;

    EditText edMaLoai,edTenLoai;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_frm_loai_sach, container, false);
        lvLoaiSach = view.findViewById(R.id.lvLoaiSach);
        fabLS = view.findViewById(R.id.fabLS);
        dao = new LoaiSachDAO(getContext());
        capNhatLV();
        fabLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getContext(), 0);
            }
        });

        lvLoaiSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getContext(), 1);
                return false;
            }
        });

        return view;
    }

    void capNhatLV(){
        list = (ArrayList<LoaiSach>) dao.getAll();
        adapter = new LoaiSachAdapter(getContext(), list, this);
        lvLoaiSach.setAdapter(adapter);
    }

    public void xoa(String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // goi function Delete
                dao.delete(id);
                // cập nhật listView
                capNhatLV();
                dialog.cancel();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void openDialog(Context context, int type){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.item_loai_sach);

        edMaLoai = dialog.findViewById(R.id.edMaLoai);
        edTenLoai = dialog.findViewById(R.id.edTenLoai);

        edMaLoai.setEnabled(false);

        if(type != 0){
            edMaLoai.setText(String.valueOf(item.getMaLoai()));
            edTenLoai.setText(item.getTenLoai());
        }

        dialog.findViewById(R.id.btnSaveLS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.btnSaveLS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new LoaiSach();
                item.setTenLoai(edTenLoai.getText().toString());

                if (validate() > 0){
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        item.setMaLoai(Integer.parseInt(edMaLoai.getText().toString()));
                        if(dao.update(item) > 0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLV();
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }

    public int validate(){
        int check = 1;
        if(edTenLoai.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}