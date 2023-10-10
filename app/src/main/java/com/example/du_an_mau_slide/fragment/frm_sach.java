package com.example.du_an_mau_slide.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.du_an_mau_slide.R;
import com.example.du_an_mau_slide.adapter.LoaiSachSpinnerAdapter;
import com.example.du_an_mau_slide.adapter.SachAdapter;
import com.example.du_an_mau_slide.dao.LoaiSachDAO;
import com.example.du_an_mau_slide.dao.SachDAO;
import com.example.du_an_mau_slide.model.LoaiSach;
import com.example.du_an_mau_slide.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.nio.channels.AlreadyBoundException;
import java.util.ArrayList;

public class frm_sach extends Fragment {
    ListView lvSach;
    SachDAO dao;
    SachAdapter adapter;
    Sach item;
    ArrayList<Sach> list;
    Dialog dialog;
    EditText edMaSach, edTenSach, edGiaThue;
    Spinner spinner;
    Button btnSave, btnCancel;

    LoaiSachSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiSach> listLS;
    LoaiSachDAO loaiSachDAO;
    LoaiSach loaiSach;
    int maLoaiSach, position;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frm_sach, container, false);
        lvSach = view.findViewById(R.id.lvSach);
        FloatingActionButton fabSach = view.findViewById(R.id.fabSach);
        dao = new SachDAO(getContext());
        capNhatLV();

        fabSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });

        lvSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });

        return view;
    }

    void capNhatLV(){
        list = (ArrayList<Sach>) dao.getAll();
        adapter = new SachAdapter(getContext(), this, list);
        lvSach.setAdapter(adapter);
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
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.item_sach);

        edMaSach = dialog.findViewById(R.id.edMaSach);
        edTenSach = dialog.findViewById(R.id.edTenSach);
        edGiaThue = dialog.findViewById(R.id.edGiaThue);
        spinner = dialog.findViewById(R.id.spLoaiSach);
        btnSave = dialog.findViewById(R.id.btnSaveSach);
        btnCancel = dialog.findViewById(R.id.btnCacelSaveSach);

        listLS = new ArrayList<>();
        loaiSachDAO = new LoaiSachDAO(context);
        listLS = (ArrayList<LoaiSach>) loaiSachDAO.getAll();

        spinnerAdapter = new LoaiSachSpinnerAdapter(context, listLS);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = listLS.get(position).getMaLoai();
                Toast.makeText(context, "Chọn" + listLS.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        edMaSach.setEnabled(false);

        //
        if (type != 0){
            edMaSach.setText(String.valueOf(item.getMaSach()));
            edTenSach.setText(item.getTenSach());
            edGiaThue.setText(String.valueOf(item.getGiaThue()));

            for(int i = 0; i < listLS.size(); i++){
                if(item.getMaLoai() == (listLS.get(i).getMaLoai())){
                    position = i;
                }
            }
            Log.i("demo", "posSach"+position);
            spinner.setSelection(position);
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new Sach();

                item.setTenSach(edTenSach.getText().toString());

                int giaThue = Integer.parseInt(edGiaThue.getText().toString());
                item.setGiaThue(giaThue);

                item.setMaLoai(maLoaiSach);

                if (validate() > 0){
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        item.setMaSach(Integer.parseInt(edMaSach.getText().toString()));
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
        if (edTenSach.getText().length() == 0 || edGiaThue.getText().length() == 0){
            Toast.makeText(getContext(), "Phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}