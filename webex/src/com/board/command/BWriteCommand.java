package com.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDao;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		int check;
		String bid = request.getParameter("bid");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		BoardDao dao = BoardDao.getInstance();
		check = dao.write(bid, btitle, bcontent);
		
		if(check == BoardDao.BOARD_WRITE_SUCCESS){
			request.setAttribute("access_msg", "�Խñ��� ����Ͽ����ϴ�.");
		} else{
			request.setAttribute("error_msg", "�Խñ� ����� �����Ͽ����ϴ�.");
		}	
	}
}
