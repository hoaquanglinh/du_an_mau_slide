package com.example.du_an_mau_slide.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.du_an_mau_slide.R;
import com.example.du_an_mau_slide.adapter.TopAdapter;
import com.example.du_an_mau_slide.dao.PhieuMuonDAO;
import com.example.du_an_mau_slide.dao.ThongKeDao;
import com.example.du_an_mau_slide.model.PhieuMuon;
import com.example.du_an_mau_slide.model.Top;

import java.util.ArrayList;

public class frm_top10 extends Fragment {
    ListView lv;
    ArrayList<Top> list;
    TopAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frm_top10, container, false);
        lv = view.findViewById(R.id.lvTop10);
        ThongKeDao thongKeDao = new ThongKeDao(getContext());
        list = (ArrayList<Top>) thongKeDao.getTop();
        adapter = new TopAdapter(getActivity(), list, this);
        lv.setAdapter(adapter);
        return view;
    }
}