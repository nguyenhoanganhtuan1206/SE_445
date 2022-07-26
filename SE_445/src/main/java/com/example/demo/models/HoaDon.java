package com.example.demo.models;

import com.example.demo.service.ChiTietHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class HoaDon {
    @Id
    private String hoa_don_id;

    private String ten_hang_hoa;

    private Double tong_tien;

    private boolean trang_thai;

    private String ngay_cam;

    private String ngay_het_han;

    @ManyToOne
    @JoinColumn(name = "tiem_cam_do_id", nullable = false)
    private TiemCamDo tiemCamDo;

    @ManyToOne
    @JoinColumn(name = "khach_hang_id", nullable = false)
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "danh_muc_id", nullable = false)
    private DanhMuc danhMuc;

    @OneToMany(mappedBy = "hoaDon")
    private Set<ChiTietHoaDon> chiTietHoaDons;

    /* Calculate total price for order */
    public Double totalPrice(List<ChiTietHoaDon> chiTietHoaDons , String hoa_don_id) {
        double sum = 0;
        for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDons) {
            if (chiTietHoaDon.getHoaDon().getHoa_don_id().equals(hoa_don_id)) {
                sum += chiTietHoaDon.getSo_luong() * chiTietHoaDon.getGia_tien_san_pham();
            }
        }
        return sum;
    }

    @Override
    public String toString() {
        return  hoa_don_id +  " , " + ten_hang_hoa +  " , " + tong_tien + " , " + trang_thai +
                " , " + ngay_cam +
                " , " + ngay_het_han +
                " , " + tiemCamDo.getMa_tiem() +
                " , " + khachHang.getKhach_hang_id() +
                " , " + danhMuc.getMa_danh_muc();
    }

    public HoaDon() {
    }

    public HoaDon(String hoa_don_id, String ten_hang_hoa, Double tong_tien, boolean trang_thai, String ngay_cam, String ngay_het_han, TiemCamDo tiemCamDo, KhachHang khachHang, DanhMuc danhMuc) {
        this.hoa_don_id = hoa_don_id;
        this.ten_hang_hoa = ten_hang_hoa;
        this.tong_tien = tong_tien;
        this.trang_thai = trang_thai;
        this.ngay_cam = ngay_cam;
        this.ngay_het_han = ngay_het_han;
        this.tiemCamDo = tiemCamDo;
        this.khachHang = khachHang;
        this.danhMuc = danhMuc;
    }

    public Set<ChiTietHoaDon> getChiTietHoaDons() {
        return chiTietHoaDons;
    }

    public void setChiTietHoaDons(Set<ChiTietHoaDon> chiTietHoaDons) {
        this.chiTietHoaDons = chiTietHoaDons;
    }

    public String getHoa_don_id() {
        return hoa_don_id;
    }

    public void setHoa_don_id(String hoa_don_id) {
        this.hoa_don_id = hoa_don_id;
    }

    public String getTen_hang_hoa() {
        return ten_hang_hoa;
    }

    public void setTen_hang_hoa(String ten_hang_hoa) {
        this.ten_hang_hoa = ten_hang_hoa;
    }

    public Double getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(Double tong_tien) {
        this.tong_tien = tong_tien;
    }

    public boolean isTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(boolean trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getNgay_cam() {
        return ngay_cam;
    }

    public void setNgay_cam(String ngay_cam) {
        this.ngay_cam = ngay_cam;
    }

    public String getNgay_het_han() {
        return ngay_het_han;
    }

    public void setNgay_het_han(String ngay_het_han) {
        this.ngay_het_han = ngay_het_han;
    }

    public TiemCamDo getTiemCamDo() {
        return tiemCamDo;
    }

    public void setTiemCamDo(TiemCamDo tiemCamDo) {
        this.tiemCamDo = tiemCamDo;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }
}
