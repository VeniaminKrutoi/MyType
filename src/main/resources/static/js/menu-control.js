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