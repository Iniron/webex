<%@page import="com.board.dto.BoardDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	int nowpage = (Integer)request.getAttribute("nowpage");
	int firstpage = (Integer)request.getAttribute("firstpage");
	int lastpage = (Integer)request.getAttribute("lastpage");
	int maxpage = (Integer)request.getAttribute("maxpage");
%>
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
					<td align="left">
						<a href="board_content_view.do?bid=<%=dtos.get(i).getBid() %>&nowpage=<%=nowpage%>">
						<%for(int j=0; j<dtos.get(i).getBindent(); j++){out.println("&nbsp;&nbsp;&nbsp;");}%>			
						<%=dtos.get(i).getBtitle()%></a>
					</td>
					<td><%=dtos.get(i).getBdate()%></td>
					<td><%=dtos.get(i).getBhit()%></td>
				</tr>
				<%
						}
					}
				%>
			</table>
			<div class="row">
				<div class="col-md-2"></div>
				<div align="center" class="col-md-8">
					<nav>
						<ul class="pagination">
					  	<% if(nowpage<=1){ %>
						  	<li class="disabled"><span><span aria-hidden="true">&laquo;</span></span></li>
						<% } else{ %>
						  	<li><a href="board_list_view.do?nowpage=<%=nowpage-1%>" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
						<% } 
					    	for(int k=firstpage; k<=lastpage; k++){
					    	if(k==maxpage+1) break; 
					    	if(k==nowpage){ %>
					    		<li class="active"><span><%=k%><span class="sr-only">(current)</span></span></li>	
					    <% continue; } %>
					    	<li><a href="board_list_view.do?nowpage=<%=k%>"><%=k%></a></li>
					    <% } 
					    	if(nowpage>=maxpage){
					    %>
					    	<li class="disabled"><span><span aria-hidden="true">&raquo;</span></span></li>
					    <% } else{ %>
					    	<li><a href="board_list_view.do?nowpage=<%=nowpage+1%>" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
					    <% } %>
					  </ul>
					</nav>
				</div>
				
				<div class="col-md-2">
					<input class="btn" type="button" value="글작성" onclick="window.location.href='board_write_view.do'">
				</div>
			</div>
			</div>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
</body>
</html>