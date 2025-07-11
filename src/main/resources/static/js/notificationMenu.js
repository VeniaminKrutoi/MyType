function showNotification(message, type = 'success') {
    const container = document.getElementById('notifications');

    const notification = document.createElement('div');
    notification.className = `notification ${type}`;
    notification.textContent = message;

    container.appendChild(notification);

    // Включаем анимацию появления
    setTimeout(() => {
        notification.classList.add('show');
    }, 10);

    // Удаляем через 5 секунд с анимацией ухода
    setTimeout(() => {
        notification.classList.remove('show');
        setTimeout(() => container.removeChild(notification), 500);
    }, 5000);
}