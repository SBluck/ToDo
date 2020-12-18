package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.dto.TdListDto;
import com.example.demo.persistence.domain.TdList;
import com.example.demo.persistence.repo.TdListRepo;

@SpringBootTest // identify as test file to Spring
@ActiveProfiles("test") // sets application properties file to be used
class TdListServiceUnitTest {

	@Autowired
	private TdListService service;

	@MockBean
	private TdListRepo repo;

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
	@Test
	void createTest() throws Exception {
		when(this.repo.save(TEST_TDLIST_1)).thenReturn(TEST_TDLIST_1);
		assertThat(this.service.create(TEST_TDLIST_1)).isEqualTo(this.mapToDTO(TEST_TDLIST_1));
		verify(this.repo, atLeastOnce()).save(TEST_TDLIST_1);
	}

	// read one
	@Test
	void readOneTest() throws Exception {
		when(this.repo.findById(TEST_TDLIST_1.getId())).thenReturn(Optional.of(TEST_TDLIST_1));
		assertThat(this.service.readOne(TEST_TDLIST_1.getId())).isEqualTo(this.mapToDTO(TEST_TDLIST_1));
		verify(this.repo, atLeastOnce()).findById(TEST_TDLIST_1.getId());
	}

	// read all
	@Test
	void readAllTest() throws Exception {
		when(this.repo.findAll()).thenReturn(TDLIST_LIST);
		assertThat(this.service.readAll().isEmpty()).isFalse();
		verify(this.repo, atLeastOnce()).findAll();
	}

	// Update
	@Test // Test 4
	void updateTest() throws Exception {
		when(this.repo.findById(TEST_TDLIST_1.getId())).thenReturn(Optional.of(TEST_TDLIST_1));
		when(this.repo.save(TEST_TDLIST_1)).thenReturn(TEST_TDLIST_1);
		assertThat(this.service.update(this.mapToDTO(TEST_TDLIST_1), TEST_TDLIST_1.getId()))
				.isEqualTo(this.mapToDTO(TEST_TDLIST_1));
		verify(this.repo, atLeastOnce()).findById(TEST_TDLIST_1.getId());
		verify(this.repo, atLeastOnce()).save(TEST_TDLIST_1);
	}

	// delete
	@Test // Test 5
	void deleteTest() throws Exception {
		when(this.repo.existsById(TEST_TDLIST_1.getId())).thenReturn(false);

		assertThat(this.service.delete(TEST_TDLIST_1.getId())).isTrue();
		verify(this.repo, atLeastOnce()).deleteById(TEST_TDLIST_1.getId());
		verify(this.repo, atLeastOnce()).existsById(TEST_TDLIST_1.getId());

	}
}
