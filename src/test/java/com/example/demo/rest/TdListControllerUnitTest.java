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

import com.example.demo.dto.TdListDto;
import com.example.demo.persistence.domain.TdList;
import com.example.demo.service.TdListService;

@SpringBootTest // identify as test file to Spring
@ActiveProfiles("test") // sets application properties file to be used
class TdListControllerUnitTest {

	@Autowired
	private TdListController controller;

	@MockBean
	private TdListService service;

	@Autowired
	private ModelMapper mapper;

	private TdListDto mapToDTO(TdList tdList) {
		return this.mapper.map(tdList, TdListDto.class);
	}

	// set up some data for use during test
	private final TdList TEST_TDLIST_1 = new TdList(1L, "Work");
	private final TdList TEST_TDLIST_2 = new TdList(2L, "Home");
	private final TdList TEST_TDLIST_3 = new TdList(3L, "Shopping");

	private final List<TdList> TDLIST_LIST = List.of(TEST_TDLIST_1, TEST_TDLIST_2, TEST_TDLIST_3);

	// create
	@Test // Test 1
	void createTest() throws Exception {
		when(this.service.create(TEST_TDLIST_1)).thenReturn(this.mapToDTO(TEST_TDLIST_1));
		assertThat(new ResponseEntity<TdListDto>(this.mapToDTO(TEST_TDLIST_1), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(TEST_TDLIST_1));
		verify(this.service, atLeastOnce()).create(TEST_TDLIST_1);
	}

	// Read one
	@Test // Test 2
	void readOneTest() throws Exception {
		when(this.service.readOne(TEST_TDLIST_1.getId())).thenReturn(this.mapToDTO(TEST_TDLIST_1));
		assertThat(new ResponseEntity<TdListDto>(this.mapToDTO(TEST_TDLIST_1), HttpStatus.OK))
				.isEqualTo(this.controller.readOne(TEST_TDLIST_1.getId()));
		verify(this.service, atLeastOnce()).readOne(TEST_TDLIST_1.getId());
	}

	// Read all
	@Test // Test 3
	void readAllTest() throws Exception {
		List<TdListDto> dtoList = TDLIST_LIST.stream().map(this::mapToDTO).collect(Collectors.toList());
		when(this.service.readAll()).thenReturn(dtoList);
		assertThat(this.controller.read()).isEqualTo(new ResponseEntity<>(dtoList, HttpStatus.OK));
		verify(this.service, atLeastOnce()).readAll();
	}

	// Update
	@Test // Test 4
	void updateTest() throws Exception {

		// rules WHEN... THEN DO....
		when(this.service.update(this.mapToDTO(TEST_TDLIST_1), TEST_TDLIST_1.getId()))
				.thenReturn(this.mapToDTO(TEST_TDLIST_1));

		// processes Store variables / perform requests....
		// assertions
		assertThat(new ResponseEntity<TdListDto>(this.mapToDTO(TEST_TDLIST_1), HttpStatus.ACCEPTED))
				.isEqualTo(this.controller.update(TEST_TDLIST_1.getId(), this.mapToDTO(TEST_TDLIST_1)));

		// verification Check how many times rule has been applied
		verify(this.service, atLeastOnce()).update(this.mapToDTO(TEST_TDLIST_1), TEST_TDLIST_1.getId());
	}

	// Delete
	@Test // Test 5
	void deleteTest() throws Exception {
		when(this.service.delete(TEST_TDLIST_1.getId())).thenReturn(true);
		assertThat(this.controller.delete(TEST_TDLIST_1.getId()))
				.isEqualTo(new ResponseEntity<>(HttpStatus.NO_CONTENT));
		verify(this.service, atLeastOnce()).delete(TEST_TDLIST_1.getId());
	}

	// Delete
	@Test // Test 6
	void deleteTest2() throws Exception {
		when(this.service.delete(TEST_TDLIST_1.getId())).thenReturn(true);
		assertThat(this.controller.delete(TEST_TDLIST_1.getId()))
				.isEqualTo(new ResponseEntity<>(HttpStatus.NO_CONTENT));
		verify(this.service, atLeastOnce()).delete(TEST_TDLIST_1.getId());
	}

}
