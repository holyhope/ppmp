<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="header" />

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="//bootswatch.com/flatly/bootstrap.min.css" />
<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="script/lang.js"></script>
<title><fmt:message key="window_title" /> -
	${param.window_title}</title>
</head>
<body>
	<div class="container">
		<h1>
			<fmt:message key="site_name" />
			<small>- <fmt:message key="site_slogan" /></small>
		</h1>