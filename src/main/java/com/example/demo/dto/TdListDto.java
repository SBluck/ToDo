package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TdListDto {
	private Long id;
	private String name;

	private List<TdTaskDto> tdTaskList = new ArrayList<>();
}
