<meta charset="utf-8" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="lib/bootstrap/js/bootstrap.min.js">
<link rel="stylesheet" href="lib/jquery/js/jquery.min.js">
<link rel="shortcut icon" href="WEB-INF/images/favicon.ico">
<div class="navbar navbar-inverse" style="width: 80%; margin: 0 auto; text-align: center;">
	<div class="navbar-inner">
		<div class="container-fluid">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</a>
			<a class="brand" href="#">
				Bank Service
			</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li class="divider-vertical"></li>
					<li>
						<a href="accounts">
							<i class="icon-home icon-white"></i>
							 Home
						</a>
					</li>
					<li class="divider-vertical"></li>
					<li>
						<a href="create-account-view">
							<i class="icon-envelope icon-white"></i>
							 Create account
						</a>
					</li>
					<li class="divider-vertical"></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<br/>
<br/>

<c:if test="${!(message eq null)}">
	<div class="alert">
	  <button type="button" class="close" data-dismiss="alert">&times;</button>
	  <strong> Incoming message : </strong>${message}
	</div>
</c:if>
