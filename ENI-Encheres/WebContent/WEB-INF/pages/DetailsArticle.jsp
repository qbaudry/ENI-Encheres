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
			action="${pageContext.request.contextPath}/ajoutArticle">
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
							<div class="form-group col-12">
								<label for="identifiant">Article :</label> <input type="text"
									class="form-control" id="article" name="article" placeholder=""
									value="" readonly>

							</div>
							<div class="form-group col-12">
								<label for="description">Desciption :</label>
								<textarea class="form-control" id="description"
									name="description" placeholder="" value="" readonly></textarea>
							</div>
							<div class="form-group col-12 col-lg-6 col-xl-6">
								<label for="categorie">Catégorie :</label> <input type="text"
									class="form-control" id="categorie" name="categorie"
									placeholder="" value="" readonly>
							</div>
							<div class="form-group col-12">
								<label for="prix">Meilleur offre :</label> <input type="number"
									class="form-control" id="prix" name="prix" placeholder=""
									value="" readonly>
							</div>

							<div class="form-group col-12 col-lg-6 col-xl-6">
								<label for="prix">Mise à prix :</label> <input type="number"
									class="form-control" id="maprix" name="maprix" placeholder=""
									value="" readonly>
							</div>
							<div class="form-group col-12 col-lg-6 col-xl-6">
								<label for="fin">Fin de l'enchère :</label> <input
									type="datetime-local" class="form-control" name="fin" id="fin"
									required>
							</div>
							<div class="form-group col-12 col-lg-6 col-xl-6">
								<label for="retrait">Retrait :</label> <input type="text"
									class="form-control" id="retrait" name="retrait" placeholder=""
									value="" readonly>
							</div>
							<div class="form-group col-12 col-lg-6 col-xl-6">
								<label for="vendeur">Vendeur :</label> <input type="text"
									class="form-control" id="vendeur" name="vendeur" placeholder=""
									value="" readonly>
							</div>

							<label for="pet-select">Ma Proposition:</label> <select
								id="pet-select">
								<option value="">--Please choose an option--</option>
								<option value="dog">Dog</option>
								<option value="cat">Cat</option>
								<option value="hamster">Hamster</option>
								<option value="parrot">Parrot</option>
								<option value="spider">Spider</option>
								<option value="goldfish">Goldfish</option>
							</select>
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