<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:setLocale value="fr" />
<fmt:setBundle basename="login" />
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="//bootswatch.com/flatly/bootstrap.min.css" />
<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="script/login.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><fmt:message key="window_title" /></title>
</head>
<body>
	<div class="container">
		<form method="post" action="Authenticate">
			<div class="form-group">
				<label for="email"><fmt:message key="email" /></label> <input
					class="form-control" type="email" id="email" name="email"
					placeholder="<fmt:message key="placeholder_email"/>" />
			</div>
			<div class="form-group">
				<label for="password"><fmt:message key="password" /></label> <input
					class="form-control" type="password" id="password" name="password"
					placeholder="<fmt:message key="placeholder_password"/>" />
			</div>
			<div class="form-group">
				<input class="btn btn-primary" type="submit"
					value="<fmt:message key="submit"/>" />
			</div>
		</form>
	</div>
</body>
</html>