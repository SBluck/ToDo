package com.example.demo.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.dto.TdTaskDto;
import com.example.demo.persistence.domain.TdTask;
import com.example.demo.service.TdTaskService;

@SpringBootTest
@ActiveProfiles("test")
class TdTaskControllerUnitTest {

	@Autowired
	private TdTaskController controller;

	@MockBean
	private TdTaskService service;

	@Autowired
	private ModelMapper mapper;

	private TdTaskDto mapToDTO(TdTask tdTask) {
		return this.mapper.map(tdTask, TdTaskDto.class);
	}

	// set up some data for use during test
	private final TdTask TEST_TDTASK_1 = new TdTask(1L, "Attend daily standup");
	private final TdTask TEST_TDTASK_2 = new TdTask(2L, "Check emails");
	private final TdTask TEST_TDTASK_3 = new TdTask(3L, "Push changes to GitHub");

	private final List<TdTask> TDTASK_LIST = List.of(TEST_TDTASK_1, TEST_TDTASK_2, TEST_TDTASK_3);

	// create
	@Test // Test 1
	void createTest() throws Exception {
		when(this.service.create(TEST_TDTASK_1)).thenReturn(this.mapToDTO(TEST_TDTASK_1));
		assertThat(new ResponseEntity<TdTaskDto>(this.mapToDTO(TEST_TDTASK_1), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(TEST_TDTASK_1));
		verify(this.service, atLeastOnce()).create(TEST_TDTASK_1);
	}

	// Read one
	@Test // Test 2
	void readOneTest() throws Exception {
		when(this.service.readOne(TEST_TDTASK_1.getId())).thenReturn(this.mapToDTO(TEST_TDTASK_1));
		assertThat(new ResponseEntity<TdTaskDto>(this.mapToDTO(TEST_TDTASK_1), HttpStatus.OK))
				.isEqualTo(this.controller.readOne(TEST_TDTASK_1.getId()));
		verify(this.service, atLeastOnce()).readOne(TEST_TDTASK_1.getId());
	}

	// Read all
	@Test // Test 3
	void readAllTest() throws Exception {
		List<TdTaskDto> dtoList = TDTASK_LIST.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.service.readAll()).thenReturn(dtoList);
		assertThat(this.controller.read()).isEqualTo(new ResponseEntity<>(dtoList, HttpStatus.OK));
		verify(this.service, atLeastOnce()).readAll();
	}

	// Update
	@Test // Test 4
	void updateTest() throws Exception {

		// rules WHEN... THEN DO....
		when(this.service.update(this.mapToDTO(TEST_TDTASK_1), TEST_TDTASK_1.getId()))
				.thenReturn(this.mapToDTO(TEST_TDTASK_1));

		// processes Store variables / perform requests....
		// assertions
		assertThat(new ResponseEntity<TdTaskDto>(this.mapToDTO(TEST_TDTASK_1), HttpStatus.ACCEPTED))
				.isEqualTo(this.controller.update(TEST_TDTASK_1.getId(), this.mapToDTO(TEST_TDTASK_1)));

		// verification Check how many times rule has been applied
		verify(this.service, atLeastOnce()).update(this.mapToDTO(TEST_TDTASK_1), TEST_TDTASK_1.getId());
	}

	// Delete
	@Test // Test 5
	void deleteTest() throws Exception {
		when(this.service.delete(TEST_TDTASK_1.getId())).thenReturn(true);
		assertThat(this.controller.delete(TEST_TDTASK_1.getId()))
				.isEqualTo(new ResponseEntity<>(HttpStatus.NO_CONTENT));
		verify(this.service, atLeastOnce()).delete(TEST_TDTASK_1.getId());
	}

	// Delete
	@Test // Test 6
	void deleteTest2() throws Exception {
		when(this.service.delete(TEST_TDTASK_1.getId())).thenReturn(true);
		assertThat(this.controller.delete(TEST_TDTASK_1.getId()))
				.isEqualTo(new ResponseEntity<>(HttpStatus.NO_CONTENT));
		verify(this.service, atLeastOnce()).delete(TEST_TDTASK_1.getId());
	}

}
