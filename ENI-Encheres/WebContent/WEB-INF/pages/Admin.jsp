<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/includes/Header.jsp"></jsp:include>
<script type="text/javascript" src="js/Admin.js"></script>
<body>
	<jsp:include page="../includes/Navbar.jsp"></jsp:include>
	<div class="container-fluid px-5">
		<div class="alert alert-dark mt-5" role="alert">
			<h1>Administration</h1>
			<hr>
			<c:if test="${listUser.size()>0}">
				<table id="tableUsers" class="table" style="overflow: auto!important; display: inline-block!important; width: 100%;">
					<thead class="thead-dark">
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
							<th>Admin</th>
							<th></th>
						</tr>
					</thead>
					<c:forEach var="user" items="${listUser}">
						<tr>
							<td onclick="modifUser('${user.pseudo}','${user.motDePasse}')">${user.pseudo}</th>
							<td onclick="modifUser('${user.pseudo}','${user.motDePasse}')">${user.nom}</td>
							<td onclick="modifUser('${user.pseudo}','${user.motDePasse}')">${user.prenom}</td>
							<td onclick="modifUser('${user.pseudo}','${user.motDePasse}')">${user.email}</td>
							<td onclick="modifUser('${user.pseudo}','${user.motDePasse}')">${user.telephone}</td>
							<td onclick="modifUser('${user.pseudo}','${user.motDePasse}')">${user.rue}</td>
							<td onclick="modifUser('${user.pseudo}','${user.motDePasse}')">${user.codePostal}</td>
							<td onclick="modifUser('${user.pseudo}','${user.motDePasse}')">${user.ville}</td>
							<td onclick="modifUser('${user.pseudo}','${user.motDePasse}')">${user.motDePasse}</td>
							<td onclick="modifUser('${user.pseudo}','${user.motDePasse}')">${user.credit}</td>
							<td onclick="modifUser('${user.pseudo}','${user.motDePasse}')"><c:if test="${user.administrateur}"><center><i class="fas fa-check" ></i></center></c:if></td>
							<td onclick="deleteUser('${user.pseudo}','${user.motDePasse}')"><center><i class="fas fa-trash"></i></center></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>
</body>
<div class="modal" id="modalProfil">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="titreModal"></h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body" id="corpsModal">
						
					</div>
				</div>
			</div>
		</div>
</html>