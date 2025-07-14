function sendInfo(data){
    fetch("http://localhost:8080/save", {
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
        .then(data => typeof data === "string" ?
            connectionFail(data) :
            connectionSuccess("Данные сохранены")
        );
}

function connectionFail(data) {
    showNotification(data, "error");
}

function connectionSuccess(data) {
    showNotification(data);
}