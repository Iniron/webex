package com.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CCommand {
	
	void execute(HttpServletRequest request, HttpServletResponse response);
	
}
