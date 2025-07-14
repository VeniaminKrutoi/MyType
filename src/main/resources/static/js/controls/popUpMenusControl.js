function showNotification(message, type = 'success') {
    const container = document.getElementById('notifications');

    const notification = document.createElement('div');
    notification.className = `notification ${type}`;
    notification.textContent = message;

    container.appendChild(notification);

    setTimeout(() => {
        notification.classList.add('show');
    }, 10);

    setTimeout(() => {
        notification.classList.remove('show');
        setTimeout(() => container.removeChild(notification), 500);
    }, 5000);
}

function toggleMenu() {
    const menu = document.getElementById("dropdownMenu");

    menu.style.visibility = menu.style.visibility === "visible" ? "hidden" : "visible";
}

document.addEventListener("click", function (event) {
    const button = document.querySelector(".menu-button");
    const menu = document.getElementById("dropdownMenu");
    if (!button.contains(event.target) && !menu.contains(event.target)) {
        menu.style.visibility = "hidden";
    }
});

function checkId(){
    if (document.cookie.includes("id=")) {
        const exitButton = document.getElementById("exitButton");

        exitButton.style.color = "black";
        exitButton.disabled = false;
    }
}

function closeUser() {
    fetch('http://localhost:8080/close')
        .then(response => {
            if (!response.ok) {
                return response.text();
            }
            return null;
        })
        .then(data => typeof data === 'string' ?
            closeFail(data) :
            closeSuccess("До свидания!")
        );
}

function closeFail(data) {
    showNotification(data, "error");
}

function closeSuccess(data) {
    showNotification(data);
    document.cookie = "id=; path=/; Max-Age=-1;";
    setTimeout(window.location.reload(), 1000);
}

checkId();