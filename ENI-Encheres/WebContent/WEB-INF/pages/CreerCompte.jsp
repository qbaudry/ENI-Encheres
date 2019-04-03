<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				    	<c:choose>
    						<c:when test="${!empty formulaire}">
								<input type="text" class="form-control" id="pseudo" name="pseudo" placeholder="Pseudo" value="${formulaire.pseudo}" required>
							</c:when>
					        <c:otherwise>
					        	<input type="text" class="form-control" id="pseudo" name="pseudo" placeholder="Pseudo" required>
					        </c:otherwise>
					    </c:choose>
					</div>
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="nom">Nom :</label>
				    	<c:choose>
    						<c:when test="${!empty formulaire}">
								<input type="text" class="form-control" id=""nom"" name="nom" placeholder="Nom" value="${formulaire.nom}" required>
							</c:when>
					        <c:otherwise>
					        	<input type="text" class="form-control" id=""nom"" name=""nom"" placeholder="Nom" required>
					        </c:otherwise>
					    </c:choose>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="prenom">Prénom :</label>
				    	<c:choose>
    						<c:when test="${!empty formulaire}">
								<input type="text" class="form-control" id="prenom" name="prenom" placeholder="Prénom" value="${formulaire.prenom}" required>
							</c:when>
					        <c:otherwise>
					        	<input type="text" class="form-control" id="prenom" name="prenom" placeholder="Prénom" required>
					        </c:otherwise>
					    </c:choose>
					</div>
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6" required>
				    	<label for="nom">Email :</label>
				    	<c:choose>
    						<c:when test="${!empty formulaire}">
								<input type="email" class="form-control" id="email" name="email" placeholder="Email" value="${formulaire.email}" required>
							</c:when>
					        <c:otherwise>
					        	<input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
					        </c:otherwise>
					    </c:choose>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="telephone">Téléphone :</label>
				    	<c:choose>
    						<c:when test="${!empty formulaire}">
								<input type="text" pattern="\d{10}" class="form-control" id="telephone" name="telephone" placeholder="Téléphone" value="${formulaire.telephone}" required>
							</c:when>
					        <c:otherwise>
					        	<input type="text" pattern="\d{10}" class="form-control" id="telephone" name="telephone" placeholder="Téléphone" required>
					        </c:otherwise>
					    </c:choose>
					</div>
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="rue">Rue :</label>
				    	<c:choose>
    						<c:when test="${!empty formulaire}">
								<input type="text" class="form-control" id="rue" name="rue" placeholder="Rue" value="${formulaire.rue}" required>
							</c:when>
					        <c:otherwise>
					        	<input type="text" class="form-control" id="rue" name="rue" placeholder="Rue" required>
					        </c:otherwise>
					    </c:choose>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="codepostal">Code postal :</label>
				    	<c:choose>
    						<c:when test="${!empty formulaire}">
								<input type="text" pattern="\d{5}" class="form-control" id="codepostal" name="codepostal" placeholder="Code postal" value="${formulaire.codePostal}" required>
							</c:when>
					        <c:otherwise>
					        	<input type="text" pattern="\d{5}" class="form-control" id="codepostal" name="codepostal" placeholder="Code postal" required>
					        </c:otherwise>
					    </c:choose>
					</div>
					<div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
				    	<label for="ville">Ville :</label>
				    	<c:choose>
    						<c:when test="${!empty formulaire}">
								<input type="text" class="form-control" id="ville" name="ville" placeholder="Ville" value="${formulaire.ville}" required>
							</c:when>
					        <c:otherwise>
					        	<input type="text" class="form-control" id="ville" name="ville" placeholder="Ville" required>
					        </c:otherwise>
					    </c:choose>
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