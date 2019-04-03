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
				<div class="col-12 col-sm-12 col-md-12 col-lg-6 col-xl-6 pb-3">
					<div class="card">
						<div class="row no-gutters">
							<div class="col-md-4 px-3 my-auto">
								<img src="https://via.placeholder.com/150" class="card-img"
									alt="...">
							</div>
							<div class="col-md-8">
								<div class="card-body">
									<div class="row">
										<div class="form-group col-12">
											<label for="identifiant">Article :</label> <input type="text"
												class="form-control" name="identifiant" id="identifiant"
												placeholder="nom de l'article">
										</div>
										<div class="form-group col-12">
											<label for="description">Desciption :</label> <input
												type="textarea" class="form-control" name="description"
												id="description" placeholder="Saisir une description de l'article">
										</div>
										<div class="form-group col-12">
											<label for="motdepasse">Catégorie :</label> <select
												class="custom-select" id="inputGroupSelect">
												<option selected>Choose...</option>
												<option value="1">One</option>
												<option value="2">Two</option>
												<option value="3">Three</option>
											</select>
										</div>
										<div class="form-group col-12">
											<label for="motdepasse">Photo de l'article :</label>
											<button type="submit" class="btn btn-block btn-success">Télécharger</button>
										</div>
										<div class="form-group col-12">
											<label for="prix">Prix de l'article :</label> <input
												type="number" class="form-control" name="prix"
												id="prix" placeholder="Prix de l'article">
										</div>
										
										<div class="form-group col-12">
											<label for="prix">Début de l'enchère :</label> <input
												type="date" class="form-control" name="debut"
												id="debut">
										</div>
										<div class="form-group col-12">
											<label for="prix">Fin de l'enchère :</label> <input
												type="date" class="form-control" name="fin"
												id="fin">
										</div>


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