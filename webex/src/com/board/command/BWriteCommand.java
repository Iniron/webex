package com.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDao;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		int check;
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		BoardDao dao = BoardDao.getInstance();
		check = dao.write(bname, btitle, bcontent);
		
		if(check == BoardDao.BOARD_WRITE_SUCCESS){
			request.setAttribute("access_msg", "게시글을 등록하였습니다.");
		} else{
			request.setAttribute("error_msg", "게시글 등록을 실패하였습니다.");
		}	
	}
}
