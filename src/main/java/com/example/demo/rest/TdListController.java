package com.example.demo.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TdListDto;
import com.example.demo.persistence.domain.TdList;
import com.example.demo.service.TdListService;

@RestController
@CrossOrigin
@RequestMapping("/tdList")
public class TdListController {

	private TdListService service;

	public TdListController(TdListService service) {
		super();
		this.service = service;
	}

	// create method
	@PostMapping("/create")
	public ResponseEntity<TdListDto> create(@RequestBody TdList tdList) {
		TdListDto created = this.service.create(tdList);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
		// http status code 201 = created
	}

	// read all method
	@GetMapping("/read")
	public ResponseEntity<List<TdListDto>> read() {
		return ResponseEntity.ok(this.service.readAll());
	}

	// read one
	@GetMapping("/read/{id}")
	public ResponseEntity<TdListDto> readOne(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.readOne(id));
	}

	// update
	@PutMapping("/update/{id}")
	public ResponseEntity<TdListDto> update(@PathVariable Long id, @RequestBody TdListDto tdListDto) {
		return new ResponseEntity<>(this.service.update(tdListDto, id), HttpStatus.ACCEPTED);
	}

	// delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<TdListDto> delete(@PathVariable Long id) {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				// if successfully deleted returns nothing
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		// else record not found
	}
}
