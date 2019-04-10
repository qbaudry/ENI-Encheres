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
			action="${pageContext.request.contextPath}/editionArticle">
			<div class="alert alert-dark mt-5" role="alert">
				<h1>Editer un Article</h1>
				<hr>
				<div class="row">
					<div
						class="col-12 col-sm-12 col-md-4 col-lg-4 col-xl-4 px-3 pb-3 pt-2 mb-auto">
						<img src="https://via.placeholder.com/150" class="card-img"
							alt="...">
					</div>
					<div class="col-12 col-sm-12 col-md-8 col-lg-8 col-xl-8">
						<div class="row">
						<input type="hidden" class="form-control" id="id" name="id"
							value="${formulaire.no_article}">
							<div class="form-group col-12">
								<label for="identifiant" class="font-weight-bold">Article :</label>
										<input type="text" class="form-control" id="article"
											name="article" placeholder="Nom article"
											value="${article.nom_article}" required>

							</div>
							<div class="form-group col-12">
								<label for="description" class="font-weight-bold">Desciption :</label>
										<textarea  class="form-control" id="description"
											name="description" placeholder="Description de l'article" 
											required>${article.description}</textarea>
							</div>
							<div class="form-group col-12 col-lg-6 col-xl-6">
								<label for="motdepasse" class="font-weight-bold">Catégorie :</label> <select
									class="custom-select" id="inputGroupSelect" name="categorie">
									<c:if test="${categories.size()>0}">
										<c:forEach var="categorie" items="${categories}">
											<option value="${categorie.noCategorie}">${categorie.libelle}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
							<div class="form-group col-12 col-lg-6 col-xl-6">
								<label for="image" class="font-weight-bold">Photo de l'article :</label>
								<div class="custom-file">
									<input type="file" class="custom-file-input" id="image"
										accept="image/png, image/jpeg" name="image" disabled> <label
										class="custom-file-label" for="image">Télécharger...</label>
								</div>
							</div>
							<div class="col-12 mb-3">
								<div class="row">
									<div class="col-12 col-lg-6 col-xl-6">
										<label for="prix" class="font-weight-bold">Mise à prix :</label>
												<input type="number" class="form-control" id="prix"
													name="prix" placeholder="Prix de l'article"
													value="${article.prix_initial}" disabled>
									</div>
									<div class="col-12 col-lg-6 col-xl-6">
										<label for="prix" class="font-weight-bold">Meilleur offre :</label>
										<c:choose>
											<c:when test="${!empty article.concerne.montant_enchere}">
												<input type="number" class="form-control"
													placeholder="Prix de l'article"
													value="${article.concerne.montant_enchere}" readonly>
											</c:when>
											<c:otherwise>
												<input type="text" class="form-control"
													value="Pas d'offre en cours" readonly>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
							<div class="form-group col-12 col-lg-6 col-xl-6">
								<label for="debut" class="font-weight-bold">Début de l'enchère :</label> <input
									type="datetime-local" class="form-control" name="debut"
									id="debut" value="${datedebut}">
							</div>
							<div class="form-group col-12 col-lg-6 col-xl-6">
								<label for="fin" class="font-weight-bold">Fin de l'enchère :</label> <input
									type="datetime-local" class="form-control" name="fin" id="fin"
									value="${datefin}" required>
							</div>
							<div class="col-12 pt-3">
								<div class="card">
									<div class="card-header font-weight-bold">Retrait</div>
									<div class="card-body pt-0 pb-3">
										<div class="form-group row col-12 m-0 pt-3">
											<label class="col-12 col-lg-3 col-xl-3 m-auto pl-0 font-weight-bold" for="rue">Rue
												:</label> <input type="text"
												class="form-control col-12 col-lg-9 col-xl-9" name="rue"
												id="rue" placeholder="Rue" value="${retrait.rue}"
												required>
										</div>
										<div class="form-group row col-12 m-0 pt-3">
											<label class="col-12 col-lg-3 col-xl-3 m-auto pl-0 font-weight-bold"
												for="codepostal">Code Postal :</label> <input type="text"
												class="form-control col-12 col-lg-9 col-xl-9"
												name="codepostal" id="codepostal" placeholder="Code Postal"
												value="${retrait.code_postal}" required>
										</div>
										<div class="form-group row col-12 m-0 pt-3">
											<label class="col-12 col-lg-3 col-xl-3 m-auto pl-0 font-weight-bold"
												for="ville">Ville :</label> <input type="text"
												class="form-control col-12 col-lg-9 col-xl-9" name="ville"
												id="ville" placeholder="Ville" value="${retrait.ville}"
												required>
										</div>
									</div>
								</div>
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col-6">
								<button type="submit" class="btn btn-block btn-success font-weight-bold">Enregistrer</button>
							</div>
							<div class="col-6">
								<a class="btn btn-block btn-danger font-weight-bold"
									href="${pageContext.request.contextPath}/listeEncheres">Supprimer Article</a>
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