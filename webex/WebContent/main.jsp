<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <% 
 	if(session.getAttribute("id")==null) response.sendRedirect("login_view.do");
 	String id = (String)session.getAttribute("id");
 	String name = (String)session.getAttribute("name");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%-- --------------------------����ó���Դϴ�.-------------------------------- 
<%
	String success_msg= (String)request.getAttribute("success_msg");
	String error_msg= (String)request.getAttribute("error_msg");
	if(success_msg!=null){
%>
	<script>alert("<%=success_msg%>");</script>
<%
	}
	if(request.getAttribute("error_msg")!=null){
%>
	<script>alert("<%=error_msg%>");history.go(-1);</script>
<%
	}
%> --%>
	<h1><%= name %>�� �α��� �Ǿ����ϴ�.</h1>
	
	<input type="button" value="logout" onclick="window.location.href='logout.do'">
	<input type="button" value="modify" onclick="window.location.href='modify_view.do'">
	<input type="button" value="deactivated" onclick="deactivated_check()">
	
	<script>
		function deactivated_check(){
			if(confirm("ȭ���� Ż���Ͻðڽ��ϱ�?")){
				window.location.href='delete.do';				
			}
		}
	</script>
</body>
</html>
