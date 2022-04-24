package com.mycompany.webapp.security;

import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class Ch17UserDetails extends User {
	private String mname;
	private String memail;
	
	public Ch17UserDetails(
			String mid, 
			String mpassword, 
			boolean menabled, 
			List<GrantedAuthority> mauthorities,
			String mname,
			String memail) {
		super(mid, mpassword, menabled, true, true, true, mauthorities);
		this.mname = mname;
		this.memail = memail;
	}
	
	public String getMname() {
		return mname;
	}

	public String getMemail() {
		return memail;
	}
}

