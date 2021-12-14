package ch.tha.picturereminder;

import android.graphics.Bitmap;

public class Reminder {
    private String title;
    private String date;
    private String time;
    private Bitmap image;

    public Reminder(String title, String date, String time, Bitmap image) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
