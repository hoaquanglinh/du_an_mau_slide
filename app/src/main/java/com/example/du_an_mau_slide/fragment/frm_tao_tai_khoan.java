package com.example.du_an_mau_slide.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an_mau_slide.R;
import com.example.du_an_mau_slide.dao.ThuThuDAO;
import com.example.du_an_mau_slide.model.ThuThu;

import java.util.ArrayList;

public class frm_tao_tai_khoan extends Fragment {
    ThuThuDAO dao;
    ArrayList<ThuThu> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frm_tao_tai_khoan, container, false);

        EditText edmatt = view.findViewById(R.id.edMaTT);
        EditText edhoten = view.findViewById(R.id.edHoTenTT);
        EditText edmatkhau = view.findViewById(R.id.edPasswordTT);
        EditText edrematkhau = view.findViewById(R.id.edRePasswordTT);

        view.findViewById(R.id.btnSaveTT).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matt = edmatt.getText().toString();
                String hoten = edhoten.getText().toString();
                String matkhau = edmatkhau.getText().toString();
                String rematkhau = edrematkhau.getText().toString();
                if (matt.isEmpty() || hoten.isEmpty() || matkhau.isEmpty() || rematkhau.isEmpty()){
                    Toast.makeText(getContext(), "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    if(!matkhau.equals(rematkhau)){
                        Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    }else{
                        ThuThu tt = new ThuThu(matt, hoten, matkhau);

                        dao = new ThuThuDAO(getContext());
                        dao.insert(tt);

                        list = new ArrayList<>();
                        list.clear();
                        list.addAll(dao.getAll());

                        Toast.makeText(getContext(), "Thêm mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        view.findViewById(R.id.btnCancelSaveTT).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edmatt.setText("");
                edhoten.setText("");
                edmatkhau.setText("");
                edrematkhau.setText("");
            }
        });

        return view;
    }
}