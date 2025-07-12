function editUsername() {
    const current = document.getElementById('username').textContent;
    const newName = prompt("Введите новое имя:", current);
    if (newName) {
        document.getElementById('username').textContent = newName;
    }
}

function editEmail() {
    const current = document.getElementById('email').textContent;
    const newEmail = prompt("Введите новую почту:", current);
    if (newEmail) {
        document.getElementById('email').textContent = newEmail;
    }
}