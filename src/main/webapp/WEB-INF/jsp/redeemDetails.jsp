<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Redeem Page</title>
<!-- Static content -->
<link rel="stylesheet" href="resources/css/style.css" type="text/css"
	media="screen" />
</head>
<body>
	<div class="topnav">
		<strong> <a style="float: right;"
			href="${pageContext.request.contextPath}/logout">Logout</a> <a
			style="float: right;" href="${pageContext.request.contextPath}/home">Home</a>
		</strong>
	</div>
	<div id="successHeader">
		<strong>Redeem Points </strong>
	</div>
	<div class="table-align">
		<table>
			<tr>
				<td>Account No</td>
				<td>${accountNo}</td>
			</tr>
			<tr>
				<td>Balance</td>
				<td>${balanceAmount}</td>
			</tr>
			<tr>
				<td>Points to Redeem</td>
				<td>${redeemPoints}</td>
			</tr>
		</table>
	</div>

	<div style="padding-top: 10px; text-align: center">
		<form action=${pageContext.request.contextPath}/postredeemDetails
			method="post">
			<input type="submit" name="submit" id="submit" value="Submit"
				autofocus="false" />
		</form>
	</div>

</body>
</html>