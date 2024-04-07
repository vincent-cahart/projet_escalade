<%@ page import="java.security.Principal" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>

<div class="container">
    <h1>Liste des Catégories</h1>
    <div class="row">
        <c:forEach items="${categories}" var="category">
            <div class="col-md-4 mb-4">
                <div class="card">
                    <!-- Image à gauche -->
                    <div class="row g-0">
                        <div class="col-md-4">
                            <img src="https://picsum.photos/id/237/200/300" class="img-fluid rounded-start" alt="Image de la catégorie">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title">${category.name}</h5>
                                <a href="categorie?categorieId=${category.id}" class="btn btn-primary">Voir les sorties</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <h1>Liste des Sorties</h1>
    <div class="row mt-4">
        <form action="search" method="GET" class="input-group mb-3">
            <input type="text" name="keyword" placeholder="Rechercher une sortie" class="form-control">
            <button type="submit" class="btn btn-primary">Rechercher</button>
        </form>
    </div>

    <div class="row">
        <%-- Inclure le composant de carte pour chaque sortie --%>
        <c:forEach items="${sorties}" var="sortie">
            <%@ include file="/WEB-INF/jsp/components/sortie.jsp" %>
        </c:forEach>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
