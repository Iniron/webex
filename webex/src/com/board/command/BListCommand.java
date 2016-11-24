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
		int pagecount = 5;		//ȭ�鿡 �������� ������ �� [ << 1 2 3 4 5 >> ] -> �� 5��
		int firstpage=1;
		int lastpage=1;
		int maxpage=1;
		
		BoardDao dao = BoardDao.getInstance();

		if((listcount=dao.getListCount())!=BoardDao.BOARD_NO_COUNT){	//�� �Խñ� �˻�
			maxpage = listcount/limit;
			if(listcount%limit>0){
				maxpage++;
			}
			System.out.println("maxpage =" + maxpage);
			
			if(request.getParameter("nowpage")!=null){
				nowpage = Integer.parseInt(request.getParameter("nowpage"));
				
				if(nowpage<1) nowpage=1;
				else if(nowpage>maxpage) nowpage=maxpage;				 
				System.out.println("nowpage= " + nowpage);		//����������
			} 
					
			int ceil = (int)Math.ceil((double)nowpage/pagecount);		//�ø�, nowpage�������� �������� �������� ���� ���� 
			firstpage = pagecount * (ceil-1) + 1;					//5*(�ø�-1) +1
			lastpage = pagecount * ceil;							//5*�ø�
			
			System.out.println("firstpage =" + firstpage);
			System.out.println("lastpage =" + lastpage);
			
			request.setAttribute("success_msg", "�Խñ��� ã�ҽ��ϴ�.");
		} else{
			request.setAttribute("success_msg", "�Խñ��� �����ϴ�.");
		}
		
		request.setAttribute("nowpage", nowpage);	
		request.setAttribute("firstpage", firstpage);
		request.setAttribute("lastpage", lastpage);
		request.setAttribute("maxpage", maxpage);
		
		ArrayList<BoardDto> dtos = dao.getList(nowpage, limit);
		request.setAttribute("list", dtos);
		
		System.out.println("dtos size =" + dtos.size());	//����Ʈ ������		
	}

}
