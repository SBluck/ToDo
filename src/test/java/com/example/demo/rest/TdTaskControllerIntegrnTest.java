package com.example.demo.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.dto.TdTaskDto;
import com.example.demo.persistence.domain.TdTask;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
// sql scripts run in order so schema first then data
@Sql(scripts = { "classpath:tdl-schema.sql",
		"classpath:tdl-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
class TdTaskControllerIntegrnTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	ObjectMapper jsonifier;

	@Autowired
	private ModelMapper mapper;

	private TdTaskDto mapToDTO(TdTask tdTask) {
		return this.mapper.map(tdTask, TdTaskDto.class);
	}

	// tdl-data.sql
//		INSERT INTO tdList (`name`) VALUES ('Work');
//		INSERT INTO tdList (`name`) VALUES ('Home');
//		INSERT INTO tdList (`name`) VALUES ('Shopping');
//		INSERT INTO tdTask (`todo`) VALUES ('Attend daily stand-up');
//		INSERT INTO tdTask (`todo`) VALUES ('Check emails');
//		INSERT INTO tdTask (`todo`) VALUES ('Push changes to GitHub');

	// set up some data for use during test
	private final TdTask TEST_TDTASK_1 = new TdTask(1L, "Attend daily standup");
//	private final TdTask TEST_TDTASK_2 = new TdTask(2L, "Check emails");
//	private final TdTask TEST_TDTASK_3 = new TdTask(3L, "Push changes to GitHub");

//	private final List<TdTask> TDTASK_LIST = List.of(TEST_TDTASK_1, TEST_TDTASK_2, TEST_TDTASK_3);

	private final String URI = "/tdTask";

	// Create test
	@Test
	void createTest() throws Exception {
		TdTaskDto testDto = mapToDTO(new TdTask("Christmas shopping"));
		String testDtoAsJson = this.jsonifier.writeValueAsString(testDto);

		RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDtoAsJson);

		ResultMatcher checkStatus = status().isCreated();

		TdTaskDto testSavedDto = mapToDTO(new TdTask("Christmas shopping"));
		testSavedDto.setId(1L);

		String testSavedDtoAsJson = this.jsonifier.writeValueAsString(testSavedDto);

		ResultMatcher checkBody = content().json(testSavedDtoAsJson);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void deleteTest() throws Exception {
		this.mvc.perform(delete(URI + "/delete/" + TEST_TDTASK_1.getId())).andExpect(status().isNoContent());
	}

}
