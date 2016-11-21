<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Join Us!</h1>
	
	<form action="join.do" method="post">
		ID: <input type="text" name="id" size="15"/><br />
		PASSWORD: <input type="password" name="pass" size="20"/><br />
		PASSWORD: <input type="password" name="pass_check" size="20"/><br />
		Name: <input type="text" name="name" size="10"/><br />
		Email: <input type="text" name="email" size="20"/><br />
		Phone: <input type="text" name="phone1" size="3"/>-<input type="text" name="phone2" size="3"/>-<input type="text" name="phone3" size="3"/><br />
		Gender: Man<input type="radio"  name="gender" value="man" checked="checked"/> Woman<input type="radio" value="woman" name="gender"/><br />
		Date of Birth: Year<input type="text" name="year" size="5"/>
		
						Month<select name="month">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
						</select>
		
						Day<select name="day">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
							<option value="24">24</option>
							<option value="25">25</option>
							<option value="26">26</option>
							<option value="27">27</option>
							<option value="28">28</option>
							<option value="29">29</option>
							<option value="30">30</option>
							<option value="31">31</option>
						</select><br /><br />
		<input type="submit" value="SignUp"/>
		<input type="reset" value="Reset"/>
		<input type="button" value="Cancle" onclick="window.location.href='login_view.do'"/>
		</form>
</body>
</html>