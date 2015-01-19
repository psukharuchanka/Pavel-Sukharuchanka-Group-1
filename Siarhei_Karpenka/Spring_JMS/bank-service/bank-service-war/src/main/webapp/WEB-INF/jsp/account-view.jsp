<meta charset="utf-8" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
<link rel="shortcut icon" href="WEB-INF/images/favicon.ico">
<script src='lib/jquery/js/jquery.min.js'></script>
<script src='lib/bootstrap/js/bootstrap.min.js'></script>
<html>
	<head>
		<title>Bank Service by Siarhei Karpenka</title>
	</head>
	<body background="lib/images/background.jpg">
	<%@include file="header.jsp" %>
		<div class="span3">
			<div class="well">
				<h2 class="text-warning">${account.person.name}</h2>
				<h2 class="text-warning">${account.person.surname}</h2>
			</div>
		</div>
		<c:forEach items="${account.bills}" var="bill">
			<div class="span3">
				<div>
					<span class="badge badge-success">${bill.currency.name}</span>
					<div class="pull-right">
						<span class="label label-info">${bill.amount}</span>
					</div>
				</div>
				<form accept-charset="UTF-8" action="exchange?accountId=${account.id}&billId=${bill.id}" method="POST">
					<select name="currencyId" class="span4">
				    	<c:forEach var="currency" items="${currencies}">
				    		<c:if test="${currency.name ne bill.currency.name}">
				     			<option value="${currency.id}">${currency.name}</option>
				     		</c:if>
				    	</c:forEach>
				    </select>
				    <button type="submit" class="btn">Exchange</button>
			    </form>
				<hr>
				<!--  p>
					<a class="btn btn-large" href="&currencyId=${currency}"><i
						class="icon-ok"></i>Exchange</a>
				</p-->
			</div>
		</c:forEach>
		<%@include file="footer.jsp" %>
	</body>
</html>