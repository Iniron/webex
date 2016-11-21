package com.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDao;

public class BUpdateCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String bid = request.getParameter("bid");
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");		
		
		BoardDao dao = BoardDao.getInstance();
		if(dao.update(bid, bname, btitle, bcontent)==BoardDao.BOARD_UPDATE_SUCCESS){
			request.setAttribute("success_msg", "게시글을 수정하였습니다.");
		} else{
			request.setAttribute("error_msg", "게시글 수정에 실패하였습니다.");
		}		
	}

}
