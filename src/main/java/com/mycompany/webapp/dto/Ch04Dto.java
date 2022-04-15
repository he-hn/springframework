package com.mycompany.webapp.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Ch04Dto {
	private String param1;
	private String param2;
	private String param3;
	private boolean param4;
	@DateTimeFormat(pattern="yyyy-MM-dd") private Date param5;
}
