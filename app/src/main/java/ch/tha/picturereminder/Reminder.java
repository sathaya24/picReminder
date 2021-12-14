package ch.tha.picturereminder;

import android.graphics.Bitmap;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reminder {
    private String title;
    private LocalDate date;
    private LocalTime time;
    private Bitmap image;

    public Reminder(String title, LocalDate date, LocalTime time, Bitmap image) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.image = image;
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
