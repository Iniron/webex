<%@page import="com.board.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link href="css/bootstrap.css" rel="stylesheet">
<style type="text/css">
	.info{
		width: 100px;
	}
	.non_over{
		max-width: 400px;
		overflow:hidden;
		word-wrap:break-word;
	}
</style>
</head>
<body>
<%
	if(request.getParameter("nowpage")!=null){
		session.setAttribute("nowpage", request.getParameter("nowpage"));
	}

	BoardDto dto = new BoardDto();
	dto = (BoardDto)request.getAttribute("content");
%>
	<div class="container">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">	
			<table class="table table-striped">
				<tr>
					<td class="info">작성자</td>
					<td><%= dto.getBname()%></td>
				</tr>
				<tr>
					<td class="info">조회수</td>
					<td><%= dto.getBhit()%></td>
				</tr>
				<tr>
					<td class="info">작성날짜</td>
					<td><%= dto.getBdate()%></td>
				</tr>	
				<tr>
					<td class="info">제목</td>
					<td><%= dto.getBtitle()%></td>
				</tr>
				<tr>
					<td class="info">내용</td>
					<td><div class="non_over"><%= dto.getBcontent()%></div></td>
				</tr>
			</table>
			<div align="right">
				<input class="btn" type="button" value="목록" onclick="window.location.href='board_list_view.do?nowpage=<%=(String)session.getAttribute("nowpage")%>'">
				<input class="btn" type="button" value="답변" onclick="window.location.href='board_reply_view.do?bid=<%=dto.getBid()%>'">
				<input class="btn" type="button" value="수정" onclick="window.location.href='board_modify_view.do?bid=<%=dto.getBid()%>'">
				<input class="btn" type="button" value="삭제" onclick="window.location.href='board_delete.do?bid=<%=dto.getBid()%>'">
			</div>
			</div>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>	
</body>
</html>