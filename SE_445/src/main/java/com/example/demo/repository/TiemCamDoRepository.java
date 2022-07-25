package com.example.demo.repository;

import com.example.demo.models.TiemCamDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiemCamDoRepository extends JpaRepository<TiemCamDo , String> {
}
