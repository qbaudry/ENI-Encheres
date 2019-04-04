<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/includes/Header.jsp"></jsp:include>
<body>
	<%@ include file="../includes/Navbar.jsp"%>
	<div class="container">
		<div class="alert alert-dark mt-5" role="alert">
			<h1>Nouvelle vente</h1>
			<hr>
			<div class="row">
				<div class="col-12 col-sm-4 col-md-4 col-lg-4 col-xl-4 px-3 pb-3 mt-4 mb-auto">
					<img src="https://via.placeholder.com/150" class="card-img" alt="...">
				</div>
				<div class="col-12 col-sm-8 col-md-8 col-lg-8 col-xl-8">
					<div class="row">
						<div class="form-group col-12">
							<label for="identifiant">Article :</label>
							<input type="text" class="form-control" name="identifiant" id="identifiant" placeholder="Nom de l'article">
						</div>
						<div class="form-group col-12">
							<label for="description">Desciption :</label>
							<textarea name="description" rows="6" class="form-control">Description de l'article</textarea>
						</div>
						<div class="form-group col-12 col-lg-6 col-xl-6">
							<label for="motdepasse">Catégorie :</label>
							<select class="custom-select" id="inputGroupSelect">
								<option selected>Choose...</option>
								<option value="1">One</option>
								<option value="2">Two</option>
								<option value="3">Three</option>
							</select>
						</div>
						<div class="form-group col-12 col-lg-6 col-xl-6">
							<label for="image">Photo de l'article :</label>
							<div class="custom-file">
								<input type="file" class="custom-file-input" id="image">
								<label class="custom-file-label" for="image">Télécharger...</label>
							</div>
						</div>
						<div class="form-group col-12">
							<label for="prix">Mise à prix :</label>
							<input type="number" class="form-control" name="prix" id="prix" placeholder="Prix de l'article">
						</div>

						<div class="form-group col-12 col-lg-6 col-xl-6">
							<label for="debut">Début de l'enchère :</label>
							<input type="date" class="form-control" name="debut" id="debut">
						</div>
						<div class="form-group col-12 col-lg-6 col-xl-6">
							<label for="fin">Fin de l'enchère :</label>
							<input type="date" class="form-control" name="fin" id="fin">
						</div>
						<div class="col-12 pt-3">
							<div class="card">
								<div class="card-header">Retrait</div>
								<div class="card-body pt-0 pb-3">
									<div class="form-group row col-12 m-0 pt-3">
										<label class="col-12 col-lg-3 col-xl-3 m-auto pl-0" for="rue">Rue :</label>
										<input type="text" class="form-control col-12 col-lg-9 col-xl-9" name="rue" id="rue" placeholder="Rue">
									</div>
									<div class="form-group row col-12 m-0 pt-3">
										<label class="col-12 col-lg-3 col-xl-3 m-auto pl-0" for="codepostal">Code Postal :</label>
										<input type="text" class="form-control col-12 col-lg-9 col-xl-9" name="codepostal" id="codepostal" placeholder="Code Postal">
									</div>
									<div class="form-group row col-12 m-0 pt-3">
										<label class="col-12 col-lg-3 col-xl-3 m-auto pl-0" for="ville">Ville :</label>
										<input type="text" class="form-control col-12 col-lg-9 col-xl-9" name="ville" id="ville" placeholder="Ville">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>