package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TdTaskDto;
import com.example.demo.exceptions.TdTaskNotFoundException;
import com.example.demo.persistence.domain.TdTask;
import com.example.demo.persistence.repo.TdTaskRepo;
import com.example.demo.util.SpringBeanUtil;

@Service
public class TdTaskService {

	// where business and CRUD logic happen

	private TdTaskRepo repo;

	// makes object mapping easy
	private ModelMapper mapper;

	private TdTaskDto mapToDTO(TdTask tdTask) {
		return this.mapper.map(tdTask, TdTaskDto.class);
	}

	@Autowired
	public TdTaskService(TdTaskRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	// create method - return DTO
	public TdTaskDto create(TdTask tdTask) {
		return this.mapToDTO(this.repo.save(tdTask));
	}

	// read all method - return DTO list
	public List<TdTaskDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	// read one method - return DTO
	public TdTaskDto readOne(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow(TdTaskNotFoundException::new));
	}

	// update method
	public TdTaskDto update(TdTaskDto tdTaskDto, Long id) {
		TdTask toUpdate = this.repo.findById(id).orElseThrow(TdTaskNotFoundException::new);
		toUpdate.setTodo(tdTaskDto.getTodo());
		SpringBeanUtil.mergeNotNull(tdTaskDto, toUpdate);
		return this.mapToDTO(this.repo.save(toUpdate));
	}

	// delete method
	public boolean delete(Long id) {
		this.repo.deleteById(id); // true if deleted
		return !this.repo.existsById(id); // true
	}

}