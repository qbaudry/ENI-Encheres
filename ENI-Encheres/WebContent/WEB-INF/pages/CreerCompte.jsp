<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/includes/Header.jsp"></jsp:include>
<body>
	<%@ include file="../includes/Navbar.jsp" %>
	<div class="container">
		<div class="alert alert-dark mt-5" role="alert">
			<h1>Mon Profil</h1>
			<hr>
			<div class="row">
				<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
			    	<label for="pseudo">Pseudo :</label>
			    	<input type="text" class="form-control" id="pseudo" placeholder="Pseudo">
				</div>
				<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
			    	<label for="nom">Nom :</label>
			    	<input type="text" class="form-control" id="nom" placeholder="Nom">
				</div>
			</div>
			<div class="row">
				<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
			    	<label for="prenom">Prénom :</label>
			    	<input type="text" class="form-control" id="prenom" placeholder="Prénom">
				</div>
				<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
			    	<label for="nom">Email :</label>
			    	<input type="email" class="form-control" id="nom" placeholder="Email">
				</div>
			</div>
			<div class="row">
				<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
			    	<label for="telephone">Téléphone :</label>
			    	<input type="number" class="form-control" id="telephone" placeholder="Téléphone">
				</div>
				<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
			    	<label for="rue">Rue :</label>
			    	<input type="text" class="form-control" id="rue" placeholder="Rue">
				</div>
			</div>
			<div class="row">
				<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
			    	<label for="codepostal">Code postal :</label>
			    	<input type="text" class="form-control" id="codepostal" placeholder="Code postal">
				</div>
				<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
			    	<label for="ville">Ville :</label>
			    	<input type="text" class="form-control" id="ville" placeholder="Ville">
				</div>
			</div>
			<div class="row">
				<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
			    	<label for="motdepasse">Mot de passe :</label>
			    	<input type="password" class="form-control" id="motdepasse" placeholder="Mot de passe">
				</div>
				<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
			    	<label for="motdepasse2">Confirmation :</label>
			    	<input type="password" class="form-control" id="motdepasse2" placeholder="Confirmation du mot de passe">
				</div>
			</div>
			<hr>
			<div class="row">
				<div class="col-6">
			    	<button type="button" class="btn btn-block btn-success">Creer</button>
				</div>
				<div class="col-6">
			    	<a class="btn btn-block btn-danger" href="${pageContext.request.contextPath}/seConnecter">Annuler</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>