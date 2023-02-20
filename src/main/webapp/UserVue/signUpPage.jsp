<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String message = (String) request.getAttribute("message");
    if (message != null) {
        out.println("<script>alert('" + message + "');</script>");
    }
%>
<html>
<head>
    <title>Signup Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/bootstrap-5.0.2-dist/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/signUpStyle.css">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <h1 class="text-center">Signup</h1>
            <form action="../authServlet" method="post" onsubmit="validatePassword(event)">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" class="form-control" id="name" name="username" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary" name="action" value="signUp">Signup</button>
            </form>
        </div>
    </div>
</div>
<script>
    function validatePassword(event) {
        const passwordInput = document.getElementById("password");
        const emailInput = document.getElementById("email");
        const password = passwordInput.value;
        const email = emailInput.value;
        // validate email format using regular expression
        const emailRegex = /^\S+@\S+\.\S+$/;
        if (!emailRegex.test(email)) {
            alert("Please enter a valid email address.");
            event.preventDefault(); // prevent the form from being submitted
            return;
        }

        if (password.length < 1) {
            alert("Password cannot be empty.");
            event.preventDefault(); // prevent the form from being submitted
        }
        else if (password.length < 8) {
            alert("Password should be at least 8 characters.");
            event.preventDefault(); // prevent the form from being submitted
        }
        // else {
        //     alert("Password is valid.");
        // }
    }

</script>
</body>
</html>
