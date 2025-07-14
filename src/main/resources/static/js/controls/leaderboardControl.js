const rowsPerPage = 20;
const tableBody = document.querySelector('#dataTable tbody');
const currentPageSpan = document.getElementById('currentPage');
const totalPagesSpan = document.getElementById('totalPages');
const pageInput = document.getElementById('pageInput');
let totalPages;

if (userCount === null || data === null) {
    showNotification("Ошибка на сервере", "error");
} else {
    totalPages = Math.ceil(userCount / rowsPerPage);
    displayPage();
}



function displayPage() {
    tableBody.innerHTML = '';


    for (let user = 0; user < data.length; user++) {
        const tr = document.createElement('tr');
        const href = `http://localhost:8080/profile/${data[user]["id"]}`

        tr.onclick = () => {
            window.location.href = href;
        };

        tr.innerHTML = `
          <td>${data[user]['username']}</td>
          <td>${data[user]['typeResults']}</td>
          <td>${data[user]['time']}</td>
        `;
        tableBody.appendChild(tr);
    }

    currentPageSpan.textContent = currentPage;
}

function changePage(page) {
    getLeaderboard(page, rowsPerPage);
}

document.getElementById('prevBtn').addEventListener('click', () => {
    if (currentPage > 1) {
        changePage(currentPage - 1);
    }
});

document.getElementById('nextBtn').addEventListener('click', () => {
    if (currentPage < totalPages) {
        changePage(currentPage + 1);
    }
});

document.getElementById('goToBtn').addEventListener('click', () => {
    const page = parseInt(pageInput.value, 10);
    if (!isNaN(page) && page >= 1 && page <= totalPages) {
        changePage(page);
        pageInput.value = '';
    } else {
        alert(`Введите число от 1 до ${totalPages}`);
    }
});