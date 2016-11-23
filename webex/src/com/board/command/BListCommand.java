package com.board.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.BoardDao;
import com.board.dto.BoardDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		int nowpage = 1;
		int limit = 10;		//한페이지에 보이는 게시글 수
		int listcount = 0;		//게시글 수
		int pagecount = 5;		//페이지 수
		int ceil;		
		int firstpage=1;
		int lastpage=1;
		
		
		if(request.getParameter("nowpage")!=null){
			nowpage = Integer.parseInt(request.getParameter("nowpage"));
			System.out.println("nowpage= " + nowpage);		//현재페이지
		}
		
		BoardDao dao = BoardDao.getInstance();
		ArrayList<BoardDto> dtos = dao.getList(nowpage, limit);
		
		System.out.println("dtos size =" + dtos.size());	//리스트 사이즈
		
		request.setAttribute("list", dtos);
		
		if((listcount=dao.getListCount())!=BoardDao.BOARD_NO_COUNT){
			
			ceil = (int)Math.ceil((double)nowpage/pagecount);		//올림
			firstpage = pagecount * (ceil-1) + 1;					//5*(올림-1) +1
			lastpage = pagecount * ceil;							//5*올립
			
//			lastpage = listcount/limit;
//			if(listcount%limit>0){
//				lastpage++;
//			}
			
			request.setAttribute("nowpage", nowpage);
			request.setAttribute("firstpage", firstpage);
			request.setAttribute("lastpage", lastpage);
			request.setAttribute("success_msg", "게시글을 찾았습니다.");
		} else{
			request.setAttribute("success_msg", "게시글이 없슨니다.");
		}
		
	}

}
