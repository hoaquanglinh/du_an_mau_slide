package com.example.du_an_mau_slide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an_mau_slide.dao.ThuThuDAO;

public class ManHinhDangNhap extends AppCompatActivity {
    EditText edUserName, edPassWord;
    Button btnLogin, btnCanCel;
    CheckBox chkRememberPass;
    String strUser, strPass;
    ThuThuDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_dang_nhap);

        edUserName = findViewById(R.id.edtUserName);
        edPassWord = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCanCel = findViewById(R.id.btnCancel);
        chkRememberPass = findViewById(R.id.chkRemember);
        dao = new ThuThuDAO(this);

        SharedPreferences pref = getSharedPreferences("user_file", MODE_PRIVATE);
        String user = pref.getString("username", "");
        String pass = pref.getString("password", "");
        Boolean rem = pref.getBoolean("remember", false);

        edPassWord.setText(pass);
        edUserName.setText(user);
        chkRememberPass.setChecked(rem);

        btnCanCel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPassWord.setText("");
                edUserName.setText("");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }
    public void remember(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("user_file", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();

        if(!status){
            // xoa tinh trang luu trc do
            edit.clear();
        }else{
            edit.putString("username", u);
            edit.putString("password", p);
            edit.putBoolean("remember", status);
        }
        // luu lai toan bo
        edit.commit();
    }
    public void checkLogin(){
        strUser = edUserName.getText().toString();
        strPass = edPassWord.getText().toString();
        if(strPass.isEmpty()||strPass.isEmpty()){
            Toast.makeText(this, "Tên đăng nhập và mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        }else{
            if (dao.checkLogin(strUser, strPass) > 0){
                Toast.makeText(this, "Login thành công", Toast.LENGTH_SHORT).show();
                remember(strUser, strPass, chkRememberPass.isChecked());
                Intent i = new Intent(getApplicationContext(), ManHinhChinh.class);
                i.putExtra("user", strUser);
                startActivity(i);
                finish();
            }else {
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
            }
        }
    }
}