function addCookie(key, value) {
    const encodeKey = encodeURIComponent(key);
    const encodeValue = encodeURIComponent(value);

    console.log(encodeKey + " " + encodeValue);

    document.cookie = `${encodeKey}=${encodeValue}; path=/`;
}

function checkId(){
    if (document.cookie.includes("id=")) {
        const exitButton = document.getElementById("exitButton");

        exitButton.style.color = "black";
        exitButton.disabled = false;

        return true;
    }

    return false;
}

function getValue(key) {
    const cookie = document.cookie;
    const keyString = key + "=";

    if (!cookie.includes(keyString)) {
        return null;
    }

    const index = cookie.indexOf(keyString);
    const valueAndOther = document.cookie.substring(index + keyString.length);

    if (!valueAndOther.includes(";")) {
        return valueAndOther;
    }

    return valueAndOther.substring(0, valueAndOther.indexOf(";"));
}