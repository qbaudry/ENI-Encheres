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
		<div class="alert alert-dark mt-5" role="alert">
			<h1>Listes des encheres</h1>
			<hr>
			<label for="filtre">Filtre :</label>
			<div class="row">
				<div class="form-group col-12 col-sm-12 col-md-6 col-lg-5 col-xl-5">
					<input type="text" class="form-control" id="filtre" name="filtre" placeholder="Le nom de l'article contient...">
				</div>
				<div class="input-group col-12 col-sm-12 col-md-6 col-lg-4 col-xl-4 mb-3">
				  	<div class="input-group-prepend">
				    	<label class="input-group-text" for="inputGroupSelect">Catégorie</label>
				  	</div>
				  	<select class="custom-select" id="inputGroupSelect">
				  		<option value="toutes" selected>Toutes</option>
						<c:if test="${categories.size()>0}">
							<c:forEach var="categorie" items="${categories}">
						    	<option value="${categorie.noCategorie}">${categorie.libelle}</option>
					      	</c:forEach>
				        </c:if>
			        </select>
				</div>
				<div class="col-12 col-sm-12 col-md-12 col-lg-3 col-xl-3">
					<button type="submit" class="btn btn-block btn-primary">Rechercher</button>
				</div>
			</div>
			<hr>
			<div class="row">
				<c:if test="${articles.size()>0}">
					<c:forEach var="article" items="${articles}">
				    	<div class="col-12 col-sm-12 col-md-12 col-lg-6 col-xl-6 pb-3">
							<div class="card">
								<div class="row no-gutters">
							    	<div class="col-md-4 px-3 my-auto">
							      		<img src="https://via.placeholder.com/150" class="card-img" alt="...">
							    	</div>
							    	<div class="col-md-8">
							      		<div class="card-body">
								        	<h5 class="card-title">${article.nom_article}</h5>
								        	<p class="card-text">Prix : ${article.prix_initial} points<br>
								        		Fin de l'enchère : ${article.date_fin_encheres}</p>
								        	<p class="card-text">Vendeur : ${article.vendeur.pseudo}</p>
							      		</div>
							    	</div>
								</div>
							</div>
						</div>
			      	</c:forEach>
		        </c:if>
				
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