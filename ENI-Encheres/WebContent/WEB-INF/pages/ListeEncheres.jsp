<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/includes/Header.jsp"></jsp:include>
<body>
	<jsp:include page="../includes/Navbar.jsp"></jsp:include>
	<script type="text/javascript" src="js/ListeEnchere.js"></script>
	<div class="container">
		<div class="alert alert-dark mt-5" role="alert">
			<h1>Listes des encheres</h1>
			<hr>
			<label for="filtre">Filtre :</label>
			<div class="row">
				<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
					<input type="text" class="form-control" id="filtre" name="filtre"
						placeholder="Le nom de l'article contient...">
				</div>
				<div
					class="input-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6 mb-3">
					<div class="input-group-prepend">
						<label class="input-group-text" for="inputGroupSelect">Catégorie</label>
					</div>
					<select class="custom-select" id="inputGroupSelect" name="categ">
						<option value="0" selected>Toutes</option>
						<c:if test="${categories.size()>0}">
							<c:forEach var="categorie" items="${categories}">
								<c:choose>
									<c:when test="${identifiant != null}">
										<option href="" value="${categorie.noCategorie}">${categorie.libelle}</option>
									</c:when>
									<c:otherwise>
										<option value="${categorie.noCategorie}">${categorie.libelle}</option>
									</c:otherwise>
								</c:choose>

							</c:forEach>
						</c:if>
					</select>
				</div>
			</div>
			<c:if test="${identifiant != null && motdepasse != null}">
				<div class="row">
					<div class="btn-group btn-group-toggle col-12"
						data-toggle="buttons">
						<label class="btn btn-secondary col-6" id="achat_btn"> <input
							type="radio" name="options" id="achat" autocomplete="off">
							Achats
						</label> <label class="btn btn-secondary col-6" id="vente_btn"> <input
							type="radio" name="options" id="vente" autocomplete="off">
							Mes ventes
						</label>
					</div>
				</div>
				<div class="row col-12 pt-3">
					<div class="col-6">
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input"
								id="encheres_ouvertes"> <label
								class="custom-control-label" for="encheres_ouvertes">Enchères
								ouvertes</label>
						</div>
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input"
								id="encheres_encours"> <label
								class="custom-control-label" for="encheres_encours">Mes
								enchères en cours</label>
						</div>
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input"
								id="encheres_remportees"> <label
								class="custom-control-label" for="encheres_remportees">Mes
								enchères remportées</label>
						</div>
					</div>
					<div class="col-6">
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input"
								id="ventes_encours"> <label class="custom-control-label"
								for="ventes_encours">Mes ventes en cours</label>
						</div>
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input"
								id="ventes_non_debutees"> <label
								class="custom-control-label" for="ventes_non_debutees">Ventes
								non débutées</label>
						</div>
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input"
								id="ventes_terminees"> <label
								class="custom-control-label" for="ventes_terminees">Ventes
								terminées</label>
						</div>
					</div>
				</div>
			</c:if>
			<hr>
			<div class="row" id="listeArticle"></div>
		</div>

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