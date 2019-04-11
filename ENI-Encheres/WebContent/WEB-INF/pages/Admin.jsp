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
			<h4 class="pb-2">Utilisateurs</h4>
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
						</tr>
					</thead>
					<c:forEach var="user" items="${listUser}">
						<tr onclick="modifUser('${user.pseudo}','${user.motDePasse}')">
							<td>${user.pseudo}</td>
							<td>${user.nom}</td>
							<td>${user.prenom}</td>
							<td>${user.email}</td>
							<td>${user.telephone}</td>
							<td>${user.rue}</td>
							<td>${user.codePostal}</td>
							<td>${user.ville}</td>
							<td>${user.motDePasse}</td>
							<td>${user.credit}</td>
							<td><c:if test="${user.administrateur}"><center><i class="fas fa-check" ></i></center></c:if></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<hr>
			<h4 class="pb-2">Catégories</h4>
			<div class="row">
				<div class="col-6">
					<c:if test="${listCateg.size()>0}">
						<table id="tableCategories" class="table" style="overflow: auto!important; width: 100%;">
							<thead class="thead-dark">
								<tr>
									<th>Numéro</th>
									<th>Libelle</th>
								</tr>
							</thead>
							<c:forEach var="categorie" items="${listCateg}">
								<tr>
									<td>${categorie.noCategorie}</td>
									<td>${categorie.libelle}</td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</div>
				<div class="col-6">
					<form method="post" action="${pageContext.request.contextPath}/AdminPage">
						<h5 class="pb-2">Ajout d'une catégorie :</h5>
						<hr>
						<div class="form-group col-12">
					    	<label for="categorie" class="font-weight-bold">Libelle de la catégorie :</label>
							<input type="text" class="form-control" id=categorie name="categorie" placeholder="Libelle" required>
						</div>
						<div class="col-12 pb-3">
					    	<button type="submit" class="btn btn-block btn-success font-weight-bold">Ajouter</button>
						</div>
					</form>
				</div>
			</div>
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