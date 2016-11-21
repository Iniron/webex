<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	if(session.getAttribute("name")!=null) response.sendRedirect("login_view.do");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Home Page</h1>
	
	<form action="login.do" method="post">
		ID: <input type="text" name="id" value="<%if(session.getAttribute("id")!=null) out.println(session.getAttribute("id"));%>" size="20"><br>
		PASSWORD: <input type="password" name="pass" size="20"><br>
		<input type="submit" value="login">
		<input type="button" value="Join" onclick="window.location.href='join_view.do'">
	</form>
</body>
</html>