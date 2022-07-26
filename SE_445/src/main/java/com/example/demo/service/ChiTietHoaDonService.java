package com.example.demo.service;

import com.example.demo.models.ChiTietHoaDon;
import com.example.demo.repository.ChiTietHoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiTietHoaDonService {
    @Autowired
    private ChiTietHoaDonRepository chiTietHoaDonRepository;

    public List<ChiTietHoaDon> findAll() {
        return this.chiTietHoaDonRepository.findAll();
    }

    public ChiTietHoaDon save(ChiTietHoaDon chiTietHoaDon) {
        return this.chiTietHoaDonRepository.save(chiTietHoaDon);
    }

    public ChiTietHoaDon findById(String id) {
        return this.chiTietHoaDonRepository.findById(id).orElse(null);
    }
}
