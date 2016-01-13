<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="login" />

<fmt:message key="window_title" var="window_title" />

<jsp:include page="header.jsp">
	<jsp:param name="window_title" value="${window_title}" />
</jsp:include>

<jsp:include page="notification.jsp" />
<script src="script/login.js"></script>
<script>
<!--
	addTranslation('login', 'notifications', {
		invalidUser : {
			title : "<fmt:message key="notification.invalid_user.title" />",
			content : "<fmt:message key="notification.invalid_user.content" />"
		}
	});
	-->
</script>
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

<jsp:include page="footer.jsp" />