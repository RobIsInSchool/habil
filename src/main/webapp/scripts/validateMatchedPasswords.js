

const checkPassInputs = event => {
    let inputVal= event.target.value;
    let passValue = document.querySelector("#password").value;
    let submit = document.querySelector("#submit");
    let passConfirm = document.querySelector("#passwordConfirm");
    if(inputVal != passValue) {
        passConfirm.setCustomValidity("Passwords do not match");
        if (inputVal.length == passValue.length) {
            passConfirm.reportValidity();
        }

        submit.disabled = true;
    } else {
        passConfirm.setCustomValidity("");
        submit.disabled = false;
    }
}

const initValidatePwdMatch = () => {
    let passConfirm = document.querySelector("#passwordConfirm");
    passConfirm.addEventListener('input', checkPassInputs);
}

// window.onload = initValidatePwdMatch;