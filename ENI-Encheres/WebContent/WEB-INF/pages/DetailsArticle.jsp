<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/includes/Header.jsp"></jsp:include>
<body>
	<jsp:include page="../includes/Navbar.jsp"></jsp:include>
	<div class="container">
		<form method="post" action="${pageContext.request.contextPath}/ajoutArticle">
			<div class="alert alert-dark mt-5" role="alert">
				<h1>Détail vente</h1>
				<hr>
				<div class="row">
					<div
						class="col-12 col-sm-4 col-md-4 col-lg-4 col-xl-4 px-3 pb-3 mt-4 mb-auto">
						<img src="https://via.placeholder.com/150" class="card-img"
							alt="...">
					</div>
					<div class="col-12 col-sm-8 col-md-8 col-lg-8 col-xl-8">
						<div class="row">
				     		<h3 class="col-12">${formulaire.nom_article}</h3>
						</div>
						<div class="row">
							<div class="font-weight-bold col-4">Desciption :</div>
				     		<div class="col-8 p-1">${formulaire.description}</div>
						</div>
						<div class="row">
							<div class="font-weight-bold col-4 m-auto">Catégorie :</div>
				     		<div class="col-8 p-1">${formulaire.categorieArticle.libelle}</div>
						</div>
						<div class="row">
							<div class="font-weight-bold col-4 m-auto">Meilleur offre :</div>
				     		<div class="col-8 p-1">${formulaire.prix_vente}</div>
						</div>
						<div class="row">
							<div class="font-weight-bold col-4 m-auto">Mise à prix :</div>
				     		<div class="col-8 p-1">${formulaire.prix_initial}</div>
						</div>
						<div class="row">
							<div class="font-weight-bold col-4 m-auto">Fin de l'enchère :</div>
				     		<div class="col-8 p-1">${formulaire.date_fin_encheres}</div>
						</div>
						<div class="row">
							<div class="font-weight-bold col-4 m-auto">Retrait :</div>
				     		<div class="col-8 p-1">...</div>
						</div>
						<div class="row">
							<div class="font-weight-bold col-4 m-auto">Vendeur :</div>
				     		<div class="col-8 p-1">${formulaire.vendeur.pseudo}</div>
						</div>
						<div class="row mb-3">
							<div class="font-weight-bold col-4 m-auto">Ma proposition :</div>
				     		<div class="form-group col-8 m-0  p-1">
								<select class="custom-select" id="inputGroupSelect" name="categorie">
									<option value="dog">Dog</option>
									<option value="cat">Cat</option>
									<option value="hamster">Hamster</option>
									<option value="parrot">Parrot</option>
									<option value="spider">Spider</option>
									<option value="goldfish">Goldfish</option>
								</select>
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col-12">
								<button type="submit" class="btn btn-block btn-success">Enchérir</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
		<c:if test="${!empty listeCodesErreur}">
			<div class="alert alert-danger" role="alert">
				<strong>Erreur !</strong>
				<ul>
					<c:forEach var="code" items="${listeCodesErreur}">
						<li>${LecteurMessage.getMessageErreur(code)}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
	</div>
</body>
</html>