<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/includes/Header.jsp"></jsp:include>
<body>
	<%@ include file="../includes/Navbar.jsp" %>
	<div class="container">
		<div class="alert alert-dark mt-5" role="alert">
			<h1>Se connecter</h1>
			<hr>
			<div class="row">
				<div class="form-group col-12">
			    	<label for="identifiant">Identifiant :</label>
			    	<input type="text" class="form-control" id="identifiant" placeholder="Identifiant">
				</div>
				<div class="form-group col-12">
			    	<label for="motdepasse">Mot de passe :</label>
			    	<input type="password" class="form-control" id="motdepasse" placeholder="Mot de passe">
				</div>
			</div>
			<hr>
			<div class="row pb-3">
				<div class="col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
			    	<button type="button" class="btn btn-block btn-success">Connexion</button>
						<div class="form-check py-3 mx-3">
					    <input type="checkbox" class="form-check-input" id="sesouvenir">
						<label class="form-check-label" for="sesouvenir">Se souvenir de moi</label>
					</div>
				</div>
				<div class="col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
					<a class="btn btn-block btn-warning">Mot de passe oublié</a>
				</div>
			</div>
			<a class="btn btn-block btn-success" href="${pageContext.request.contextPath}/creerCompte">Créer un compte</a>
		</div>
	</div>
</body>
</html>