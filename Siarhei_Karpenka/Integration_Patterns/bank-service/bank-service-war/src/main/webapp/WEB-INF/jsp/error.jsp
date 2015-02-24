<!DOCTYPE style PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="shortcut icon" href="images/favicon.ico">
<script src="lib/jquery/js/jquery.min.js"></script>
<script src="lib/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
<html>
	<head>
		<style>
			.center {
				text-align: center;
				margin-left: auto;
				margin-right: auto;
				margin-bottom: auto;
				margin-top: auto;
			}
		</style>
		<title>Error page</title>
	</head>
	<body background="images/background.jpg">
		<div class="hero-unit center">
			<h1>
				Message 
				<small>
					<font face="Tahoma" color="red">
						${errorMessage}
					</font>
				</small>
			</h1>
			<br />
			<p>
				Use your browsers <b>Back</b> button to navigate to the page you have
				previously come from
			</p>
			<p>
				<b>
					Or you could just press this neat little button:
				</b>
			</p>
			<a href="accounts" class="btn btn-large btn-info">
				<i class="icon-home icon-white"></i>
				Home page
			</a>
		</div>
	</body>
</html>
