<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

   	<div class="row">
   		<div class="font-weight-bold col-4">Nom :</div>
   		<div class="col-8">${util.nom}</div>
   	</div>
   	<div class="row">
   		<div class="font-weight-bold col-4">Prénom :</div>
   		<div class="col-8">${util.prenom}</div>
   	</div>
   	<div class="row">
   		<div class="font-weight-bold col-4">Email :</div>
   		<div class="col-8">${util.email}</div>
   	</div>
   	<div class="row">
   		<div class="font-weight-bold col-4">Téléphone :</div>
   		<div class="col-8">${util.telephone}</div>
   	</div>
   	<div class="row">
   		<div class="font-weight-bold col-4">Rue :</div>
   		<div class="col-8">${util.rue}</div>
   	</div>
   	<div class="row">
   		<div class="font-weight-bold col-4">Code postal :</div>
   		<div class="col-8">${util.codePostal}</div>
   	</div>
   	<div class="row">
   		<div class="font-weight-bold col-4">Ville :</div>
   		<div class="col-8">${util.ville}</div>
   	</div>
   	<c:if test="${admin}">
   		<hr>
    	<div class="row">
    		<div class="col-4">
				<c:choose>
	 					<c:when test="${!util.administrateur}">
						<a class="btn btn-block btn-success font-weight-bold" href="" onclick="adminUser('${util.pseudo}','${util.motDePasse}')">Ajout droits</a>
					</c:when>
			        <c:otherwise>
			        	<a class="btn btn-block btn-danger font-weight-bold" href="" onclick="adminUser('${util.pseudo}','${util.motDePasse}')">Retirer droits</a>
			        </c:otherwise>
			    </c:choose>
		    </div>
			<div class="col-4">
				<c:choose>
	 				<c:when test="${!util.banni}">
						<a class="btn btn-block btn-danger font-weight-bold" href="" onclick="banUser('${util.pseudo}','${util.motDePasse}')">Bannir</a>
					</c:when>
			        <c:otherwise>
			        	<a class="btn btn-block btn-success font-weight-bold" href="" onclick="banUser('${util.pseudo}','${util.motDePasse}')">Débannir</a>
			        </c:otherwise>
			    </c:choose>
			</div>
			<div class="col-4">
				<a class="btn btn-block btn-danger font-weight-bold" href="" onclick="deleteUser('${util.pseudo}','${util.motDePasse}')">Supprimer</a>
			</div>
    	</div>
   	</c:if>