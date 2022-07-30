package com.example.service;

import com.example.models.DanhMuc;
import com.example.repository.DanhMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanhMucService {
    @Autowired
    private DanhMucRepository danhMucRepository;

    public List<? extends Object> findAll() {
        return this.danhMucRepository.findAll();
    }

    public DanhMuc save(DanhMuc danhMuc) {
        return this.danhMucRepository.save(danhMuc);
    }

    public DanhMuc findById(String id) {
        return this.danhMucRepository.findById(id).orElse(null);
    }
}
