package com.example.bookshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshop.Model.CHUDESACH;
import com.example.bookshop.R;

import java.util.ArrayList;

// quy tac tao ADAPTER
// + 1:  Tao class CHUDEVIEWHOLDER truoc, tao contructor , khai bao, va anh xa
// + 2:  extends RecyclerView.ViewHolder<CHUDEVIEWHOLDER> , implement method, truyen bien "context", arrayList...
// + 3:  tao contructor cua 2 bien trong CHUDE_ADAPTER,...
// + 4:  ... xem clip di, chu ko nho =))

public class CHUDE_APDAPTER extends RecyclerView.Adapter<CHUDEVIEWHOLDER> {
    Context context;
    ArrayList<CHUDESACH> chudedata;

    public CHUDE_APDAPTER(Context context, ArrayList<CHUDESACH> chudedata) {
        this.context = context;
        this.chudedata = chudedata;
    }

    @NonNull
    @Override
    public CHUDEVIEWHOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chudesach_layout, parent, false);
        return new CHUDEVIEWHOLDER(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CHUDEVIEWHOLDER holder, int position) {
        CHUDESACH chudesach = chudedata.get(position);
        holder.tvTenchude.setText(chudesach.TenChuDe);
        // hinh se load tu server bang Picasso
    }

    @Override
    public int getItemCount() {
        return chudedata.size();
    }
}

class CHUDEVIEWHOLDER extends RecyclerView.ViewHolder {

    ImageView imgChuDe;
    TextView tvTenchude;

    public CHUDEVIEWHOLDER(@NonNull View itemView) {
        super(itemView);

        imgChuDe = itemView.findViewById(R.id.imgChuDe);
        tvTenchude = itemView.findViewById(R.id.tvTenchude);
    }
}
