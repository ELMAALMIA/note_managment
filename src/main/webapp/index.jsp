<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="CSS/style.css">
    <script src="Js/validate.js"></script>
</head>
<body>
<h1>Login</h1>
<form action="LoginServlet" method="get" onsubmit="validatePassword(event)">
    <table>
        <tr>
            <td >Login:</td>
            <td><input type="email" name="email" id="email"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password" id="password"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Login" ></td>
        </tr>
        <tr>
            <p>Don't have an account yet? <a href="UserVue/signUpPage.jsp">Sign up</a></p>
        </tr>
    </table>

    <h1></h1>
</form>


<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
<p style="color:red;">
    <%= errorMessage %>
</p>
<%
    }
%>
</body>
</html>
