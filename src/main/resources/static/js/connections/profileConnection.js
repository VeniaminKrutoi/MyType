if (user === null) {
    connectionFail("Проблемы на сервере");
} else {
    connectionSuccess(user);
}

function connectionSuccess(data) {
    const nameEl = document.getElementById("username");
    nameEl.innerText = data['username'];

    const emailEl = document.getElementById("email");
    if (emailEl) {
        emailEl.innerText = data['email'];
    }

    const passwordEl = document.getElementById("password");
    if (passwordEl) {
        passwordEl.innerText = data['password'];
    }

    //Sign Per Minute Element
    const spmEl = document.getElementById("spm");
    const typeResults = data['typeResult'];
    if (Array.isArray(typeResults) && typeResults.length !== 0) {
        const averageSpm = Math.round(typeResults.reduce((acc, num) => acc + num, 0) / typeResults.length);
        spmEl.innerText = averageSpm.toString();
    } else {
        spmEl.innerText = "0";
    }

    const timeEl = document.getElementById("time");
    if (data['time'] === null) {
        timeEl.innerText = "0";
    } else {
        timeEl.innerText = data['time'];
    }

    const profileEl = document.getElementById("profile");
    const uploadingEl = document.getElementById("uploading");
    profileEl.style.visibility = "visible";
    uploadingEl.style.visibility = "hidden";
}

function connectionFail(error) {
    console.log(error);
    showNotification(error, "error");

    const errorButtonEl = document.getElementById("errorButton");
    const uploadingEl = document.getElementById("uploading");
    errorButtonEl.style.visibility = "visible";
    uploadingEl.style.visibility = "hidden";
}

function changeUsernameEmailPassword(data) {
    fetch(`http://localhost:8080/users/${getValue("id")}`, {
        method: "PATCH",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                return response.text();
            }
            return response.json();
        })
        .then(data => typeof data === 'string' ?
            updateFail(data) :
            updateSuccess("Информация изменина", data)
        );
}

function updateSuccess(text, data) {
    showNotification(text);
    const usernameEl = document.getElementById("username");
    usernameEl.innerText = data['username'];
    const emailEl = document.getElementById("email");
    emailEl.innerText = data['email'];
    const passwordEl = document.getElementById("password");
    passwordEl.innerText = data['password'];
}

function updateFail(text) {
    showNotification(text, "error");
}

function deleteUser() {
    if (!confirm("Вы точно хотите удалить профиль?")) {
        return;
    }

    fetch(`http://localhost:8080/users/${getValue("id")}`, {
        method: "DELETE"})
        .then(response => {
            if (!response.ok) {
                return response.text();
            }
            return null;
        })
        .then(data => typeof data === 'string' ?
            deleteFail(data) :
            deleteSuccess("Возвращайтесь!")
        );
}

function deleteFail(data) {
    showNotification(data, "error");
}

function deleteSuccess(data) {
    showNotification(data);
    document.cookie = "id=; path=/; Max-Age=-1;";
    setTimeout(window.location.href = "http://localhost:8080", 1000);
}