
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: ayoub
  Date: 16/02/2023
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin</title>
    <link rel="stylesheet" href="../CSS/adminStyle.css">
</head>
<body>

<nav>
    <ul>
        <li><a href="#">Settings</a></li>
        <li class="right"><a href="logout">Logout</a></li>
        <%--        <li><a href="#" class="right">Welcome, John Doe</a></li>--%>
    </ul>
</nav>


<h1>notes management</h1>

<h2>Add notes </h2>
<form action="/NotesServlet" method="get">
    <label for="title">Name:</label>
    <input type="text" id="title" name="title"><br>
    <label for="description">E-mail:</label>
    <input type="text" id="description" name="description"><br>
    <button type="submit" name="action" value="addNote">Add notes</button>
</form>
<h2>All Notes </h2>
<table>
    <thead>
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${notes}" var="user">
        <tr>
            <td>
                <input type="text" name="title" value=${notes.getTitle()}>
            </td>
            <td>
                <input type="text" name="description" value=${notes.getDescription()}>
            </td>
            <td>
                <form action="/NotesServlet" method="get">
                    <input type="hidden" name="id" value="${notes.getId()}">
                    <button type="submit" name="action" value="editNotes">Edit</button>
                    <button type="submit" name="action" value="deleteNotes">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>

