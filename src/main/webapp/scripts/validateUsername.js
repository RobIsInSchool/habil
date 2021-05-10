
class Validator {
    usernames;

    constructor() {
        this.getUserNames();
    }

    getUserNames = () => {
        let url = location.pathname.substr(0, location.pathname.lastIndexOf('/')) + "/api/usernames";
        let xhr = new XMLHttpRequest();
        xhr.open("get", url);
        xhr.send();
        xhr.onload = () => {
            if (xhr.status != 200) {

            } else {
                this.usernames = JSON.parse(xhr.responseText);
            }
        }
    }

    validateUsername = event => {
        let targetUsername = event.target.value;
        const submit = document.querySelector(".submitButton");
        let inputField = document.querySelector("#username");

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
        let username;
        for (let i = 0; i < this.usernames.length; i++) {
            username = this.usernames[i].username;
            if (targetUsername == username) {
                return false;
            }
        }
        return true;
    }
}

const initValidateUsername = () => {
    let validator = new Validator();
    const inputField = document.querySelector("#username");
    inputField.addEventListener('input', validator.validateUsername);
}
