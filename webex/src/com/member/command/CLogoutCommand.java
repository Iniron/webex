package com.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CLogoutCommand implements CCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();		
		session.invalidate();
		
		System.out.println("logout success");
		request.setAttribute("success_mgs", "�α׾ƿ� �Ǿ����ϴ�.");
	}

}
