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

