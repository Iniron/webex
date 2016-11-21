<%@page import="com.board.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link href="css/bootstrap.css" rel="stylesheet">
</head>
<body>
<%
	BoardDto dto = new BoardDto();
	dto = (BoardDto)request.getAttribute("content");
%>
	<table border="1" cellpadding="2" cellspacing="0">
		<tr>
			<td>작성자</td>
			<td><%= dto.getBname()%></td>
		</tr>
		<tr>
			<td>조회수</td>
			<td><%= dto.getBhit()%></td>
		</tr>
		<tr>
			<td>작성날짜</td>
			<td><%= dto.getBdate()%></td>
		</tr>	
		<tr>
			<td>제목</td>
			<td><%= dto.getBtitle()%></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><%= dto.getBcontent()%></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="목록" onclick="window.location.href='board_list_view.do'">
				<input type="button" value="답변" onclick="window.location.href='board_reply_view.do'">
				<input type="button" value="수정" onclick="window.location.href='board_modify_view.do'">
				<input type="button" value="삭제" onclick="window.location.href='board_delete.do?bid=<%=dto.getBid()%>'">
			</td>
		</tr>
	</table>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>	
</body>
</html>