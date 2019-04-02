<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/includes/Header.jsp"></jsp:include>
<body>
	<%@ include file="../includes/Navbar.jsp" %>
	<div class="container">
		<form method="post" action="${pageContext.request.contextPath}/creerCompte">
			<div class="alert alert-dark mt-5" role="alert">
				<h1>Mon Profil</h1>
				<hr>
				<div class="row">
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="pseudo">Pseudo :</label>
				    	<input type="text" class="form-control" id="pseudo" name="pseudo" placeholder="Pseudo" required>
					</div>
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="nom">Nom :</label>
				    	<input type="text" class="form-control" id="nom" name="nom" placeholder="Nom" required>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="prenom">Prénom :</label>
				    	<input type="text" class="form-control" id="prenom" name="prenom" placeholder="Prénom" required>
					</div>
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6" required>
				    	<label for="nom">Email :</label>
				    	<input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="telephone">Téléphone :</label>				    	
				    	<input type="text" pattern="\d{10}" class="form-control" id="telephone" name="telephone" placeholder="Téléphone" required>
					</div>
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="rue">Rue :</label>
				    	<input type="text" class="form-control" id="rue" name="rue" placeholder="Rue" required>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="codepostal">Code postal :</label>
				    	<input type="text" class="form-control" id="codepostal" name="codepostal" placeholder="Code postal" required>
					</div>
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="ville">Ville :</label>
				    	<input type="text" class="form-control" id="ville" name="ville" placeholder="Ville" required>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="motdepasse">Mot de passe :</label>
				    	<input type="password" class="form-control" id="motdepasse" name="motdepasse" placeholder="Mot de passe" required>
					</div>
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="motdepasse2">Confirmation :</label>
				    	<input type="password" class="form-control" id="motdepasse2" name="motdepasse2" placeholder="Confirmation du mot de passe" required>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-6">
				    	<button type="submit" class="btn btn-block btn-success">Creer</button>
					</div>
					<div class="col-6">
				    	<a class="btn btn-block btn-danger" href="${pageContext.request.contextPath}/seConnecter">Annuler</a>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>