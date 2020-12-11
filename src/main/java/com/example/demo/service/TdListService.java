package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TdListDto;
import com.example.demo.exceptions.TdListNotFoundException;
import com.example.demo.persistence.domain.TdList;
import com.example.demo.persistence.repo.TdListRepo;
import com.example.demo.util.SpringBeanUtil;

@Service
public class TdListService {
	// where business and CRUD logic happen

	private TdListRepo repo;

	// makes object mapping easy
	private ModelMapper mapper;

	private TdListDto mapToDTO(TdList tdList) {
		return this.mapper.map(tdList, TdListDto.class);
	}

	@Autowired
	public TdListService(TdListRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	// create method - return DTO
	public TdListDto create(TdList tdList) {
		return this.mapToDTO(this.repo.save(tdList));
	}

	// read all method - return DTO list
	public List<TdListDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	// read one method - return DTO
	public TdListDto readOne(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow(TdListNotFoundException::new));
	}

	// update method - return DTO
	public TdListDto update(TdListDto tdListDto, Long id) {
		TdList toUpdate = this.repo.findById(id).orElseThrow(TdListNotFoundException::new);
		toUpdate.setName(tdListDto.getName());
		SpringBeanUtil.mergeNotNull(tdListDto, toUpdate);
		return this.mapToDTO(this.repo.save(toUpdate));
	}

	// delete method - return true or false
	public boolean delete(Long id) {
		this.repo.deleteById(id); // true if deleted
		return !this.repo.existsById(id); // true
	}
}
