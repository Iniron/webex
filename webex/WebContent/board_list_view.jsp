<%@page import="com.board.dto.BoardDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
	.board_title{
		width: 50%;
	}
</style>
<link href="css/bootstrap.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">	
			<table class="table table-striped">
				<tr class="info" align="center">
					<td>번호</td>
					<td>작성자</td>
					<td class="board_title">제목</td>
					<td>작성날짜</td>
					<td>조회수</td>
				</tr>
				<%	
					if(request.getAttribute("list")!=null){
					ArrayList<BoardDto> dtos = (ArrayList<BoardDto>)request.getAttribute("list");			
					for(int i=0; i<dtos.size(); i++){
				%>
				<tr align="center">
					<td><%=dtos.get(i).getBid()%></td>
					<td><%=dtos.get(i).getBname()%></td>
					<td align="left"><a href="board_content_view.do?bid=<%=dtos.get(i).getBid() %>"><%=dtos.get(i).getBtitle()%></a></td>
					<td><%=dtos.get(i).getBdate()%></td>
					<td><%=dtos.get(i).getBhit()%></td>
				</tr>
				<%
						}
					}
				%>
			</table>
			<div align="right">
				<input class="btn" type="button" value="글작성" onclick="window.location.href='board_write_view.do'">
			</div>
			</div>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
</body>
</html>