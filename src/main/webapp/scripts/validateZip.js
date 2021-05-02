
const validateInput = results => {
    let inputField = document.querySelector(".zipTextInput");
    console.log("Reults: " + results);
    let submit = document.querySelector(".submitButton");
    if (results > 0) {
        submit.disabled = false;
        inputField.setCustomValidity("");
    } else {
        submit.disabled = true;
        inputField.setCustomValidity("Sorry, that zip code cannot be validated. Please make sure it's a US zip and completely numeric.");
    }
}

const checkGeoAPI = zip => {
    let url = `http://api.geonames.org/postalCodeSearchJSON?postalcode=${zip}&maxRows=10&country=US&username=${values.geoUserName}`;
    console.log("URL: " + url);
    let xhr = new XMLHttpRequest();
    let numResults = null;
    xhr.open("get", url)
    xhr.send();
    xhr.onload = () => {
        if (xhr.status != 200) {
            console.log("There was an error with the request");
        } else {
            console.log("Pure response text: " + xhr.responseText);
            numResults = JSON.parse(xhr.responseText).postalCodes.length;
            validateInput(numResults);
        }
    }
}


const validateZip = event => {
    let zip = event.target.value;
    let numDigits = zip.length;

    console.log("change detected...");
    console.log(`Num digits: ${numDigits}`);
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
    const zipInput = document.querySelector(".zipTextInput");
    zipInput.addEventListener('keyup', validateZip);
    zipInput.addEventListener('paste', validateZip)
    console.log("initializing validate...");
}

// window.onload = initValidate;