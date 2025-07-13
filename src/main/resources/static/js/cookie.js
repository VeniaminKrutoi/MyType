function addCookie(key, value) {
    const encodeKey = encodeURIComponent(key);
    const encodeValue = encodeURIComponent(value);

    console.log(encodeKey + " " + encodeValue);

    document.cookie = `${encodeKey}=${encodeValue}; path=/`;
}

function checkUser(){
    if (document.cookie.includes("user")) {
        const exitButton = document.getElementById("exitButton");

        exitButton.style.color = "black";
        exitButton.disabled = false;
    }
}

function getEmail() {
    const cookie = document.cookie;

    if (!cookie.includes("user=")) {
        return null;
    }

    const index = cookie.indexOf("user=");
    const valueAndOther = document.cookie.substring(index + 5);

    if (!valueAndOther.includes(";")) {
        return valueAndOther;
    }

    return valueAndOther.substring(0, valueAndOther.indexOf(";"));
}