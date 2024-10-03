package com.example.bookshop.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookshop.Adapter.CHUDE_APDAPTER;
import com.example.bookshop.Adapter.SACH_ADAPTER;
import com.example.bookshop.Model.CHUDESACH;
import com.example.bookshop.Model.SACH;
import com.example.bookshop.R;
import com.example.bookshop.SERVER;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    // khai bao
    ViewFlipper viewFlipper;
    RecyclerView rcvChuDeSach, rcvSach; // Sửa tên biến Sach thành rcvSach để dễ hiểu hơn
    ArrayList<CHUDESACH> chudedata;
    CHUDE_APDAPTER chudeAdapter;

    // sach
    ArrayList<SACH> sachdata;
    SACH_ADAPTER sachAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Khởi tạo View và RecyclerView
        viewFlipper = view.findViewById(R.id.viewFlipper);  // Khởi tạo viewFlipper
        rcvChuDeSach = view.findViewById(R.id.rcvChuDeSach);
        rcvSach = view.findViewById(R.id.rcvSach);

        // Khởi tạo Adapter và LayoutManager cho RecyclerView
        chudedata = new ArrayList<>();
        chudeAdapter = new CHUDE_APDAPTER(getContext(), chudedata);
        rcvChuDeSach.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcvChuDeSach.setAdapter(chudeAdapter);

        sachdata = new ArrayList<>();
        sachAdapter = new SACH_ADAPTER(getContext(), sachdata);
        rcvSach.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rcvSach.setAdapter(sachAdapter);

        // Gọi hàm load dữ liệu
        loadSlide();

        if (chudedata.size() == 0) {
            loadChuDe();
        }

        if (sachdata.size() == 0) {
            loadSach();
        }
    }

    private void loadSach() {
        // B3:
        sachdata.clear();
        Response.Listener<String> thanhcong = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject sach = jsonArray.getJSONObject(i);
                        sachdata.add(new SACH(
                                sach.getString("Masach"),
                                sach.getString("Tensach"),
                                sach.getString("Mota"),
                                sach.getString("Hinhminhhoa"),
                                sach.getString("Machude"),
                                sach.getString("Ngaycapnhat"),
                                sach.getInt("Dongia"),
                                sach.getInt("Soluongban")
                        ));
                    }

                } catch (JSONException e) {
                    Toast.makeText(getContext(), "LOI"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    throw new RuntimeException(e);
                }

            }};
        Response.ErrorListener thatbai= new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "thất bại" + error, Toast.LENGTH_SHORT).show();
            }
        };

        // B1: Tạo request trong Volley
        //kiêểu Json mảng array dùng nếu dùng để lấy nhiều đối tượng
        StringRequest stringRequest = new StringRequest(SERVER.laysach_url, thanhcong, thatbai);
        // B2: Dùng request với Volley
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }



    private void loadSlide() {
        // B3:
        Response.Listener<String> thanhcong = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] mangFile = response.split("-"); // Phân tách các file từ chuỗi response

                for (String filename : mangFile) {
                    ImageView imageView = new ImageView(getContext());
                    Picasso.get().load(SERVER.slide_url + filename).into(imageView);
                    // Căn chỉnh chiều rộng và chiều cao của slide
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    viewFlipper.addView(imageView);
                }
            }
        };

        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("View Flipper", "Lỗi: " + error.toString());
                Toast.makeText(getContext(), "Lỗi: " + error.toString(), Toast.LENGTH_LONG).show();
            }
        };

        // B1: Tạo request trong Volley
        StringRequest stringRequest = new StringRequest(SERVER.laySlide_url, thanhcong, thatbai);

        // B2: Tạo requestQueue và thêm request vào hàng đợi
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

        viewFlipper.setFlipInterval(2500);
        viewFlipper.setAutoStart(true);
    }

    void loadChuDe() {
        chudedata.clear();

        // B3: Khởi tạo request vào Volley
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        chudedata.add(new CHUDESACH(
                                object.getString("MaChuDe"),
                                object.getString("TenChuDe"),
                                object.getString("HinhChuDe")
                        ));
                    } catch (JSONException e) {
                        Toast.makeText(getContext(), "Lỗi lấy dữ liệu chủ đề sách: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                chudeAdapter.notifyDataSetChanged();
            }
        };

        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Thất bại: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        // B1: Tạo request trong Volley
        JsonArrayRequest request = new JsonArrayRequest(SERVER.laychude_url, thanhcong, thatbai);

        // B2: Tạo requestQueue và thêm request vào hàng đợi
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }
}
