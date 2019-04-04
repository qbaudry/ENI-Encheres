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
		<form method="post" action="${pageContext.request.contextPath}/Profil">
			<div class="alert alert-dark mt-5" role="alert">
				<h1>Mon Profil</h1>
				<hr>
				<% if(request.getAttribute("error") != null) {%>
   				 <div class="alert alert-danger"><%=request.getAttribute("error")%></div>
				<%}%>
				<% if(request.getAttribute("congret") != null) {%>
   				 <div class="alert alert-success"><%=request.getAttribute("congret")%></div>
				<%}%>
				<% if(request.getAttribute("supress") != null) {%>
   				 <div class="alert alert-success"><%=request.getAttribute("supress")%></div>
				<%}%>
				<div class="row">
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="pseudo">Pseudo :</label>
				    	<input type="text" class="form-control" id="pseudo" name="pseudo" value="<%=request.getAttribute("pseudo")%>" >
					</div>
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="nom">Nom :</label>
				    	<input type="text" class="form-control" id="nom" name="nom" value="<%=request.getAttribute("nom")%>" >
					</div>
				</div>
				<div class="row">
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="prenom">Prénom :</label>
				    	<input type="text" class="form-control" id="prenom" name="prenom" value="<%=request.getAttribute("prenom")%>" >
					</div>
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6" >
				    	<label for="nom">Email :</label>
				    	<input type="email" class="form-control" id="email" name="email" value="<%=request.getAttribute("email")%>" >
					</div>
				</div>
				<div class="row">
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="telephone">Téléphone :</label>				    	
				    	<input type="text" pattern="\d{10}" class="form-control" id="telephone" name="telephone" value="<%=request.getAttribute("telephone")%>" >
					</div>
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="rue">Rue :</label>
				    	<input type="text" class="form-control" id="rue" name="rue" value="<%=request.getAttribute("rue")%>" >
					</div>
				</div>
				<div class="row">
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="codepostal">Code postal :</label>
				    	<input type="text" class="form-control" id="codepostal" name="codepostal" value="<%=request.getAttribute("codepostal")%>" >
					</div>
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="ville">Ville :</label>
				    	<input type="text" class="form-control" id="ville" name="ville" value="<%=request.getAttribute("ville")%>" >
					</div>
				</div>
				<div class="row">
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="motdepasse">Mot de passe :</label>
				    	<input type="password" class="form-control" id="motdepasse" name="motdepasse" value="<%=request.getAttribute("motdepasse")%>" >
					</div>
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="motdepasse2">Crédit :</label>
				    	<input type="number" class="form-control" id="credit" name="credit"  value="<%=request.getAttribute("credit")%>" readonly>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-6">
				    	<button type="submit" class="btn btn-block btn-success">Modifier</button>
					</div>
					<div class="col-6">
						<a class="btn btn-block btn-danger" href="${pageContext.request.contextPath}/supprimerCompte">Supprimer</a>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>