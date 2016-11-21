package com.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.dao.MemberDao;
import com.member.dto.MemberDto;

public class CLoginCommand implements CCommand {

	@Override
	
	
	public void execute(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		int check=0;
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pass");
		
		MemberDao dao = MemberDao.Getinstance();
		check = dao.userCheck(id, pw);
		if(check == MemberDao.MEMBER_LOGIN_OK){
			System.out.println("Login Success");
			
			MemberDto dto = dao.getMember(id);
			HttpSession session = request.getSession();
			session.setAttribute("id", dto.getId());
			session.setAttribute("pw", dto.getPw());
			session.setAttribute("name", dto.getName());
			request.setAttribute("success_msg", "로그인 되었습니다.");
	
		} else if(check == MemberDao.MEMBER_LOGIN_NO_ID){
			System.out.println("Member is not exist");
			
			request.setAttribute("error_msg", "회원이 존재하지 않습니다.");
		} else{
			System.out.println("Unmatch password");
			
			request.setAttribute("error_msg", "비밀번호가 일치하지 않습니다.");
		}
		
	}

}
