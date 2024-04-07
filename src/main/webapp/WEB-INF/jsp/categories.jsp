<%@ page import="mybootapp.jpa.model.Category" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Catégories</title>
</head>
<body>
<h1>Liste des Catégories</h1>
<ul>
    <% for (Category category : categories) { %>
    <li>${category.name}</li>
    <% } %>
</ul>
</body>
</html>
