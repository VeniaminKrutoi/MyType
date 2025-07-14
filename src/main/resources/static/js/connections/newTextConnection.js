document.getElementById("addText")
    .addEventListener(
        "submit",
        function (event) {
            event.preventDefault();

            const data = {
                title: null,
                author: null,
                sourceLink: null,
                text: null,
                checked: true
            };

            data["title"] = document.getElementById("title").value;
            data["author"] = document.getElementById("author").value;
            data["sourceLink"] = document.getElementById("source").value;
            data["text"] = document.getElementById("text").value;

            fetch("http://localhost:8080/save/update", {
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
                .then(data => typeof data === "string" ? connectionFail() : connectionSuccess("Успех"));
        });

function connectionFail(text) {
    showNotification(text, "error");
}

function connectionSuccess(data) {
    showNotification(data);
    closeMenu();
}