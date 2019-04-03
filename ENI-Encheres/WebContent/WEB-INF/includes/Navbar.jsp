<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand font-weight-bold" href="${pageContext.request.contextPath}">
    <i class="fas fa-balance-scale pr-1"></i>
    ENI-Enchères
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

	<% if(session.getAttribute("identifiant") != null) {%>
   		<div class="collapse navbar-collapse" id="navbarSupportedContent">
	  	<span class="m-auto"></span>
	    <ul class="navbar-nav">   
	      <li class="nav-item">      
	        <a class="nav-link" href="${pageContext.request.contextPath}/listeEncheres">Enchères</a>
	      </li>
	      <li class="nav-item">      
	        <a class="nav-link" href="${pageContext.request.contextPath}/ajoutArticle">Vendre un article</a>
	      </li>
	      <li class="nav-item">      
	        <a class="nav-link" href="${pageContext.request.contextPath}/Profil">Mon profil</a>
	      </li>
	      <li class="nav-item">      
	        <a class="nav-link" href="${pageContext.request.contextPath}/seDeconnecter">Déconnexion</a>
	      </li>
	    </ul>
	  </div>
   		
   		
	<%}else{%>
		
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
	  	<span class="m-auto"></span>
	    <ul class="navbar-nav">   
	      <li class="nav-item">      
	        <a class="nav-link" href="${pageContext.request.contextPath}/seConnecter">S'inscire - Se connecter</a>
	      </li>
	    </ul>
	  </div>
		
	<% }%>
		   
  
</nav>