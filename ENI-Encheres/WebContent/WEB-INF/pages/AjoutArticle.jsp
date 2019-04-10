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
		<form method="post" action="${pageContext.request.contextPath}/ajoutArticle" 
			enctype="multipart/form-data">
			<div class="alert alert-dark mt-5" role="alert">
				<h1>Nouvelle vente</h1>
				<hr>
				<div class="row">
					<div
						class="col-12 col-sm-12 col-md-4 col-lg-4 col-xl-4 px-3 pb-3 pt-2 mb-auto">
						<img src="https://via.placeholder.com/150" class="card-img"
							alt="...">
					</div>
					<div class="col-12 col-sm-12 col-md-8 col-lg-8 col-xl-8">
						<div class="row">
							<div class="form-group col-12">
							
								<label for="identifiant">Article :</label>
								<c:choose>
									<c:when test="${!empty art}">
										<input type="text" class="form-control" id="article"
											name="article" placeholder="Nom article"
											value="${formulaire.nom_article}" required>
									</c:when>
									<c:otherwise>
										<input type="text" class="form-control" id="article"
											name="article" placeholder="Nom article" required>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="form-group col-12">
								<label for="description">Desciption :</label>
								<c:choose>
									<c:when test="${!empty art}">
										<textarea  class="form-control" id="description"
											name="description" placeholder="Description de l'article"
											value="${formulaire.description}" required></textarea>
									</c:when>
									<c:otherwise>
										<textarea class="form-control" id="description"
											name="description" placeholder="Description de l'article" required></textarea>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="form-group col-12 col-lg-6 col-xl-6">
								<label for="motdepasse">Catégorie :</label> <select
									class="custom-select" id="inputGroupSelect" name="categorie">
									<c:if test="${categories.size()>0}">
										<c:forEach var="categorie" items="${categories}">
											<option value="${categorie.noCategorie}">${categorie.libelle}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
							<div class="form-group col-12 col-lg-6 col-xl-6">
								<label for="image">Photo de l'article :</label>
								<div class="custom-file">
									<input type="file" class="custom-file-input" id="image"
										accept="image/png, image/jpeg" name="image" disabled> <label
										class="custom-file-label" for="image">Télécharger...</label>
								</div>
							</div>
							<div class="form-group col-12">
								<label for="prix">Mise à prix :</label>
								<c:choose>
									<c:when test="${!empty art}">
										<input type="number" class="form-control" id="prix"
											name="prix" placeholder="Prix de l'article"
											value="${formulaire.prix_initial}" required>
									</c:when>
									<c:otherwise>
										<input type="number" class="form-control" id="prix"
											name="prix" placeholder="Prix de l'article" required>
									</c:otherwise>
								</c:choose>
							</div>

							<div class="form-group col-12 col-lg-6 col-xl-6">
								<label for="debut">Début de l'enchère :</label> <input
									type="datetime-local" class="form-control" name="debut"
									id="debut" value="${debut}">
							</div>
							<div class="form-group col-12 col-lg-6 col-xl-6">
								<label for="fin">Fin de l'enchère :</label> <input
									type="datetime-local" class="form-control" name="fin" id="fin"
									required>
							</div>
							<div class="col-12 pt-3">
								<div class="card">
									<div class="card-header">Retrait</div>
									<div class="card-body pt-0 pb-3">
										<div class="form-group row col-12 m-0 pt-3">
											<label class="col-12 col-lg-3 col-xl-3 m-auto pl-0" for="rue">Rue
												:</label> <input type="text"
												class="form-control col-12 col-lg-9 col-xl-9" name="rue"
												id="rue" placeholder="Rue" value="${utilisateur.rue}"
												required>
										</div>
										<div class="form-group row col-12 m-0 pt-3">
											<label class="col-12 col-lg-3 col-xl-3 m-auto pl-0"
												for="codepostal">Code Postal :</label> <input type="text"
												class="form-control col-12 col-lg-9 col-xl-9"
												name="codepostal" id="codepostal" placeholder="Code Postal"
												value="${utilisateur.codePostal}" required>
										</div>
										<div class="form-group row col-12 m-0 pt-3">
											<label class="col-12 col-lg-3 col-xl-3 m-auto pl-0"
												for="ville">Ville :</label> <input type="text"
												class="form-control col-12 col-lg-9 col-xl-9" name="ville"
												id="ville" placeholder="Ville" value="${utilisateur.ville}"
												required>
										</div>
									</div>
								</div>
							</div>
						</div>
						<hr>
						<div class="row">
							<div class="col-6">
								<button type="submit" class="btn btn-block btn-success">Enregistrer</button>
							</div>
							<div class="col-6">
								<a class="btn btn-block btn-danger"
									href="${pageContext.request.contextPath}/listeEncheres">Annuler</a>
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