package com.example.demo.service;

import com.example.demo.models.TiemCamDo;
import com.example.demo.repository.TiemCamDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TiemCamDoService {
    @Autowired
    private TiemCamDoRepository tiemCamDoRepository;

    /* Get all */
    public List<? extends Object> findAll() {
        return this.tiemCamDoRepository.findAll();
    }

    /* Save database */
    public TiemCamDo save(TiemCamDo tiemCamDo) {
        return this.tiemCamDoRepository.save(tiemCamDo);
    }
}
