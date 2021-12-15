package ch.tha.picturereminder;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ReminderListAdapter extends ArrayAdapter<Reminder> {
    private Context context;
    private int resource;
    private ArrayList<Reminder> listReminder;
    private int lastPosition = -1;

    public ReminderListAdapter(Context context, int resource, ArrayList<Reminder> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource=resource;
        this.listReminder=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String title = getItem(position).getTitle();
        String date = getItem(position).getDate();
        String time = getItem(position).getTime();
        Bitmap image = getItem(position).getImage();

        Reminder reminder = new Reminder(title, date, time, image);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView titleTvt = (TextView) convertView.findViewById(R.id.titleItem);
        TextView dateTvt = (TextView) convertView.findViewById(R.id.dateItem);
        TextView timeTvt = (TextView) convertView.findViewById(R.id.timeItem);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageItem);

        titleTvt.setText(title);
        dateTvt.setText(date);
        timeTvt.setText(time);
        imageView.setImageBitmap(image);

        return convertView;
    }

    @Override
    public Reminder getItem(int position) {
        return listReminder.get(position);
    }
}
