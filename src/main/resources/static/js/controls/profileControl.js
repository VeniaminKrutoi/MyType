function profileControl(key, message) {
    const current = document.getElementById(key).textContent;
    const newValue = prompt(message, current);

    const data = {
        username: null,
        email: null,
        password: null
    }

    if (newValue) {
        data[key] = newValue;
        changeUsernameEmailPassword(data);
    }
}

function checkUser() {
    if (!document.cookie.includes("id=")) {
        window.location.href = "http://localhost:8080/auth";
    }
}

checkUser();

