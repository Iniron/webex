package com.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.dao.MemberDao;

public class CDeleteCommand implements CCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();		

		String id = (String)session.getAttribute("id");
		MemberDao dao = MemberDao.Getinstance();
		if(dao.deleteMember(id)==MemberDao.MEMBER_DELETE_SUCCESS)
		{
			System.out.println("Delete Success");
			
			session.invalidate();
			request.setAttribute("success_msg", "회원탈퇴 되었습니다.");
		}else{
			System.out.println("Delete Fail");
			
			request.setAttribute("success_msg", "회원탈퇴에 실패하였습니다.");

		}
		
	}

}
