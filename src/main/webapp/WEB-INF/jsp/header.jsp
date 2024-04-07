<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ page import="java.security.Principal" %>
<c:url var="css" value="/style.css" />

<html>
<head>
	<meta charset="UTF-8">
	<title>Escalade</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<link rel="stylesheet" href="${css}">
</head>
<body>
<header>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Accueil</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item">
						<a class="nav-link" href="#">Catégories</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#">Prochaines sorties</a>
					</li>
					<li class="nav-item">
						<%-- Vérifier si l'utilisateur est connecté en utilisant l'objet Principal --%>
						<% Principal principal = request.getUserPrincipal(); %>
						<% if (principal != null) { %>
						<%-- Utilisateur connecté --%>
							<a class="nav-link" href="/logout">Se déconnecter</a>
						<% } else { %>
						<%-- Utilisateur non connecté, afficher le bouton de connexion --%>
						<a class="nav-link" href="/login">Se connecter</a>
						<% } %>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</header>
<div class="container">
