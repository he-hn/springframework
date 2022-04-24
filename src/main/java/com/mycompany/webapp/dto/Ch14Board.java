package com.mycompany.webapp.dto;

import java.util.Date;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class Ch14Board {
	private int bno;
	private String btitle;
	private String bcontent;
	private Date bdate;
	private String mid;
	private String bhitcount;
	private String battachoname;
	private String battachsname;
	private String battachtype;
	private MultipartFile battach;
}
