package com.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDao;
import com.board.dto.BoardDto;

public class BContentCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String bid = (String)request.getParameter("bid");
		
		BoardDao dao = BoardDao.getInstance();
		BoardDto dto = dao.contentView(bid);
		
		request.setAttribute("content", dto);
	}
}
