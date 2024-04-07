<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<mvc:annotation-driven date-format="dd/MM/yyyy HH:mm" />

<h1>Modifier la sortie</h1>

<div class="card bg-light">
    <div class="card-body">
        <form:form method="POST" modelAttribute="sortie" action="/sortie/modifier?sortieId=${sortie.id}">
            <form:hidden path="id" />

            <div class="form-group">
                <label for="name">Nom :</label>
                <form:input path="name" class="form-control" />
                <form:errors path="name" cssClass="alert alert-warning" element="div" />
            </div>

            <div class="form-group">
                <label for="description">Description :</label>
                <form:textarea path="description" class="form-control" rows="4" />
                <form:errors path="description" cssClass="alert alert-warning" element="div" />
            </div>

            <div class="form-group">
                <label for="dateSortie">Date et heure (dd/MM/yyyy HH:mm):</label>
                <form:input path="dateSortie" type="datetime-local" class="form-control" />
                <form:errors path="dateSortie" cssClass="alert alert-warning" element="div" />
            </div>

            <div class="form-group">
                <label for="category">Catégorie :</label>
                <form:select path="category.id" class="form-control" required="required">
                    <form:option value="">Sélectionnez une catégorie</form:option>
                    <form:options items="${categories}" itemValue="id" itemLabel="name" />
                </form:select>
                <form:errors path="category.id" cssClass="alert alert-warning" element="div" />
            </div>

            <div class="form-group">
                <label for="siteWeb">Site Web :</label>
                <form:input path="siteWeb" class="form-control" />
                <form:errors path="siteWeb" cssClass="alert alert-warning" element="div" />
            </div>

            <button type="submit" class="btn btn-primary">Modifier</button>
        </form:form>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
