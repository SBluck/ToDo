package com.example.demo.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.persistence.domain.TdTask;

@Repository
public interface TdTaskRepo extends JpaRepository<TdTask, Long> {
	// allows CRUD implementation and custom sql queries
}
