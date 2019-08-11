<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New User</title>
<style>
	<%@include file="/WEB-INF/css/style.css"%>
</style>
</head>
<body>
	<h1>Hotel Booking Management System</h1>
	<form action="login" method="post">
		<table align="center">
			<tr>
				<td>User Name:<span class="required">*</span></td>
				<td><input type="text" name="username" pattern="[A-Za-z0-9]{1,20}" required=""/></td>
			</tr>
			<tr>
				<td>Email:<span class="required">*</span></td>
				<td><input type="text" name="email" pattern="[A-Za-z0-9._%+-]{1,18}@[a-z0-9.-]{1,7}\.[a-z]{2,3}$" required=""/></td>
			</tr>
			<tr>
				<td>Address:<span class="required">*</span></td>
				<td><input type="text" name="address" pattern="[A-Za-z0-9]{1,25}" required=""/></td>
			</tr>
			<tr>
				<td>Mobile Number:<span class="required">*</span></td>
				<td><input type="text" name="mobile_no" pattern="[789][0-9]{9}" required=""/></td>
			</tr>
			<tr>
				<td>Phone:<span class="required">*</span></td>
				<td><input type="text" name="phone" pattern="[789][0-9]{9}" required=""/></td>
			</tr><tr>
				<td>Password:<span class="required">*</span></td>
				<td><input type="password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required=""/></td>
			</tr>
		</table><br>
		<input type="submit" value="Submit" name="submit"/><br><br>
		
	</form>
	<form action="login" method="post">
		<input type="submit" name="submit" value="Home Page"/>
	</form>
</body>
</html>