package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.model.bo.SeatingChart;

@Repository
public interface SeatingChartRepository extends JpaRepository<SeatingChart, Integer> {
}
