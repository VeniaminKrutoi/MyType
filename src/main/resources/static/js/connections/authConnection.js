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

            fetch("http://localhost:8080/users", {
                method: "POST",
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
                    }
                )
                .then(data => typeof data === 'string' ?
                    connectionFail(data) :
                    connectionSuccess("Аккаунт создан. Добро пожаловать!", data)
                );
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

            fetch("http://localhost:8080/login", {
                method: "POST",
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
                    connectionFail(data) :
                    connectionSuccess("Добро пожаловать!", data)
                );
        }
    );

function connectionSuccess(text, user) {
    addCookie("id", user['id']);

    console.log("success");
    showNotification(text);
    setTimeout(window.location.href = `http://localhost:8080/profile/${user['id']}`, 1000);
}

function connectionFail(error) {
    console.log(error);
    showNotification(error, "error")
}