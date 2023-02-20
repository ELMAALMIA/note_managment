 const form = document.querySelector('form'); // Get the form element
    const submitButton = form.querySelector('button[type="submit"]'); // Get the submit button element

    // Check if the form has already been submitted
    if (sessionStorage.getItem('formSubmitted') === 'true') {
    // Disable the submit button
    submitButton.disabled = true;
}

    // Add a submit event listener to the form
 form.addEventListener('submit', function(event) {
    // Check if the form has already been submitted
    if (sessionStorage.getItem('formSubmitted') === 'true') {
    // Prevent the form from being submitted
    event.preventDefault();
} else {
    // Set the flag indicating that the form has been submitted
    sessionStorage.setItem('formSubmitted', 'true');
    // Disable the submit button
    submitButton.disabled = true;
}
});
