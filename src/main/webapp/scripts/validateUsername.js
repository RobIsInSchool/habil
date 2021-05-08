
class Validator {
    usernames;

    constructor() {
        this.getUserNames();

    }

    getUserNames = () => {
        let url = location.pathname.substr(0, location.pathname.lastIndexOf('/')) + "/api/usernames";
        console.log("Connecting to URL: " + url);
        let xhr = new XMLHttpRequest();
        xhr.open("get", url);
        xhr.send();
        xhr.onload = () => {
            if (xhr.status != 200) {
                console.log("There was an error with the request");
            } else {
                console.log("Request successful: " + xhr.responseText);
                this.usernames = JSON.parse(xhr.responseText);
            }
        }
    }

    validateUsername = event => {
        let targetUsername = event.target.value;
        const submit = document.querySelector(".submitButton");
        let inputField = document.querySelector("#username");

        console.log("Validating username...");

        if (this.isUnique(targetUsername)) {
            inputField.setCustomValidity("");
            submit.disabled = false;
        } else {
            inputField.setCustomValidity("Sorry, that user name already exists. Please choose something else");
            inputField.reportValidity();
            submit.disabled = true;
        }
    }

    isUnique = targetUsername => {
        console.log("checking list of usernames against target: " + targetUsername);
        let username;

        for (let i = 0; i < this.usernames.length; i++) {
            username = this.usernames[i].username;
            console.log("checking -> " + username);
            if (targetUsername == username) {
                console.log("An identical username was found");
                return false;
            }
        }
        console.log("the username " + targetUsername + " is unique");
        return true;
    }
}

const init = () => {
    console.log("script initialized successfully");
    let validator = new Validator();
    const inputField = document.querySelector("#username");

    inputField.addEventListener('input', validator.validateUsername);
}

window.onload = init;