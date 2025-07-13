document.getElementById("registerForm")
    .addEventListener(
        "submit",
        function (event) {
            event.preventDefault();
            window.dataPromise = null;

            const username = document.getElementById("userNameReg").value;
            const email = document.getElementById("emailReg").value;
            const password = document.getElementById("passwordReg").value;

            if (username === null || username === "") {
                connectionFail("Имя должно быть обязательно");
                return;
            }

            if (email === null || email === "") {
                connectionFail("Почта должна быть обязательно");
                return;
            }

            if (password === null || password === "") {
                connectionFail("Пароль должен быть обязательно");
                return;
            }

            const data = {
                username: username,
                email: email,
                password: password
            }

            fetch("http://localhost:8080/profile/register", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error(response.body.toString());
                    }
                    return response.text();
                })
                .then(data => {
                    if (data === "success") {
                        connectionSuccess("Аккаунт создан. Добро пожаловать!");
                        addCookie("user", email);
                        window.location.href = "http://localhost:8080/profile";
                    } else {
                        connectionFail(data)
                    }
                })
                .catch(error => connectionFail(error.message));
        }
    );

document.getElementById("loginForm")
    .addEventListener(
        "submit",
        function (event) {
            event.preventDefault();
            window.dataPromise = null;

            const email = document.getElementById("emailLog").value;
            const password = document.getElementById("passwordLog").value;

            if (email === null || email === "") {
                connectionFail("Почта должна быть обязательно");
                return;
            }

            if (password === null || password === "") {
                connectionFail("Пароль должен быть обязательно");
                return;
            }

            const data = {
                email: email,
                password: password
            }

            fetch("http://localhost:8080/profile/login", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error(response.body.toString());
                    }
                    return response.text();
                })
                .then(data => {
                    if (data === "success") {
                        connectionSuccess("Добро пожаловать!");
                        addCookie("user", email);
                        window.location.href = "http://localhost:8080/profile";
                    } else {
                        connectionFail(data)
                    }
                })
                .catch(error => connectionFail(error.message));
        }
    );

function connectionSuccess(text) {
    console.log("success");
    showNotification(text)
}

function connectionFail(error) {
    console.log(error);
    showNotification(error, "error")
}