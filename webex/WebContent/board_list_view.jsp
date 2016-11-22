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
					<td>��ȣ</td>
					<td>�ۼ���</td>
					<td class="board_title">����</td>
					<td>�ۼ���¥</td>
					<td>��ȸ��</td>
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
						<a href="board_content_view.do?bid=<%=dtos.get(i).getBid() %>">
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
					    <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
					    <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
					    <li><a href="#">2</a></li>
					    <li><a href="#">3</a></li>
					    <li><a href="#">4</a></li>
					    <li><a href="#">5</a></li>
					    <li>
					      <a href="#" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
					  </ul>
					</nav>
				</div>
				
				<div class="col-md-2">
					<input class="btn" type="button" value="���ۼ�" onclick="window.location.href='board_write_view.do'">
				</div>
			</div>
			</div>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
</body>
</html>