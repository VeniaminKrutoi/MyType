document.getElementById("addText")
    .addEventListener(
        "submit",
        function (event) {
            event.preventDefault();
            window.dataPromise = null;

            const title = document.getElementById('title').value;
            const author = document.getElementById('author').value;
            const sourceLink = document.getElementById('source').value;
            const content = document.getElementById('content').value;

            if (content === null || content === "") {
                connectionFail("Нельзя отправлять пустой текст");
                return;
            }

            const data = {
                title: title,
                author: author,
                sourceLink: sourceLink,
                text: content
            };

            fetch('http://localhost:8080/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
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
    );

function connectionSuccess() {
    console.log("success");
    showNotification("Текст отправлен");
}

function connectionFail(error) {
    console.log(error);
    showNotification(error, "error");
}