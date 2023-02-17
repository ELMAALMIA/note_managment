
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
    <link rel="stylesheet" href="/CSS/adminStyle.css">
</head>
<body>

<nav>
    <ul>
        <li><a href="#">Settings</a></li>
        <li class="right"><a href="logout">Logout</a></li>
        <%--        <li><a href="#" class="right">Welcome, John Doe</a></li>--%>
    </ul>
</nav>


<h1>User Management</h1>

<h2>Add New User</h2>
<form action="AdminServlet" method="get">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name"><br>
    <label for="email">E-mail:</label>
    <input type="email" id="email" name="email"><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password"><br>
    <button type="submit" name="action" value="addUser">Add User</button>
</form>
<h2>All Users</h2>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>
                <input type="text" name="username" value=${user.getUsername()}>
            </td>
            <td>
                <input type="text" name="emailmodify" value=${user.getEmail()}>
            </td>
            <td>
                <form action="AdminServlet" method="get">
                    <input type="hidden" name="id" value="${user.getId()}">
                    <button type="submit" name="action" value="editUser">Edit</button>
                    <button type="submit" name="action" value="deleteUser">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>

