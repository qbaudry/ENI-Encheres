<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/includes/Header.jsp"></jsp:include>
<body>
	<jsp:include page="../includes/Navbar.jsp"></jsp:include>
	<div class="container">
		<form method="post" action="${pageContext.request.contextPath}/mdpOublie">
		
			<div class="alert alert-dark mt-5" role="alert">
				<h1>Mot de passe oublié ?</h1>
				<hr>
				<% if(request.getAttribute("error") != null) {%>
   				 <div class="alert alert-danger"><%=request.getAttribute("error")%></div>
				<%}%>
				<% if(request.getAttribute("congret") != null) {%>
   				 <div class="alert alert-success"><%=request.getAttribute("congret")%></div>
				<%}%>
				<br>
				<div class="row">
					<div class="form-group col-12">
				    	<label for="identifiant">Email :</label>
				    	<input type="text" class="form-control" name="mail" id="mail" placeholder="exemple@example.com">
					</div>
					<div class="form-group col-12">
				    	<label for="identifiant">Pseudo :</label>
				    	<input type="text" class="form-control" name="pseudo" id="pseudo" placeholder="Pseudo">
					</div>
				</div>
				<hr>
				<div class="row pb-3">
					<div class="col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<button type="submit" class="btn btn-block btn-success">Envoyer</button>
					</div>
				</div>
			</div>
		</form>
		
	</div>

</body>
</html>