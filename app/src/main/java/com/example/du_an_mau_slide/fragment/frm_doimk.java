package com.example.du_an_mau_slide.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.du_an_mau_slide.ManHinhDangNhap;
import com.example.du_an_mau_slide.R;
import com.example.du_an_mau_slide.dao.ThuThuDAO;
import com.example.du_an_mau_slide.model.ThuThu;
import com.google.android.material.textfield.TextInputEditText;

public class frm_doimk extends Fragment {
    TextInputEditText edPassOld, edPass, edRePass;
    Button btnsave, btnCancel;
    ThuThuDAO dao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_frm_doimk, container, false);
        edPassOld = view.findViewById(R.id.edPassOld);
        edPass = view.findViewById(R.id.edPassChange);
        edRePass = view.findViewById(R.id.edRePassChange);
        btnsave = view.findViewById(R.id.btnSaveUserChange);
        btnCancel = view.findViewById(R.id.btnCacelUserChange);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPassOld.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("user_file", Context.MODE_PRIVATE);
                String user = pref.getString("username", "");
                if(validate()>0){
                    dao = new ThuThuDAO(getContext());
                    ThuThu thuThu = (ThuThu) dao.getID(user);
                    thuThu.setMatKhau(edPass.getText().toString());
                    if(dao.update(thuThu) > 0){
                        Toast.makeText(getContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), ManHinhDangNhap.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getContext(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

    public int validate(){
        int check = 1;
        if(edPassOld.getText().length()==0 || edPass.getText().length() == 0 || edRePass.getText().length() ==0){
            Toast.makeText(getContext(), "Bạn phải điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else{
            SharedPreferences pref = getActivity().getSharedPreferences("user_file", Context.MODE_PRIVATE);
            String passOld = pref.getString("password", "");
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if(!passOld.equalsIgnoreCase(edPassOld.getText().toString())){
                Toast.makeText(getContext(), "Sai mật khẩu cũ", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(rePass)){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}