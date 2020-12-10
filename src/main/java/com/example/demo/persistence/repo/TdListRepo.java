package com.example.demo.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TdListRepo extends JpaRepository<TdListRepo, Long> {
	// allows CRUD implementation and custom sql queries
}
