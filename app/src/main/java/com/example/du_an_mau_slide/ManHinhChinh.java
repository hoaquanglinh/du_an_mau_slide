package com.example.du_an_mau_slide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.du_an_mau_slide.dao.ThuThuDAO;
import com.example.du_an_mau_slide.fragment.frm_doanh_thu;
import com.example.du_an_mau_slide.fragment.frm_doimk;
import com.example.du_an_mau_slide.fragment.frm_loai_sach;
import com.example.du_an_mau_slide.fragment.frm_phieu_muon;
import com.example.du_an_mau_slide.fragment.frm_sach;
import com.example.du_an_mau_slide.fragment.frm_tao_tai_khoan;
import com.example.du_an_mau_slide.fragment.frm_thanh_vien;
import com.example.du_an_mau_slide.fragment.frm_top10;
import com.example.du_an_mau_slide.model.ThuThu;
import com.google.android.material.navigation.NavigationView;

public class ManHinhChinh extends AppCompatActivity {
    Fragment fragment;
    ThuThuDAO thuThuDAO;
    View mHeaderView;
    TextView tvUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);

        Toolbar toolbar = findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                ManHinhChinh.this, drawerLayout, toolbar,
                R.string.open,
                R.string.close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);

        Intent i = getIntent();
        String user = i.getStringExtra("user");

        if (user.equalsIgnoreCase("admin")) {
            navigationView.getMenu().findItem(R.id.taotaikhoan).setVisible(true);
        }

        mHeaderView = navigationView.getHeaderView(0);
        tvUser = mHeaderView.findViewById(R.id.tvUser);
        thuThuDAO = new ThuThuDAO(this);
        ThuThu thuThu = thuThuDAO.getID(user);
        String username = thuThu.getHoTen();
        tvUser.setText("Welcome " + username + "!");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.quanlyphieumuon){
                    fragment = new frm_phieu_muon();
                }else if(item.getItemId() == R.id.quantlyloaisach){
                    fragment = new frm_loai_sach();
                }else if(item.getItemId() == R.id.quanlysach){
                    fragment = new frm_sach();
                }else if(item.getItemId() == R.id.quanlythanhvien){
                    fragment = new frm_thanh_vien();
                }else if(item.getItemId() == R.id.top10){
                    fragment = new frm_top10();
                }else if(item.getItemId() == R.id.doanhthu){
                    fragment = new frm_doanh_thu();
                }else if(item.getItemId() == R.id.taotaikhoan){
                    fragment = new frm_tao_tai_khoan();
                }else if(item.getItemId() == R.id.doimatkhau){
                    fragment = new frm_doimk();
                }else{
                    Intent intent = new Intent(ManHinhChinh.this, ManHinhDangNhap.class);
                    startActivity(intent);
                }

                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout, fragment)
                            .commit();
                    drawerLayout.close();
                }
                return true;
            }
        });
    }
}