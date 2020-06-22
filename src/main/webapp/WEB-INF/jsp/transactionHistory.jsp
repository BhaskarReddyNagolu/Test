<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Transaction History</title>
<!-- Static content -->
<link rel="stylesheet" href="resources/css/style.css" type="text/css"
	media="screen" />
</head>
<body>
	<div class="topnav">
		<strong> <a style="float: right;"
			href=${pageContext.request.contextPath}/logout>Logout</a> <a
			style="float: right;" href=${pageContext.request.contextPath}/home>Home</a>
		</strong>
	</div>


	<label id="lblAccountNo"> Account No: ${accountNo}</label>
	<label id="lblAmount"> Balance Amount: ${balanceAmount} </label>
	<div id="successHeader">
		<strong>Debit Transaction History </strong>
	</div>

	<div class="table-align">
		<div class="table-align">
			<table>
				<caption>
					<caption>
						<tr>
							<th scope="col">Transfer Type</th>
							<th scope="col">To Account</th>
							<th scope="col">Amount</th>
							<th scope="col">Remarks</th>
						</tr>
						<tr>
							<c:forEach var="accountTransactonsForDebit"
								items="${accountTransactonsForDebit}">
								<tr>
									<td>${accountTransactonsForDebit.getTransferType()}</td>
									<td>${accountTransactonsForDebit.getToAccountNumber()}</td>
									<td>${accountTransactonsForDebit.getBalaceAmount()}</td>
									<td>${accountTransactonsForDebit.getRemarks()}</td>
								</tr>
							</c:forEach>
						</tr>
			</table>
		</div>

		<div id="successHeader">
			<strong>Credit Transaction History </strong>
		</div>
		<div class="table-align">
			<div class="table-align">
				<table>
					<caption>
						<caption>
							<tr>
								<th scope="col">Transfer Type</th>
								<th scope="col">From Account</th>
								<th scope="col">Amount</th>
								<th scope="col">Remarks</th>
							</tr>
							<tr>
								<c:forEach var="accountTransactonsForCredit"
									items="${accountTransactonsForCredit}">
									<tr>
										<td>${accountTransactonsForCredit.getTransferType()}</td>
										<td>${accountTransactonsForCredit.getFromAccountNumber()}</td>
										<td>${accountTransactonsForCredit.getBalaceAmount()}</td>
										<td>${accountTransactonsForCredit.getRemarks()}</td>
									</tr>
								</c:forEach>
							</tr>
				</table>
			</div>
</body>
</html>