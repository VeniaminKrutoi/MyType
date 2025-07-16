function openMenu(text) {
    document.getElementById("id").innerText = text["id"];
    document.getElementById("title").value = text["title"];
    document.getElementById("author").value = text["author"];
    document.getElementById("source").value = text["sourceLink"];
    document.getElementById("content").value = text["text"];

    document.getElementById('overlayMenu').style.display = 'block';
}

function closeMenu() {
    document.getElementById('overlayMenu').style.display = 'none';
}

function populateTable() {
    const tbody = document.querySelector("table tbody");
    texts.forEach(text => {
    const row = document.createElement("tr");
    const cell = document.createElement("td");
    console.log(text['text']);
    cell.textContent = text['text'];
    cell.style.fontSize = "24px";
    cell.style.cursor = "pointer";
    cell.onclick = () => openMenu(text);
    row.appendChild(cell);
    tbody.appendChild(row);
    });
    }
    window.onload = populateTable;

function setValue(text) {

}