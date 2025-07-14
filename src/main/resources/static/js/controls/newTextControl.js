    const texts = ["НОВЫЙ ТЕКСТ", "ЕЩЁ ОДИН", "ДОБАВЛЕННЫЙ", "ТЕКСТ СЮДА"];

    function populateTable() {
    const tbody = document.querySelector("table tbody");
    texts.forEach(text => {
    const row = document.createElement("tr");
    const cell = document.createElement("td");
    cell.textContent = text;
    cell.style.fontSize = "24px";
    cell.style.cursor = "pointer";
    cell.onclick = openMenu;
    row.appendChild(cell);
    tbody.appendChild(row);
    });
    }

    // Вызовем populateTable при загрузке страницы
    window.onload = populateTable;

function setValue(text) {

}