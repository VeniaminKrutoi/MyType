function registration() {
    fetch("http://localhost:8080/profile/registration")
        .then(response => {
            if (!response.ok) {
                throw new Error("Не удалось подключиться к серверу");
            }
            return response.text();
        })
        .then(data => {
            if (data === "success") {
                connectionSuccess();
            } else {
                connectionFail(data)
            }
        })
        .catch(error => connectionFail(error.message));
}

function connectionSuccess(){
    console.log("success");
    showNotification("Аккаунт создан. Добро пожаловать!")
}

function connectionFail(error) {
    console.log(error);
    showNotification(error, "error")
}