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
		<form method="post"
			action="${pageContext.request.contextPath}/detailEnchere">
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
					<input type="hidden" class="form-control" id="id"
								name="id" value="${formulaire.no_article}">
						<div class="row">
							<h3 class="col-12 mb-4">${formulaire.nom_article}</h3>
						</div>
						<div class="row">
							<div class="font-weight-bold col-4">Desciption :</div>
							<div class="col-8 px-3 py-1">${formulaire.description}</div>
						</div>
						<div class="row">
							<div class="font-weight-bold col-4 m-auto">Catégorie :</div>
							<div class="col-8 px-3 py-1">${formulaire.categorieArticle.libelle}</div>
						</div>
						<div class="row">
							<div class="font-weight-bold col-4 m-auto">Meilleur offre :</div>
							<c:choose>
								<c:when test="${!empty enchere}">
									<div class="col-8 px-3 py-1">${formulaire.concerne.montant_enchere} pts par ${enchere.encherit.pseudo}</div>
								</c:when>
								<c:otherwise>
									<div class="col-8 px-3 py-1">Pas d'offre en cours</div>
								</c:otherwise>

							</c:choose>

						</div>
						<div class="row">
							<div class="font-weight-bold col-4 m-auto">Mise à prix :</div>
							<div class="col-8 px-3 py-1">${formulaire.prix_initial} points</div>
						</div>
						<div class="row">
							<div class="font-weight-bold col-4 m-auto">Fin de l'enchère
								:</div>
							<div class="col-8 px-3 py-1">${formulaire.date_fin_encheres}</div>
						</div>
						<div class="row">
							<div class="font-weight-bold col-4 m-auto">Retrait :</div>
							<div class="col-8 px-3 py-1">${retrait.rue}
								${retrait.code_postal} ${retrait.ville}</div>
						</div>
						<div class="row">
							<div class="font-weight-bold col-4 m-auto">Vendeur :</div>
							<div class="col-8 px-3 py-1">${formulaire.vendeur.pseudo}</div>
						</div>
						<div class="row">
							<div class="font-weight-bold col-4 m-auto">Ma proposition :</div>
							<div class="col-8 px-3 py-1">
								<input type="number" class="form-control" id="solde"
								name="solde" required>
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