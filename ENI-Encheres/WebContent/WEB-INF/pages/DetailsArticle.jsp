<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/includes/Header.jsp"></jsp:include>
<script type="text/javascript" src="js/DetailArticle.js"></script>
<body>
	<jsp:include page="../includes/Navbar.jsp"></jsp:include>
	<div class="container">
		<form method="post"
			action="${pageContext.request.contextPath}/detailEnchere">
			<div class="alert alert-dark mt-5" role="alert">
				<h1>Détail vente</h1>
				<hr>

				<c:if test="${succes != null}">
					<div class="alert alert-success">${succes}</div>
				</c:if>
				<c:if test="${echec != null}">
					<div class="alert alert-danger">${echec}</div>
				</c:if>

				<div class="row">
					<div
						class="col-12 col-sm-12 col-md-4 col-lg-4 col-xl-4 px-3 pb-3 pt-2 mb-auto">
						<img src="https://via.placeholder.com/150" class="card-img"
							alt="...">
					</div>
					<div class="col-12 col-sm-12 col-md-8 col-lg-8 col-xl-8">
						<input type="hidden" class="form-control" id="id" name="id"
							value="${formulaire.no_article}">
							<input type="hidden" class="form-control" id="paye" name="paye"
							value="${formulaire.paye}">
						<div class="row">
							<h3 class="col-12 mb-4">${formulaire.nom_article}</h3>
						</div>
						<div class="row">
							<div class="font-weight-bold col-12 col-sm-12 col-md-4 col-lg-4 col-xl-4 m-auto pr-0">Desciption :</div>
							<div class="col-12 col-sm-12 col-md-8 col-lg-8 col-xl-8 px-3 py-1">${formulaire.description}</div>
						</div>
						<div class="row">
							<div class="font-weight-bold col-12 col-sm-12 col-md-4 col-lg-4 col-xl-4 m-auto pr-0">Catégorie :</div>
							<div class="col-12 col-sm-12 col-md-8 col-lg-8 col-xl-8 px-3 py-1">${formulaire.categorieArticle.libelle}</div>
						</div>
						<div class="row">
							<div class="font-weight-bold col-12 col-sm-12 col-md-4 col-lg-4 col-xl-4 m-auto pr-0">Meilleur offre :</div>
							<c:choose>
								<c:when test="${!empty enchere}">
									<div class="col-12 col-sm-12 col-md-8 col-lg-8 col-xl-8 px-3 py-1">${formulaire.concerne.montant_enchere}
										points par ${enchere.encherit.pseudo}</div>
								</c:when>
								<c:otherwise>
									<div class="col-12 col-sm-12 col-md-8 col-lg-8 col-xl-8 px-3 py-1">Pas d'offre en cours</div>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="row">
							<div class="font-weight-bold col-12 col-sm-12 col-md-4 col-lg-4 col-xl-4 m-auto pr-0">Mise à prix :</div>
							<div class="col-12 col-sm-12 col-md-8 col-lg-8 col-xl-8 px-3 py-1">${formulaire.prix_initial}
								points</div>
						</div>
						<div class="row">
							<div class="font-weight-bold col-12 col-sm-12 col-md-4 col-lg-4 col-xl-4 mt-1 mb-auto pr-0">Fin de l'enchère
								:</div>
							<div class="col-12 col-sm-12 col-md-8 col-lg-8 col-xl-8 px-3 py-1">
								<input type="hidden" id="dateTimer" value="${formulaire.date_fin_encheres}">
								<fmt:formatDate value="${formulaire.date_fin_encheres}"
									pattern="dd/MM/yyyy à HH:mm" /><div id="demo"></div> 
							</div>
						</div>
						<div class="row">
							<div class="font-weight-bold col-12 col-sm-12 col-md-4 col-lg-4 col-xl-4 m-auto pr-0">Retrait :</div>
							<div class="col-12 col-sm-12 col-md-8 col-lg-8 col-xl-8 px-3 py-1">${retrait.rue}
								${retrait.code_postal} ${retrait.ville}</div>
						</div>
						<div class="row">
							<div class="font-weight-bold col-12 col-sm-12 col-md-4 col-lg-4 col-xl-4 m-auto pr-0">Vendeur :</div>
							<div class="col-12 col-sm-12 col-md-8 col-lg-8 col-xl-8 px-3 py-1">${formulaire.vendeur.pseudo}</div>
						</div>
						<c:if test="${succes == null && echec == null && identifiant != enchere.encherit.pseudo}">
							<div class="row">
								<div class="font-weight-bold col-12 col-sm-12 col-md-4 col-lg-4 col-xl-4 m-auto pr-0">Ma proposition :</div>
								<div class="col-12 col-sm-12 col-md-8 col-lg-8 col-xl-8 px-3 py-1">
									<input type="number" class="form-control" id="solde"
										name="solde" required>
								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-12">
									<button type="submit" class="btn btn-block btn-success font-weight-bold">Enchérir</button>
								</div>
							</div>
						</c:if>
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