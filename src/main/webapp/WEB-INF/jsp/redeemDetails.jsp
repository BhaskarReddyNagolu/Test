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
		<strong> <a href="#">Cash Transfer</a> <a href="#">View
				Transaction History</a> <a href="/redeemDetails">Redeem</a>  <a style="float: right;"
			href="${pageContext.request.contextPath }/logout">Logout</a>
		</strong>
	</div>
	<div class="table-align">
		<table>
			<tr>
			  <td> Account No
			  </td>
			 
			  <td>${accountNo}</td>
			  
			</tr>
			<tr>
			  <td> Balance
			  </td>
			  
			  <td>${balanceAmount}</td>
			  
			</tr>
			<tr>
			  <td> Points to Redeem
			  </td>
			  
			  <td>${redeemPoints}</td>
			  
			</tr>		
		</table>
	</div>
	
	<div style="margin-left: 6%; padding-top: 10px;">
	
	<form action="/postredeemDetails" method="post">
		<input type="submit" name="submit" id="submit" value="submit" autofocus="false"/>
	</form>
	</div>
	
</body>
</html>