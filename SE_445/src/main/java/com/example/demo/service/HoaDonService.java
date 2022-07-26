package com.example.demo.service;

import com.example.demo.models.HoaDon;
import com.example.demo.repository.HoaDonRepository;
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
