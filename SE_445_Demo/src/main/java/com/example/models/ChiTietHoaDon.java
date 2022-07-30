package com.example.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ChiTietHoaDon {
    @Id
    private String chi_tiet_hoa_don_id;

    private int so_luong;

    private String ten_san_pham;

    private double gia_tien_san_pham;

    @ManyToOne
    @JoinColumn(name = "hoa_don_id", nullable = false)
    private HoaDon hoaDon;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(String chi_tiet_hoa_don_id, int so_luong, String ten_san_pham, double gia_tien_san_pham, HoaDon hoaDon) {
        this.chi_tiet_hoa_don_id = chi_tiet_hoa_don_id;
        this.so_luong = so_luong;
        this.ten_san_pham = ten_san_pham;
        this.gia_tien_san_pham = gia_tien_san_pham;
        this.hoaDon = hoaDon;
    }

    @Override
    public String toString() {
        return chi_tiet_hoa_don_id + " , " + so_luong + " , " + ten_san_pham + " , " + gia_tien_san_pham + " , " + hoaDon.getHoa_don_id();
    }

    public String getChi_tiet_hoa_don_id() {
        return chi_tiet_hoa_don_id;
    }

    public void setChi_tiet_hoa_don_id(String chi_tiet_hoa_don_id) {
        this.chi_tiet_hoa_don_id = chi_tiet_hoa_don_id;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public String getTen_san_pham() {
        return ten_san_pham;
    }

    public void setTen_san_pham(String ten_san_pham) {
        this.ten_san_pham = ten_san_pham;
    }

    public double getGia_tien_san_pham() {
        return gia_tien_san_pham;
    }

    public void setGia_tien_san_pham(double gia_tien_san_pham) {
        this.gia_tien_san_pham = gia_tien_san_pham;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }
}
