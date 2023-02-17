<%--
  Created by IntelliJ IDEA.
  User: ayoub
  Date: 17/02/2023
  Time: 08:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Signup Page</title>
    <link rel="stylesheet" href="../CSS/style.css">
</head>
<body>
<body>
<h1>Signup</h1>

<form action="../signUpServlet" method="get">

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <input type="submit" value="SignUp">
</form>
</body>
</body>
</html>
