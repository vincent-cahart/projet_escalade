<%@ page import="java.security.Principal" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col-md-4 mb-4">
    <div class="card">
        <!-- Bannière en haut -->
        <img src="https://picsum.photos/id/237/200/300" class="card-img-top" alt="Bannière de la sortie">
        <div class="card-body">
            <h5 class="card-title">${sortie.name}</h5>
            <p class="card-text">${sortie.description}</p>
            <p class="card-text">Date: ${sortie.dateSortie}</p>

            <% if (principal != null) { %>
            <%-- Utilisateur connecté, afficher les informations spécifiques --%>
            <p class="card-text">Site web: <a href="${sortie.siteWeb}">${sortie.siteWeb}</a></p>
            <p class="card-text">Créateur: ${sortie.creator.firstName} ${sortie.creator.lastName}</p>

            <%-- Vérifier si l'utilisateur connecté est le créateur de la sortie --%>
            <% if (principal.getName() == sortie.creator.email) { %>
            <%-- Utilisateur connecté est le créateur de la sortie, afficher le bouton de modification --%>
            <div class="btn-group" role="group">
                <a href="sortie?sortieId=${sortie.id}" class="btn btn-primary">Modifier/Supprimer</a>
            </div>
            <% } %>
            <% } %>

            <!-- Ajoutez ici d'autres informations -->
            <a href="sortie?sortieId=${sortie.id}" class="btn btn-primary">Voir les détails</a>
        </div>
    </div>
</div>
