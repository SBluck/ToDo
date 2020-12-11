package com.example.demo.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TdTask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String todo;

//	@ManyToOne
//	private TdList tdList;

	public TdTask(Long id, String todo) {
		super();
		this.id = id;
		this.todo = todo;

	}

	public TdTask(String todo) {
		super();
		this.todo = todo;

	}

}
