<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<h1>Résultats de la Recherche</h1>
<ul>
    <div class="row">
        <%-- Inclure le composant de carte pour chaque sortie --%>
        <c:forEach items="${sorties}" var="sortie">
            <%@ include file="/WEB-INF/jsp/components/sortie.jsp" %>
        </c:forEach>
    </div>
</ul>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
