<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<c:if test="${articles.size()>0}">
	<c:forEach var="article" items="${articles}">
		<div class="col-12 col-sm-12 col-md-12 col-lg-6 col-xl-6 pb-3">
			<div class="card" style="height: 100%;">
				<div class="row no-gutters" style="height: 100%;">
					<div class="col-md-4 px-3 my-auto">
						<img src="https://via.placeholder.com/150" class="card-img"
							alt="...">
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<form method="post" id="art${article.no_article}" action="/detailEnchere">
								<input type="hidden" name="no_article" value="${article.no_article}">
							</form>
							<h5 class="card-title"><a onclick="$('#art${article.no_article}')" href="">${article.nom_article}</a></h5>
							<c:choose>
								<c:when test="${article.concerne != null}">
									<p class="card-text">Prix : ${article.concerne.montant_enchere} points
								</c:when>
								<c:otherwise>
									<p class="card-text">Prix : ${article.prix_initial} points
								</c:otherwise>
							</c:choose>
							<br>Fin de l'enchère :
							<fmt:formatDate value="${article.date_fin_encheres}"
								pattern="dd/MM/yyyy HH:mm" />
							</p>
							<p class="card-text">Vendeur : 
								<a href="#" data-toggle="modal" data-target="#modalProfil" onclick="viewProfil(${article.vendeur.pseudo}, ${article.vendeur.motDePasse})">${article.vendeur.pseudo}</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</c:if>
