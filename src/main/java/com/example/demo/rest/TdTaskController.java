package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.dto.TdTaskDto;
import com.example.demo.persistence.domain.TdTask;
import com.example.demo.service.TdTaskService;

@RestController
@CrossOrigin
@RequestMapping("/tdTask")
public class TdTaskController {

	private TdTaskService service;

	@Autowired
	public TdTaskController(TdTaskService service) {
		super();
		this.service = service;
	}

	// create method
	@PostMapping("/create")
	public ResponseEntity<TdTaskDto> create(@RequestBody TdTask tdTask) {

		TdTaskDto created = this.service.create(tdTask);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
		// http status code 201 = created
	}

	// read all method
	@GetMapping("/read")
	public ResponseEntity<List<TdTaskDto>> read() {
		return ResponseEntity.ok(this.service.readAll());
	}

	// read one
	@GetMapping("/read/{id}")
	public ResponseEntity<TdTaskDto> readOne(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.readOne(id));
	}

	// update
	@PutMapping("/update/{id}")
	public ResponseEntity<TdTaskDto> update(@PathVariable Long id, @RequestBody TdTaskDto tdTaskDto) {
		return new ResponseEntity<>(this.service.update(tdTaskDto, id), HttpStatus.ACCEPTED);
	}

	// delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<TdTaskDto> delete(@PathVariable Long id) {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				// if successfully deleted returns nothing
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		// (else) record not found
	}
}
