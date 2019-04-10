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
		<form method="post" action="${pageContext.request.contextPath}/seConnecter">
		
			<div class="alert alert-dark mt-5" role="alert">
				<h1>Se connecter</h1>
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
				    	<label for="identifiant">Identifiant :</label>
				    	<input type="text" class="form-control" name="identifiant" id="identifiant" placeholder="Identifiant" value="${pseudo}">
					</div>
					<div class="form-group col-12">
				    	<label for="motdepasse">Mot de passe :</label>
				    	<input type="password" class="form-control" name="motdepasse" id="motdepasse" placeholder="Mot de passe" value="${mdp}">
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<button type="submit" class="btn btn-block btn-success">Connexion</button>
						<div class="input-group mb-3">
						  <div class="input-group-prepend">
						    <div class="input-group-text">
						      <input type="checkbox" id="sesouvenir" name="sesouvenir" <c:if test="${sesouvenir}">checked="checked"</c:if>>
						    </div>
						  </div>
						  <input type="text" class="form-control" value="Se souvenir de moi" disabled>
						</div>
					</div>
					<div class="col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
						<a class="btn btn-block btn-warning" href="${pageContext.request.contextPath}/mdpOublie">Mot de passe oublié</a>
					</div>
				</div>
				<div class="pt-3 pt-sm-3 pt-md-0 pt-lg-0 pt-xl-0">
					<a class="btn btn-block btn-success" href="${pageContext.request.contextPath}/creerCompte">Créer un compte</a>
				</div>
			</div>
		</form>
		
	</div>

</body>
</html>