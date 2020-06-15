<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
<!-- Static content -->
<link rel="stylesheet" href="resources/css/style.css" type="text/css"
	media="screen" />
</head>
<body>
	<div class="topnav">
		<strong> <a href="${pageContext.request.contextPath}/cashTransfer">Cash Transfer</a> <a
			href="${pageContext.request.contextPath}/transactionHistory">View Transaction History</a>
			 <c:forEach
				var="userDetails" items="${userDetails}">
				<c:if test="${userDetails.getPremiumUser().equals('Yes')}">
					<a href="${pageContext.request.contextPath}/redeemDetails">Redeem</a>
				</c:if>
			</c:forEach> <a style="float: right;"
			href="${pageContext.request.contextPath}/logout">Logout</a>
		</strong>
	</div>
	<div id="successHeader">
		<strong>Logged in successful for the user - <strong
			style="color: darkblue;"> ${userName}</strong>
		</strong> <strong style="float: right; padding-right: 15px"">${date}</strong><br />
	</div>

	<div class="table-align">
		<table>
			<caption>
				<caption>
					<tr>
						<th scope="col">AccountNo</th>
						<th scope="col">UserName</th>
						<th scope="col">LoginId</th>
						<th scope="col">PremiumUser</th>
						<th scope="col">BalanceAmount</th>
					</tr>
					<tr>
						<c:forEach var="userDetails" items="${userDetails}">
							<tr>
								<td>${userDetails.getAccountNo()}</td>
								<td>${userDetails.getUserName()}</td>
								<td>${userDetails.getLoginId()}</td>
								<td>${userDetails.getPremiumUser()}</td>
								<td>${userDetails.getBalanceAmount()}</td>
							</tr>
						</c:forEach>
					</tr>
		</table>
	</div>
</body>
</html>