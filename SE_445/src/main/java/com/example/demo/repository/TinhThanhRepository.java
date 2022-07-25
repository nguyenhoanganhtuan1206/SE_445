package com.example.demo.repository;

import com.example.demo.models.TinhThanh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TinhThanhRepository extends JpaRepository<TinhThanh , Long> {
}
