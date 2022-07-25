package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class TiemCamDo {
    @Id
    private String ma_tiem;

    private String ten_tiem;

    private String dia_chi;

    @OneToMany(mappedBy = "tiemCamDo")
    private Set<HoaDon> hoaDons;

    public TiemCamDo() {}

    public TiemCamDo(String ma_tiem, String ten_tiem, String dia_chi, Set<HoaDon> hoaDons) {
        this.ma_tiem = ma_tiem;
        this.ten_tiem = ten_tiem;
        this.dia_chi = dia_chi;
        this.hoaDons = hoaDons;
    }

    public TiemCamDo(String ma_tiem, String ten_tiem, String dia_chi) {
        this.ma_tiem = ma_tiem;
        this.ten_tiem = ten_tiem;
        this.dia_chi = dia_chi;
    }


    @Override
    public String toString() {
        return ma_tiem + " , " + ten_tiem + " , " + dia_chi;
    }

    public String getMa_tiem() {
        return ma_tiem;
    }

    public void setMa_tiem(String ma_tiem) {
        this.ma_tiem = ma_tiem;
    }

    public String getTen_tiem() {
        return ten_tiem;
    }

    public void setTen_tiem(String ten_tiem) {
        this.ten_tiem = ten_tiem;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public Set<HoaDon> getHoaDons() {
        return hoaDons;
    }

    public void setHoaDons(Set<HoaDon> hoaDons) {
        this.hoaDons = hoaDons;
    }
}
