<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" href="resources/css/style.css" type="text/css"
	media="screen" />
</head>
<body style="background: #b3d1ff;">
	<strong style="float: right; padding-right: 15px">${date}</strong>
	<div class="center-square">
		<h2 style="text-align: center;">Login</h2>
		<div id="error" style="color: red">${error}</div>
		<form action=${pageContext.request.contextPath}/login method="POST">
			<div style="text-align: center;">
				<label for="userId"><strong
					style="margin: 0px 0px 10px 0px;"> User Id : </strong></label>&nbsp;&nbsp;
				<input type="text" id="userId" name="userName" required
					mxlength="30" autofocus="false" title="User Name Max 30 chars"><br />
				<br /> <label for="password"><strong>Password : </strong></label> <input
					type="password" id="password" minlength="8" maxlength="30"
					name="password" title="Password should at least 8 chars " required>
				<br /> <br />
				<div style="margin-left: 6%; padding-top: 10px;">
					<input type="submit" name="submit" id="login" value="Login"
						autofocus=" false" /> &nbsp;&nbsp;&nbsp; <input name="reset"
						type="reset" id="reset" value="Clear" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>