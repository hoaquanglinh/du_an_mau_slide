package com.example.du_an_mau_slide.fragment;

import static java.time.MonthDay.now;

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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_mau_slide.R;
import com.example.du_an_mau_slide.adapter.PhieuMuonAdapter;
import com.example.du_an_mau_slide.adapter.SachSpinnerAdapter;
import com.example.du_an_mau_slide.adapter.ThanhVienSpinnerAdapter;
import com.example.du_an_mau_slide.dao.PhieuMuonDAO;
import com.example.du_an_mau_slide.dao.SachDAO;
import com.example.du_an_mau_slide.dao.ThanhVienDAO;
import com.example.du_an_mau_slide.model.PhieuMuon;
import com.example.du_an_mau_slide.model.Sach;
import com.example.du_an_mau_slide.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class frm_phieu_muon extends Fragment {
    ListView lvPM;
    ArrayList<PhieuMuon> list;
    PhieuMuonDAO dao;
    PhieuMuonAdapter adapter;
    PhieuMuon pm;

    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaPM;
    TextView tvNgay, tvTien;
    CheckBox chkTrangThai;
    Button btnSave, btnCancel;
    Spinner spTV, spSach;

    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;

    SachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;
    Sach sach;
    int maSach, tienThue;
    int positionTV, positionSach;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View conertView = inflater.inflate(R.layout.fragment_frm_phieu_muon, container, false);
        lvPM = conertView.findViewById(R.id.lvPM);
        fab = conertView.findViewById(R.id.fabPM);
        dao = new PhieuMuonDAO(getActivity());

        capNhatLV();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });

        lvPM.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                pm = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        return conertView;
    }
    void capNhatLV(){
        list = (ArrayList<PhieuMuon>) dao.getAll();
        adapter = new PhieuMuonAdapter(getContext(), this, list);
        lvPM.setAdapter(adapter);
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

    public  void openDialog(Context context, int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.item_phieu_muon);

        edMaPM = dialog.findViewById(R.id.edMaPM);
        spTV = dialog.findViewById(R.id.spnThanhVien);
        spSach = dialog.findViewById(R.id.spnSach);
        tvNgay = dialog.findViewById(R.id.tvNgay);
        tvTien = dialog.findViewById(R.id.tvTien);
        chkTrangThai = dialog.findViewById(R.id.chkTrangThai);
        btnCancel = dialog.findViewById(R.id.btnCancelPM);
        btnSave = dialog.findViewById(R.id.btnSavePM);

        sachDAO = new SachDAO(context);
        listSach = new ArrayList<>();
        listSach = (ArrayList<Sach>) sachDAO.getAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(context, listSach);
        spSach.setAdapter(sachSpinnerAdapter);

        thanhVienDAO = new ThanhVienDAO(context);
        listThanhVien = new ArrayList<>();
        listThanhVien = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context, listThanhVien);
        spTV.setAdapter(thanhVienSpinnerAdapter);

        // Lay Sach, ThanhVien
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).getMaSach();
                tienThue = listSach.get(position).getGiaThue();
                tvTien.setText(String.valueOf(tienThue));
                Toast.makeText(context, "Chọn "+ listSach.get(position).getTenSach(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maThanhVien = listThanhVien.get(position).getMaTV();
                Toast.makeText(context, "Chọn "+ listThanhVien.get(position).getHoTen(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edMaPM.setEnabled(false);

        if(type != 0){
            edMaPM.setText(String.valueOf(pm.getMaPM()));

            for (int i = 0; i<listSach.size(); i++){
                if(pm.getMaSach() == listSach.get(i).getMaSach()){
                    positionSach = i;
                }
            }
            spSach.setSelection(positionSach);

            for (int i = 0; i<listThanhVien.size(); i++){
                if(pm.getMaTV() == listThanhVien.get(i).getMaTV()){
                    positionTV = i;
                }
            }
            spTV.setSelection(positionTV);
            tvNgay.setText(sdf.format(pm.getNgay()));
            tvTien.setText(String.valueOf(pm.getTienThue()));
            if(pm.getTraSach() == 1){
                chkTrangThai.setChecked(true);
            }else{
                chkTrangThai.setChecked(false);
            }
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
                pm = new PhieuMuon();
                pm.setMaTV(maThanhVien);
                pm.setMaSach(maSach);
                pm.setNgay(new Date());
                pm.setTienThue(tienThue);
                if (chkTrangThai.isChecked()){
                    pm.setTraSach(1);
                }else{
                    pm.setTraSach(0);
                }

                if(validate() > 0){
                    if (type == 0){
                        if (dao.insert(pm) > 0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        pm.setMaPM(Integer.parseInt(edMaPM.getText().toString()));
                        if (dao.update(pm) > 0){
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

        return check;
    }
}