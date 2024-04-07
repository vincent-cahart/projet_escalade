<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<h1>Détails de la Sortie</h1>
<p>Nom : ${sortie.name}</p>
<p>Description : ${sortie.description}</p>
<p>Date : ${sortie.dateSortie}</p>
<p>Créateur : ${sortie.creator.firstName} ${sortie.creator.lastName}</p>
<p>Catégorie : ${sortie.category.name}</p>

<%-- Vérifiez si la sortie est éditable --%>
<%
    Boolean editable = (Boolean) request.getAttribute("editable");
    if (editable != null && editable) {
%>
<a href="/sortie/modifier?sortieId=${sortie.id}" class="btn btn-primary">Modifier</a>
<%
    }
%>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
