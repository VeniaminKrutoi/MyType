function getTotalUsers(page) {
    fetch("http://localhost:8080/users/count", {
        method: "POST"
    })
        .then(response => {
            if (!response.ok) {
                return response.text();
            }

            return response.json();
        })
        .then(data => typeof data === "string" ? countFail(data) : countSuccess(data, page));
}

function countFail(error) {
    showNotification(error, "error");
}

function countSuccess(data, page) {
    setTotalPages(data['count']);
    getLeaders(page, rowsPerPage);
}

function getLeaders(page, rowsPerPage) {
    const to = page * rowsPerPage;
    const from = to - rowsPerPage;

    get = `http://localhost:8080/users/?from=${from} to=${to}`;

    fetch(get)
        .then(response => {
            if (!response.ok) {
                return response.text();
            }

            return response.json();
        })
        .then(data => typeof data == "string" ? connectionFail(data) : connectionSuccess(data));
}

function connectionFail(error) {
    console.log(error);
    showNotification(error, "error");
}

function connectionSuccess(data) {
    console.log(data);
    setTotalPages(data[0]);
    displayPage(data[1]);
}