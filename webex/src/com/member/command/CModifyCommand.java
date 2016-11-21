package com.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.dao.MemberDao;
import com.member.dto.MemberDto;

public class CModifyCommand implements CCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		String id, pw, name, email, phone, phone1, phone2, phone3, gender, year, day, month, birth;
		HttpSession session = request.getSession();		
		//ȸ�� ������ ����
		id = (String)session.getAttribute("id");
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
		
		//������ ���̽� ���� �� ����	
		MemberDto dto = new MemberDto();
		dto.setId(id);		//session���� id�� ������
		dto.setPw(pw);
		dto.setName(name);
		dto.setEmail(email);
		dto.setPhone(phone);
		dto.setGender(gender);
		dto.setBirth(birth);
		
		MemberDao dao = MemberDao.Getinstance();
		if(dao.updateMember(dto) == dao.MEMBER_UPDATE_SUCCESS){
			System.out.println("Update Success");
					
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			request.setAttribute("success_msg", "���������� �����Ͽ����ϴ�.");

		}
		else{
			System.out.println("Update Fail");
			
			request.setAttribute("error_msg", "���������� �����Ͽ����ϴ�.");

		}
				
	}

}
