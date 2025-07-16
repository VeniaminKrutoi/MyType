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

            fetch('http://localhost:8080/texts', {
                method: 'POST',
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
                .then(data => typeof data === "string" ? connectionFail(data) : connectionSuccess())
                .catch(error => connectionFail(error.message));
        }
    );

function connectionSuccess() {
    console.log("success");
    showNotification("Текст отправлен на проверку");
}

function connectionFail(error) {
    console.log(error);
    showNotification(error, "error");
}