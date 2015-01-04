function formatTime(seconds) {
    if (seconds < 0) seconds = 0;

    days = Math.floor(seconds/86400)
    seconds = seconds-(days*86400);
    hours = Math.floor(seconds/3600)
    seconds = seconds-(hours*3600);
    minutes = Math.floor(seconds/60)
    seconds = seconds-(minutes*60);

    if (days == 0) {
        if (hours == 0) {
            if (minutes < 2) {
                return "Онлайн";
            } else {
                return minutes + "мин. ";
            }
        } else {
            return hours + " ч.";
        }
    } else {
        return days + " д. " + hours + ' ч.';
    }

}


