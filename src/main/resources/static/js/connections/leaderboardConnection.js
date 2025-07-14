function getLeaderboard(page, rowsPerPage) {
    const to = page * rowsPerPage;
    const from = to - rowsPerPage;

    window.location.href = `http://localhost:8080/leaderboard/?from=${from} to=${to}`;
}