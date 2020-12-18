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

import com.example.demo.dto.TdTaskDto;
import com.example.demo.persistence.domain.TdTask;
import com.example.demo.persistence.repo.TdTaskRepo;

@SpringBootTest // identify as test file to Spring
@ActiveProfiles("test") // sets application properties file to be used
class TdTaskServiceUnitTest {

	@Autowired
	private TdTaskService service;

	@MockBean
	private TdTaskRepo repo;

	@Autowired
	private ModelMapper mapper;

	private TdTaskDto mapToDTO(TdTask tdTask) {
		return this.mapper.map(tdTask, TdTaskDto.class);
	}

// set up some data for use during test

	private final TdTask TEST_TDTASK_1 = new TdTask(1L, "Attend daily standup");
	private final TdTask TEST_TDTASK_2 = new TdTask(2L, "Review broadband");
	private final TdTask TEST_TDTASK_3 = new TdTask(3L, "lots of Pasta");

	private final List<TdTask> TDTASK_LIST = List.of(TEST_TDTASK_1, TEST_TDTASK_2, TEST_TDTASK_3);

	// create
	@Test
	void createTest() throws Exception {
		when(this.repo.save(TEST_TDTASK_1)).thenReturn(TEST_TDTASK_1);
		assertThat(this.service.create(TEST_TDTASK_1)).isEqualTo(this.mapToDTO(TEST_TDTASK_1));
		verify(this.repo, atLeastOnce()).save(TEST_TDTASK_1);
	}

	// read one
	@Test
	void readOneTest() throws Exception {
		when(this.repo.findById(TEST_TDTASK_1.getId())).thenReturn(Optional.of(TEST_TDTASK_1));
		assertThat(this.service.readOne(TEST_TDTASK_1.getId())).isEqualTo(this.mapToDTO(TEST_TDTASK_1));
		verify(this.repo, atLeastOnce()).findById(TEST_TDTASK_1.getId());
	}

	// read all
	@Test
	void readAllTest() throws Exception {
		when(this.repo.findAll()).thenReturn(TDTASK_LIST);
		assertThat(this.service.readAll().isEmpty()).isFalse();
		verify(this.repo, atLeastOnce()).findAll();
	}

	// Update
	@Test // Test 4
	void updateTest() throws Exception {
		when(this.repo.findById(TEST_TDTASK_1.getId())).thenReturn(Optional.of(TEST_TDTASK_1));
		when(this.repo.save(TEST_TDTASK_1)).thenReturn(TEST_TDTASK_1);
		assertThat(this.service.update(this.mapToDTO(TEST_TDTASK_1), TEST_TDTASK_1.getId()))
				.isEqualTo(this.mapToDTO(TEST_TDTASK_1));
		verify(this.repo, atLeastOnce()).findById(TEST_TDTASK_1.getId());
		verify(this.repo, atLeastOnce()).save(TEST_TDTASK_1);
	}

	// delete
	@Test // Test 5
	void deleteTest() throws Exception {
		when(this.repo.existsById(TEST_TDTASK_1.getId())).thenReturn(false);

		assertThat(this.service.delete(TEST_TDTASK_1.getId())).isTrue();
		verify(this.repo, atLeastOnce()).deleteById(TEST_TDTASK_1.getId());
		verify(this.repo, atLeastOnce()).existsById(TEST_TDTASK_1.getId());

	}
}
