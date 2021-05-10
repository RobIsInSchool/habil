
const makeForm = () => {
    let form = document.createElement("form");
    let zipTextInput = document.createElement("input");
    let submitButton = document.createElement("input");

    form.setAttribute("action", "changeZip");
    form.setAttribute("method", "POST");
    zipTextInput.setAttribute("type", "text");
    zipTextInput.setAttribute("name", "newZip");
    zipTextInput.setAttribute("pattern", "[0-9]{5}");
    zipTextInput.setAttribute("id", "zip");
    submitButton.setAttribute("type", "submit");
    submitButton.setAttribute("id", "submit");
    submitButton.setAttribute("value", "Change");
    submitButton.setAttribute("class", "submitButton");
    submitButton.disabled = true;

    form.appendChild(zipTextInput);
    form.appendChild(submitButton);

    return form;

}

const handleClick = event => {
    let outputArea = document.querySelector("#zipChangeInputArea");
    outputArea.innerHTML = "";
    outputArea.appendChild(makeForm());
    initValidateZip();
}

const initChange = () => {
    let changeButton = document.querySelector("#zipChange");
    changeButton.addEventListener("click", handleClick);
}

// window.onload = initChange;