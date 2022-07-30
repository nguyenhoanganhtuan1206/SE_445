package com.example.service;

import com.example.models.KhachHang;
import com.example.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    /* Get all */
    public List<? extends Object> findAll() {
        return this.khachHangRepository.findAll();
    }

    public KhachHang save(KhachHang khachHang) {
        return this.khachHangRepository.save(khachHang);
    }

    public KhachHang findById(String id) {
        return this.khachHangRepository.findById(id).orElse(null);
    }
}
