package com.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDao;

public class BDeleteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String bid = request.getParameter("bid");
		
		BoardDao dao = BoardDao.getInstance();
		
		if(dao.delete(bid)==BoardDao.BOARD_DELETE_SUCCESS){
			request.setAttribute("success_msg", "게시글을 삭제하였습니다.");
		} else{
			request.setAttribute("error_msg", "게시글 삭제에 실패하였습니다.");
		}
	}

}
