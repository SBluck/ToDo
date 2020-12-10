package com.example.demo.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

	@ManyToOne
	private TdList tdList;

	public TdTask(Long id, @NotNull String todo, TdList tdList) {
		super();
		this.id = id;
		this.todo = todo;
		this.tdList = tdList;
	}

	public TdTask(@NotNull String todo, TdList tdList) {
		super();
		this.todo = todo;
		this.tdList = tdList;
	}

}
