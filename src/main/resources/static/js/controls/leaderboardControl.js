const rowsPerPage = 20;
let currentPage = 1;
const tableBody = document.querySelector('#dataTable tbody');
const currentPageSpan = document.getElementById('currentPage');
const totalPagesSpan = document.getElementById('totalPages');
const pageInput = document.getElementById('pageInput');
let totalPages;

function setTotalPages(totalRows) {
    let totalPages = Math.ceil(totalRows / rowsPerPage);
    totalPagesSpan.textContent = totalPages;
}

function displayPage(userList) {
    tableBody.innerHTML = '';

    for (const user of userList) {
        const tr = document.createElement('tr');
        tr.innerHTML = `
          <td>${user['username']}</td>
          <td>${user['spm']}</td>
          <td>${user['time']}</td>
        `;
        tableBody.appendChild(tr);
    }

    currentPage = page;
    currentPageSpan.textContent = currentPage;
}

document.getElementById('prevBtn').addEventListener('click', () => {
    if (currentPage > 1) {
        displayPage(currentPage - 1);
    }
});

document.getElementById('nextBtn').addEventListener('click', () => {
    if (currentPage < totalPages) {
        displayPage(currentPage + 1);
    }
});

document.getElementById('goToBtn').addEventListener('click', () => {
    const page = parseInt(pageInput.value, 10);
    if (!isNaN(page) && page >= 1 && page <= totalPages) {
        displayPage(page);
        pageInput.value = '';
    } else {
        alert(`Введите число от 1 до ${totalPages}`);
    }
});