package ch.tha.picturereminder;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reminder {
    private String title;
    private LocalDate date;
    private LocalTime time;

    public Reminder() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
