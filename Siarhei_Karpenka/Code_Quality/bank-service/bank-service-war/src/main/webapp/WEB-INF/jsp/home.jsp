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
	<body background="lib/images/background.jpg" style="color:white;">
		<%@include file="header.jsp" %>
		<div align="center">
			<table class="table price-table">
				<thead>
					<tr>
						<th>
							<a href="sort?param=id&type=asc"><i class="icon-arrow-up "></i></a>
								Id
							<a href="sort?param=id&type=desc"><i class="icon-arrow-down"/></i></a>
						</th>
						<th>
							<a href="sort?param=name&type=asc"><i class="icon-arrow-up"></i></a>
								Client name
							<a href="sort?param=name&type=desc"><i class="icon-arrow-down"/></i></a>
						</th>
						<th>
							<a href="sort?param=surname&type=asc"><i class="icon-arrow-up"></i></a>
								Client surname
							<a href="sort?param=surname&type=desc"><i class="icon-arrow-down"/></i></a>
						</th>
						<th>Currency exchange</th>
						<th>Delete account</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="account" items="${accounts}">
						<tr>
							<td>
								<a class="badge badge-info" href="account-view?accountId=${account.id}">${account.id}</a>
							</td>
							<td>
								${account.person.name}
							</td> 
							<td>${account.person.surname}</td>
							<td>
								<a href="account-view?accountId=${account.id}" data-toggle="modal">
									<i class="icon-pencil"></i>
								</a>
							</td>
							<td>
								<a href="delete?accountId=${account.id}" data-toggle="modal">
									<i class="icon-remove"></i>
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pagination" style="text-align: center;">
			<ul>
				<c:choose>
					<c:when test="${pageNum == 1}">
						<li class="disabled"><a href="#" >Prev</a></li>
					</c:when>
					<c:otherwise>
						<li class="disabled"><a href="accounts?pagePosition=Prev&pageNum=${pageNum}">Prev</a></li>
					</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="1" end="${totalPageNumber}">
					<c:choose>
						<c:when test="${pageNum == i}">
							<li class="disabled"><a href="#">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="accounts?pageNum=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${pageNum == totalPageNumber}">
						<li class="disabled"><a href="#" style="color:grey">Next</a></li>
					</c:when>
					<c:otherwise>
						<li class="disabled"><a href="accounts?pagePosition=Next&pageNum=${pageNum}">Next</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<%@include file="footer.jsp" %>
	</body>
</html>

