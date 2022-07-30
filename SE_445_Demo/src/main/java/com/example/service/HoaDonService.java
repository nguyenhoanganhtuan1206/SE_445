package com.example.service;

import com.example.models.HoaDon;
import com.example.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonService {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    public List<? extends Object> findAll() {
        return this.hoaDonRepository.findAll();
    }

    public HoaDon save(HoaDon hoaDon) {
        return this.hoaDonRepository.save(hoaDon);
    }

    public HoaDon findById(String id) {
        return this.hoaDonRepository.findById(id).orElse(null);
    }
}
