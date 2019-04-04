<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/includes/Header.jsp"></jsp:include>
<body>
	<%@ include file="../includes/Navbar.jsp" %>
	<div class="container">
		<div class="alert alert-danger mt-5" role="alert">
			<h1>Error</h1>
			<hr>
			<p>Vous n'êtes pas connecté !</p>
		</div>
	</div>
</body>
</html>