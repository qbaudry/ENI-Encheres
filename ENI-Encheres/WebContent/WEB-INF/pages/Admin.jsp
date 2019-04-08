<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/includes/Header.jsp"></jsp:include>
	
	<link rel="stylesheet" type="text/css" href="css/Admin.css">
<body>
	<jsp:include page="../includes/Navbar.jsp"></jsp:include>
	<br>
	<script type="text/javascript" src="js/Admin.js"></script>
	<div class="container">
		<c:if test="${listUser.size()>0}">
			<table id="tableUsers" class="row-border" style="width:100%">
				<thead>
					<tr>
						<th>Pseudo</th>
						<th>Nom</th>
						<th>Prenom</th>
						<th>E-mail</th>
						<th>Téléphone</th>
						<th>Rue</th>
						<th>Code Postal</th>
						<th>Ville</th>
						<th>Mot De Passe</th>
						<th>Crédit</th>
						<th>Administrateur</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach var="user" items="${listUser}">
					<tr>
						<td onclick="modifUser(${user.pseudo},${user.motDePasse})">${user.pseudo}</th>
						<td>${user.nom}</td>
						<td>${user.prenom}</td>
						<td>${user.email}</td>
						<td>${user.telephone}</td>
						<td>${user.rue}</td>
						<td>${user.codePostal}</td>
						<td>${user.ville}</td>
						<td>${user.motDePasse}</td>
						<td>${user.credit}</td>
						<td><c:if test="${user.administrateur}">Oui</c:if></td>
						<td></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>