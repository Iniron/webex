package com.member.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.command.BCommand;
import com.board.command.BContentCommand;
import com.board.command.BDeleteCommand;
import com.board.command.BListCommand;
import com.board.command.BUpdateCommand;
import com.board.command.BWriteCommand;
import com.member.command.CCommand;
import com.member.command.CDeleteCommand;
import com.member.command.CJoinCommand;
import com.member.command.CLoginCommand;
import com.member.command.CLogoutCommand;
import com.member.command.CModifyCommand;

/**
 * Servlet implementation class CFrontController
 */
@WebServlet("*.do")
public class CFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("EUC-KR");
		
		String viewPage = null;
		CCommand cCommand = null;
		BCommand bCommand = null;
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String comStr = uri.substring(contextPath.length());
		
		if(comStr.equals("/login_view.do")){
			viewPage = "login_view.jsp";
		} else if(comStr.equals("/login.do")){
			cCommand = new CLoginCommand();
			cCommand.execute(request, response);
			viewPage = "main.do";
		} else if(comStr.equals("/main.do")){
			viewPage = "main.jsp";	
		} else if(comStr.equals("/join_view.do")){
			viewPage = "join_view.jsp";
		} else if(comStr.equals("/join.do")){
			cCommand = new CJoinCommand();
			cCommand.execute(request, response);
			viewPage = "login_view.do";
		} else if(comStr.equals("/modify_view.do")){
			viewPage = "modify_view.jsp";
		} else if(comStr.equals("/modify.do")){
			cCommand = new CModifyCommand();
			cCommand.execute(request, response);
			viewPage = "main.do";
		} else if(comStr.equals("/logout.do")){
			cCommand = new CLogoutCommand();
			cCommand.execute(request, response);
			viewPage = "login_view.do";
		} else if(comStr.equals("/delete.do")){
			cCommand = new CDeleteCommand();
			cCommand.execute(request, response);
			viewPage = "login_view.do";
		}
		
		
		if(comStr.equals("/board_list_view.do")){
			bCommand = new BListCommand();
			bCommand.execute(request, response);
			viewPage = "board_list_view.jsp";
		} else if(comStr.equals("/board_write_view.do")){
			viewPage = "board_write_view.jsp";
		} else if(comStr.equals("/board_write.do")){
			bCommand = new BWriteCommand();
			bCommand.execute(request, response);
			viewPage = "board_list_view.do";
		} else if(comStr.equals("/board_content_view.do")){
			bCommand = new BContentCommand();
			bCommand.execute(request, response);
			viewPage = "board_content_view.jsp";
		} else if(comStr.equals("/board_delete.do")){
			bCommand = new BDeleteCommand();
			bCommand.execute(request, response);
			viewPage = "board_list_view.do";
		} else if(comStr.equals("/board_modify_view.do")){
			bCommand = new BContentCommand();
			bCommand.execute(request, response);
			viewPage = "board_modify_view.jsp";
		} else if(comStr.equals("/board_modify.do")){
			bCommand = new BUpdateCommand();
			bCommand.execute(request, response);
			viewPage = "board_content_view.do";
		}
		//클라이언트로부터 받은 request, response를 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
