package com.example.demo.service;

import com.example.demo.models.TinhThanh;
import com.example.demo.repository.TinhThanhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TinhThanhService {
    @Autowired
    private TinhThanhRepository tinhThanhRepository;

    public List<? extends Object> findAll() {
        return this.tinhThanhRepository.findAll();
    }

    public TinhThanh save(TinhThanh tinhThanh) {
        return this.tinhThanhRepository.save(tinhThanh);
    }

    public TinhThanh findById(Long id) {
        return this.tinhThanhRepository.findById(id).orElse(null);
    }
}
