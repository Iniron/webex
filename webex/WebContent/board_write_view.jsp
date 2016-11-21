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
		text-align: center;
		vertical-align: middle;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">	
			<form action="board_write.do" method="post">
				<table class="table table-striped">
				<tr>
					<td class="info" size="200">이름</td>
					<td><input type="text" name="bname" class="form-control" placeholder="Input your name"></td>
				</tr>	
				<tr>
					<td class="info">제목</td>
					<td><input type="text" name="btitle" class="form-control" placeholder="Input title"></td>
				</tr>
				<tr>
					<td class="info">내용</td>
					<td><textarea rows="10" cols="50" name="bcontent" class="form-control" placeholder="Input Content" ></textarea></td>
				</tr>
				</table>
				<div align="right">
					<input class="btn" type="submit" value="입력"></td>				
				</div>	
			</form>
			</div>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>	
</body>
</html>