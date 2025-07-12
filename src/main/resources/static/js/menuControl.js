function toggleMenu() {
    const menu = document.getElementById("dropdownMenu");

    menu.style.visibility = menu.style.visibility === "visible" ? "hidden" : "visible";
}

function closeProfile() {
    document.cookie = "user=; path=/; expires=Thu, 01 Jan 1970 00:00:00 UTC";
    window.location.reload();
}

document.addEventListener("click", function (event) {
    const button = document.querySelector(".menu-button");
    const menu = document.getElementById("dropdownMenu");
    if (!button.contains(event.target) && !menu.contains(event.target)) {
        menu.style.visibility = "hidden";
    }
});