package com.example.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class DanhMuc {
    @Id
    private String ma_danh_muc;

    private String ten_danh_muc;

    @OneToMany(mappedBy = "danhMuc")
    private Set<HoaDon> hoaDons;

    public DanhMuc() {}

    public DanhMuc(String ma_danh_muc, String ten_danh_muc) {
        this.ma_danh_muc = ma_danh_muc;
        this.ten_danh_muc = ten_danh_muc;
    }

    @Override
    public String toString() {
        return ma_danh_muc +  " , " + ten_danh_muc;
    }

    public String getMa_danh_muc() {
        return ma_danh_muc;
    }

    public void setMa_danh_muc(String ma_danh_muc) {
        this.ma_danh_muc = ma_danh_muc;
    }

    public String getTen_danh_muc() {
        return ten_danh_muc;
    }

    public void setTen_danh_muc(String ten_danh_muc) {
        this.ten_danh_muc = ten_danh_muc;
    }

    public Set<HoaDon> getHoaDons() {
        return hoaDons;
    }

    public void setHoaDons(Set<HoaDon> hoaDons) {
        this.hoaDons = hoaDons;
    }
}
