function checkUser() {
    fetch("http://localhost:8080/profile/me", {
        method: "GET",
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                window.location.href = "http://localhost:8080/profile/auth";
            }
        });
}

function getUserData() {
    const errorButtonEl = document.getElementById("errorButton");
    const profileEl = document.getElementById("profile");
    const uploadingEl = document.getElementById("uploading");
    errorButtonEl.style.visibility = "hidden";
    profileEl.style.visibility = "hidden";
    uploadingEl.style.visibility = "visible";

    fetch("http://localhost:8080/profile/me", {
        method: "GET",
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(response.body.toString());
            }
            return response.json()
        })
        .then(data => connectionSuccess(data))
        .catch(error => connectionFail(error.message));
}

function connectionSuccess(data) {
    const nameEl = document.getElementById("username");
    nameEl.innerText = data['username'];

    const emailEl = document.getElementById("email");
    emailEl.innerText = data['email'];


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

function changeUsernameEmail(username, email) {
    const data = {
        username: username,
        email: email
    }
    //
    // fetch(`http://localhost:8080/profile/${username}`, {
    //     method: "PUT",
    //
    // })
}

checkUser();