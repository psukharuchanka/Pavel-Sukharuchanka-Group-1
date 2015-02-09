<meta charset="utf-8" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
<link rel="shortcut icon" href="WEB-INF/images/favicon.ico">
<script src='lib/jquery/js/jquery.min.js'></script>
<script src='lib/bootstrap/js/bootstrap.min.js'></script>
<%@ taglib prefix="it" tagdir="/WEB-INF/tags"%>
<script>
	$(".dropdown-menu li a").click(function(){
	  var selText = $(this).text();
	  $(this).parents('.btn-group').find('.dropdown-toggle').html(selText+' <span class="caret"></span>');
	});
</script>
<html>
	<head>
		<title>Bank Service by Siarhei Karpenka</title>
	</head>
	<body background="lib/images/background.jpg">
	<%@include file="header.jsp" %>
		<div align="center">
			<form accept-charset="UTF-8" action="create" method="POST">
				<fieldset>
					<div class="span3">
						<div class="well">
							<h4 class="text-important">Person name</h4>
							<input type="text" name="name" id="name" size="30" autofocus required>
							<h4 class="text-important">Person surname</h4>
							<input type="text" name="surname" id="surname" size="30" required>
							<h4 class="text-important">Currency</h4>
	               	    	<select name="currency" class="span2">
						    	<c:forEach var="currency" items="${currencies}">
						     		<option value="${currency.name}">${currency.name}</option>
						    	</c:forEach>
						    </select>
						    <h4 class="text-important">Amount</h4>
							<input type="text" name="amount" id="amount" size="30" required>
							<button type="submit" class="btn">Create account</button>
				       </div>
				    </div>
				</fieldset>
			</form>
		</div>
		<%@include file="footer.jsp" %>
	</body>
</html>