package com.example.service;

import com.example.models.TinhThanh;
import com.example.repository.TinhThanhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TinhThanhService {
    @Autowired
    private TinhThanhRepository tinhThanhRepository;

    public List<TinhThanh> findAll() {
        return this.tinhThanhRepository.findAll();
    }

    public TinhThanh save(TinhThanh tinhThanh) {
        return this.tinhThanhRepository.save(tinhThanh);
    }

    public TinhThanh findById(Long id) {
        return this.tinhThanhRepository.findById(id).orElse(null);
    }
}
