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
            href="${pageContext.request.contextPath }/logout">Logout</a> <a
            style="float: right;" href="${pageContext.request.contextPath }/home">Home</a>
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
							<c:forEach var="tranactionDetails" items="${tranactionDetails}">
								<tr>
									<td>${tranactionDetails.getTransferType()}</td>
									<td>${tranactionDetails.getToAccountNumber()}</td>
									<td>${tranactionDetails.getBalaceAmount()}</td>
									<td>${tranactionDetails.getRemarks()}</td>
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
								<c:forEach var="tranactionDetails1"
									items="${tranactionDetails1}">
									<tr>
										<td>${tranactionDetails1.getTransferType()}</td>
										<td>${tranactionDetails1.getFromAccountNumber()}</td>
										<td>${tranactionDetails1.getBalaceAmount()}</td>
										<td>${tranactionDetails1.getRemarks()}</td>
									</tr>
								</c:forEach>
							</tr>
				</table>
			</div>
</body>
</html>