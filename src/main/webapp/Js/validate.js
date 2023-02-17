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
