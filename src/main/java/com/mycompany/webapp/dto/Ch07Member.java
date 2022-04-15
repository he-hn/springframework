package com.mycompany.webapp.dto;

import lombok.Data;

@Data
public class Ch07Member {
	private String name;
	private int age;
	private String job;
	private Ch07City city;
}
