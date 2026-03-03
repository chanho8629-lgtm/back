const notice = document.querySelector('.notice-container');
const notificationPanel = document.getElementById('mainNotificationPanel');

if (notice) {
    notice.addEventListener("click", (e) => {
        e.preventDefault();
        e.stopPropagation();

        if (notificationPanel) {
            notificationPanel.classList.toggle('open');
        }

        const activeBadge = notice.querySelector('.NotificationBadge.active');
        if (activeBadge) {
            activeBadge.classList.remove('active');
        }
    });
}

document.addEventListener('click', (e) => {
    if (!notificationPanel || !notice) {
        return;
    }

    if (!notificationPanel.contains(e.target) && !notice.contains(e.target)) {
        notificationPanel.classList.remove('open');
    }
});
