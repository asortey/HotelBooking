<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<style>
	<%@include file="/WEB-INF/css/style.css"%>
</style>
</head>
<body>
	<h1>Hotel Booking Management System</h1>
	<form method="post" action="login">
		User Name: <input type="text" name="userName"/><br><br>
		Password: <input type="password" name="password"/><br><br>
		<input type="submit" value="Login" name="submit"/><br><br>
		<input type="submit" name="submit" value="Home Page"/>
	</form>
	
</body>
</html>