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
		int pagecount = 5;		//화면에 보여지는 페이지 수 [ << 1 2 3 4 5 >> ] -> 총 5개
		int firstpage=1;
		int lastpage=1;
		int maxpage=1;
		
		BoardDao dao = BoardDao.getInstance();

		if((listcount=dao.getListCount())!=BoardDao.BOARD_NO_COUNT){	//총 게시글 검색
			maxpage = listcount/limit;
			if(listcount%limit>0){
				maxpage++;
			}
			System.out.println("maxpage =" + maxpage);
			
			if(request.getParameter("nowpage")!=null){
				nowpage = Integer.parseInt(request.getParameter("nowpage"));
				
				if(nowpage<1) nowpage=1;
				else if(nowpage>maxpage) nowpage=maxpage;				 
				System.out.println("nowpage= " + nowpage);		//현재페이지
			} 
					
			int ceil = (int)Math.ceil((double)nowpage/pagecount);		//올림, nowpage기준으로 보여지는 페이지의 범위 선정 
			firstpage = pagecount * (ceil-1) + 1;					//5*(올림-1) +1
			lastpage = pagecount * ceil;							//5*올립
			
			System.out.println("firstpage =" + firstpage);
			System.out.println("lastpage =" + lastpage);
			
			request.setAttribute("success_msg", "게시글을 찾았습니다.");
		} else{
			request.setAttribute("success_msg", "게시글이 없슨니다.");
		}
		
		request.setAttribute("nowpage", nowpage);	
		request.setAttribute("firstpage", firstpage);
		request.setAttribute("lastpage", lastpage);
		request.setAttribute("maxpage", maxpage);
		
		ArrayList<BoardDto> dtos = dao.getList(nowpage, limit);
		request.setAttribute("list", dtos);
		
		System.out.println("dtos size =" + dtos.size());	//리스트 사이즈		
	}

}
