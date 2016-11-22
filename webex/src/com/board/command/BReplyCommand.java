package com.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDao;

public class BReplyCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String bgroup = request.getParameter("bgroup");
		String bstep = request.getParameter("bstep");
		String bindent = request.getParameter("bindent");
		System.out.println("1");
		
		BoardDao dao = BoardDao.getInstance();
		
		if(dao.reply(bname, btitle, bcontent, bgroup, bstep, bindent)==BoardDao.BOARD_REPLY_SUCCESS){
			request.setAttribute("success_msg", "�Խñۿ� �亯�� �Է��Ͽ����ϴ�.");
			System.out.println("4");
		} else{
			request.setAttribute("error_msg", "�亯�� �Է��� �����Ͽ����ϴ�.");
		}
		
	}

}
