<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand font-weight-bold" href="${pageContext.request.contextPath}">
    	<i class="fas fa-balance-scale pr-1"></i>
    	ENI-Enchères
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
  		<span class="navbar-toggler-icon"></span>
  </button>
	<c:choose>
		<c:when test="${identifiant != null}">
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
			  	<span class="text-uppercase font-weight-bold mt-1 mr-1">${identifiant}</span>
			  	<span class="badge badge-light mt-1 mr-auto">${credits}$</span>
			    <ul class="navbar-nav">
			      	<li class="nav-item">      
			        	<a class="nav-link" href="${pageContext.request.contextPath}/listeEncheres">Enchères</a>
			      	</li>
			      	<li class="nav-item">      
			        	<a class="nav-link" href="${pageContext.request.contextPath}/ajoutArticle">Vendre un article</a>
			      	</li>
			    	  <li class="nav-item">      
			    	    <a class="nav-link" href="${pageContext.request.contextPath}/monProfil">Mon profil</a>
			     	</li>
			      	<li class="nav-item">
			        	<a class="nav-link my-1 ml-2" href="${pageContext.request.contextPath}/seDeconnecter">
				        	<i class="fas fa-power-off"></i>
				        	<span class="d-lg-none">Déconnection</span>
			        	</a>
			    	</li>
			    	<c:if test="${admin}">
				    	<li class="nav-item">      
				        	<a class="nav-link my-1 ml-2" href="${pageContext.request.contextPath}/AdminPage">
					        	<i class="fas fa-user-cog"></i>
					        	<span class="d-lg-none">Administration</span>
				        	</a>
				        	<!--<div class="dropdown show">
							  <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuAdmin" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							    <i class="fas fa-user-cog"></i>
							  </a>
							
							  <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuAdmin">
							    <a class="dropdown-item" href="${pageContext.request.contextPath}/AdminPage">Administration Utilisateur</a>
							  </div>
							</div>-->
				      	</li>
			      	</c:if>
				</ul>
			</div>
		</c:when>
        <c:otherwise>
        	<div class="collapse navbar-collapse" id="navbarSupportedContent">
			  	<span class="m-auto"></span>
			    <ul class="navbar-nav">
			      <li class="nav-item">      
		        	<a class="nav-link" href="${pageContext.request.contextPath}/listeEncheres">Enchères</a>
		      	  </li>
			      <li class="nav-item">      
			        <a class="nav-link font-weight-bold" href="${pageContext.request.contextPath}/seConnecter">S'inscire / Se connecter</a>
			      </li>
			    </ul>
			  </div>
        </c:otherwise>
    </c:choose>  
</nav>