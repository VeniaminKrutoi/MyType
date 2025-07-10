document.addEventListener("click", function (event) {
    const button = document.querySelector(".menu-button");
    const menu = document.getElementById("dropdownMenu");
    if (!button.contains(event.target) && !menu.contains(event.target) && menu.style.right === "20px") {
        menu.style.right = "-160px";
    }
});