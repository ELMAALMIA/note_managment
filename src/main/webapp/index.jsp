<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/bootstrap-5.0.2-dist/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/style.css">
    <script src="${pageContext.request.contextPath}/Js/validate.js"></script>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-12 col-md-8 col-lg-6">
            <div class="card">
                <div class="card-header">
                    <h1 class="text-center">Login</h1>
                </div>
                <div class="card-body">
                    <form action="authServlet" method="post" onsubmit="validatePassword(event)">
                        <div class="form-group mb-3">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" name="email" id="email">
                        </div>
                        <div class="form-group mb-3">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" name="password" id="password">
                        </div>
                        <button type="submit" class="btn btn-primary" name="action" value="login">Login</button>
                    </form>
                </div>
                <div class="card-footer">
                    <p class="text-center mb-0">Don't have an account yet? <a href="${pageContext.request.contextPath}/UserVue/signUpPage.jsp">Sign up</a></p>
                </div>
            </div>
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
