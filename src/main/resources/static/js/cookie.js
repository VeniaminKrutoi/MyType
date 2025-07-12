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