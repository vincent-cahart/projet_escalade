<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>

<h1>Liste des Sorties pour ${category.name}</h1>
<ul>
    <c:forEach items="${sorties}" var="sortie">
        <li>
            <a href="sortie?sortieId=${sortie.id}">
                    ${sortie.name}
            </a>
        </li>
    </c:forEach>
</ul>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>