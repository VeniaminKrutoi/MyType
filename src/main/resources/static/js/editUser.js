function editUsername() {
    const current = document.getElementById('username').textContent;
    const newName = prompt("Введите новое имя:", current);
    if (!newName) {
        showNotification("Не удалось изменить имя");
    }
    document.getElementById('username').textContent = newName;

    fetch("http://localhost:8080/profile", {
        method: "POST"
    })
}

function editEmail() {
    const current = document.getElementById('email').textContent;
    const newEmail = prompt("Введите новую почту:", current);
    if (newEmail) {
        document.getElementById('email').textContent = newEmail;
    }
}
