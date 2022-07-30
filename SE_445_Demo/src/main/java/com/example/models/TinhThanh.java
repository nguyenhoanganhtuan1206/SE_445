package com.example.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class TinhThanh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "tinhThanh")
    private Set<KhachHang> khachHangs;

    public TinhThanh() {}

    public TinhThanh(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  id + " , " + name;
    }

    public TinhThanh(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<KhachHang> getKhachHangs() {
        return khachHangs;
    }

    public void setKhachHangs(Set<KhachHang> khachHangs) {
        this.khachHangs = khachHangs;
    }
}
