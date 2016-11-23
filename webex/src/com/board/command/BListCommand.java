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
		int limit = 10;		//���������� ���̴� �Խñ� ��
		int listcount = 0;		//�Խñ� ��
		int pagecount = 5;		//������ ��
		int ceil;		
		int firstpage=1;
		int lastpage=1;
		
		
		if(request.getParameter("nowpage")!=null){
			nowpage = Integer.parseInt(request.getParameter("nowpage"));
			System.out.println("nowpage= " + nowpage);		//����������
		}
		
		BoardDao dao = BoardDao.getInstance();
		ArrayList<BoardDto> dtos = dao.getList(nowpage, limit);
		
		System.out.println("dtos size =" + dtos.size());	//����Ʈ ������
		
		request.setAttribute("list", dtos);
		
		if((listcount=dao.getListCount())!=BoardDao.BOARD_NO_COUNT){
			
			ceil = (int)Math.ceil((double)nowpage/pagecount);		//�ø�
			firstpage = pagecount * (ceil-1) + 1;					//5*(�ø�-1) +1
			lastpage = pagecount * ceil;							//5*�ø�
			
//			lastpage = listcount/limit;
//			if(listcount%limit>0){
//				lastpage++;
//			}
			
			request.setAttribute("nowpage", nowpage);
			request.setAttribute("firstpage", firstpage);
			request.setAttribute("lastpage", lastpage);
			request.setAttribute("success_msg", "�Խñ��� ã�ҽ��ϴ�.");
		} else{
			request.setAttribute("success_msg", "�Խñ��� �����ϴ�.");
		}
		
	}

}
