package com.example.bookshop.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshop.Adapter.CHUDE_APDAPTER;
import com.example.bookshop.Model.CHUDESACH;
import com.example.bookshop.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    // khai bao
    ViewFlipper viewFlipper;
    RecyclerView rcvChuDeSach, Sach;
    ArrayList<CHUDESACH> chudedata;
    CHUDE_APDAPTER adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // anh xa
        viewFlipper = view.findViewById(R.id.viewFlipper);
        rcvChuDeSach = view.findViewById(R.id.rcvDanhMucSach);
        Sach = view.findViewById(R.id.rcvSach);

        // Khởi tạo chudedata ở đây
        chudedata = new ArrayList<>();
        chudedata.add(new CHUDESACH("1", "Truyen tranh", ""));
        chudedata.add(new CHUDESACH("2", "Tieu thuyet", ""));
        chudedata.add(new CHUDESACH("3", "Sach giao khoa", ""));
        chudedata.add(new CHUDESACH("4", "Khoa hoc", ""));

        adapter = new CHUDE_APDAPTER(getContext(), chudedata);
        rcvChuDeSach.setAdapter(adapter);
        rcvChuDeSach.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }
}