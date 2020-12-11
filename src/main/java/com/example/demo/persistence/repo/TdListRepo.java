package com.example.demo.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.persistence.domain.TdList;

@Repository
public interface TdListRepo extends JpaRepository<TdList, Long> {
	// allows CRUD implementation and custom sql queries
}
