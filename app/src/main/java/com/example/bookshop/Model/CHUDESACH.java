package com.example.bookshop.Model;

import java.io.Serializable;

public class CHUDESACH implements Serializable {
    public String MaChuDe,TenChuDe,HinhChuDe;

    public CHUDESACH(String maChuDe, String tenChuDe, String hinhChuDe) {
        MaChuDe = maChuDe;
        TenChuDe = tenChuDe;
        HinhChuDe = hinhChuDe;
    }
}
