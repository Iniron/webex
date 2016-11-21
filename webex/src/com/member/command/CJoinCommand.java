package com.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.dao.MemberDao;
import com.member.dto.MemberDto;

public class CJoinCommand implements CCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		String id, pw, name, email, phone, phone1, phone2, phone3, gender, year, day, month, birth;
		HttpSession session = request.getSession(); 		
		
		//회원 정보값 저장
		id = request.getParameter("id");
		pw = request.getParameter("pass");
		name = request.getParameter("name");
		email = request.getParameter("email");
		phone1 = request.getParameter("phone1");
		phone2 = request.getParameter("phone2");
		phone3 = request.getParameter("phone3");
		gender = request.getParameter("gender");
		year = request.getParameter("year");
		month = request.getParameter("month");
		day = request.getParameter("day");
		
		phone = phone1 + "-" + phone2 + "-" + phone3;
		birth = year + "-" + month + "-" + day;
		
		//데이터 베이스 연결 및 쿼리	
		MemberDto dto = new MemberDto();
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.setEmail(email);
		dto.setPhone(phone);
		dto.setGender(gender);
		dto.setBirth(birth);
		
		MemberDao dao = MemberDao.Getinstance();
		if(dao.insertMember(dto) == dao.MEMBER_JOIN_SUCCESS){
			System.out.println("Join Success");
			
			session.setAttribute("id", id);
			request.setAttribute("success_mag", "회원가입 되었습니다.");
		}
		else{
			System.out.println("Join Fail");
			
			request.setAttribute("error_mag", "회원가입에 실패하였습니다.");
		}
	}

}
