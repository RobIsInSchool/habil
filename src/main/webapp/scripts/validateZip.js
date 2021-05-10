
const validateInput = results => {
    let inputField = document.querySelector("#zip");
    let submit = document.querySelector("#submit");
    if (results > 0) {
        inputField.setCustomValidity("");
        submit.disabled = false;
    } else {
        inputField.setCustomValidity("Sorry, that zip code cannot be validated. Please make sure it's a US zip and completely numeric.");
        inputField.reportValidity();
        submit.disabled = true;
    }
}

const checkGeoAPI = zip => {
    let url = `http://api.geonames.org/postalCodeSearchJSON?postalcode=${zip}&maxRows=10&country=US&username=${values.geoUserName}`;
    let xhr = new XMLHttpRequest();
    let numResults = null;
    xhr.open("get", url)
    xhr.send();
    xhr.onload = () => {
        if (xhr.status != 200) {
            console.log("There was an error with the request");
        } else {
            numResults = JSON.parse(xhr.responseText).postalCodes.length;
            validateInput(numResults);
        }
    }
}


const validateZip = event => {
    let zip = event.target.value;
    let numDigits = zip.length;

    if (checkDigitCount(numDigits) && !isNaN(zip)) {
        checkGeoAPI(zip);
    }
}


const checkDigitCount = numDigits => {
    if (numDigits == 5) {
        return true;
    }
    return false;
}

const initValidate = () => {
    const zipInput = document.querySelector("#zip");
    zipInput.addEventListener('input', validateZip);
    console.log("initializing validate...");
}

// window.onload = initValidate;