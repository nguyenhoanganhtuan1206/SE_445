package com.example.demo.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class KhachHang {
    @Id
    private String khach_hang_id;

    private String ho_ten;

    private String ten_lot;

    private String cmnd;

    private String so_dien_thoai;

    private String dia_chi;

    private String ngay_sinh;

    @OneToMany(mappedBy = "khachHang")
    private Set<HoaDon> hoaDons;

    @ManyToOne
    @JoinColumn(name = "tinh_thanh_id", nullable = false)
    private TinhThanh tinhThanh;

    public KhachHang() {
    }

    public KhachHang(String khach_hang_id, String cmnd, String ngay_sinh, String ho_ten, String dia_chi, String ten_lot, String so_dien_thoai , TinhThanh tinhThanh) {
        this.khach_hang_id = khach_hang_id;
        this.ho_ten = ho_ten;
        this.ten_lot = ten_lot;
        this.cmnd = cmnd;
        this.so_dien_thoai = so_dien_thoai;
        this.dia_chi = dia_chi;
        this.ngay_sinh = ngay_sinh;
        this.tinhThanh = tinhThanh;
    }

    @Override
    public String toString() {
        return khach_hang_id + " , " + ho_ten + " , " + ten_lot + " , " + cmnd + " , " + so_dien_thoai + " , " + dia_chi + " , " + ngay_sinh + " , " + tinhThanh;
    }

    public String getKhach_hang_id() {
        return khach_hang_id;
    }

    public void setKhach_hang_id(String khach_hang_id) {
        this.khach_hang_id = khach_hang_id;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public String getTen_lot() {
        return ten_lot;
    }

    public void setTen_lot(String ten_lot) {
        this.ten_lot = ten_lot;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public String getNgay_sinh() {
        return ngay_sinh;
    }

    public void setNgay_sinh(String ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }

    public Set<HoaDon> getHoaDons() {
        return hoaDons;
    }

    public void setHoaDons(Set<HoaDon> hoaDons) {
        this.hoaDons = hoaDons;
    }
}
