<%@ page import="java.security.Principal" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>

<div class="container">
    <h1>Mes Sorties</h1>
    <div class="row">
        <%-- Inclure le composant de carte pour chaque sortie --%>
        <c:forEach items="${userSorties}" var="sortie">
            <%@ include file="/WEB-INF/jsp/components/sortie.jsp" %>
        </c:forEach>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
