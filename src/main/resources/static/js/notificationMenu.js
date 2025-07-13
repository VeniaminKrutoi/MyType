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