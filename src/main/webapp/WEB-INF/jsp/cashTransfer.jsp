<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cash Transfer</title>
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

	<div id="successHeader">
		<strong>Cash Transfer </strong>
	</div>

	<div class="center-square">
		<h2 style="text-align: center;">Account Details</h2>
		<div id="error" style="color: red">${error}</div>
		<form action=${pageContext.request.contextPath}/cashTransferSuccess
			method="POST">
			<div style="text-align: center;">
				<label for="accountNumber"> <strong
					style="margin: 0px 0px 10px 0px;"> Account : </strong></label>&nbsp;&nbsp;
				${accountNo}<br /> <br /> <label for="balance"><strong>Balance
						: </strong></label>&nbsp;&nbsp; ${balanceAmount }<br /> <br /> <label
					for="lbltransferType"> <strong
					style="margin: 0px 0px 10px 0px;"> Transfer Type : </strong></label>&nbsp;&nbsp;


				<select name="transferType" id=transferType>
					<c:forEach items="${listTransferType}" var="listTransferType">
						<option value="${listTransferType}">${listTransferType}</option>
					</c:forEach>
				</select> <br /> <br> <br> <label for="userId"><strong
					style="margin: 0px 0px 10px 0px;"> To Account : </strong> </label>&nbsp;&nbsp;

				<select name="toAccountNo" id=toAccountNo>
					<c:forEach items="${toAccountNumbers}" var="toAccountNumbers">
						<option value="${toAccountNumbers}">${toAccountNumbers}</option>
					</c:forEach>
				</select> <br /> <br /> <br /> <label for="amount"><strong
					style="margin: 0px 0px 10px 0px;"> Amount : </strong></label>&nbsp;&nbsp; <input
					type="text" id="amount" name="amount" required mxlength="30"
					autofocus="false"><br /> <br> <label for="remarks"><strong
					style="margin: 0px 0px 10px 0px;"> Remarks : </strong></label>&nbsp;&nbsp;
				<input type="text" id="remarks" name="remarks" mxlength="30"
					autofocus="false"><br />


				<div style="margin-left: 6%; padding-top: 10px;">
					<input type="submit" name="submit" id="Submit" value="Submit"
						autofocus=" false" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>


