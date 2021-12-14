package ch.tha.picturereminder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Reminder> myReminders;
    private Reminder reminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(view -> addReminder());

        listView = (ListView) findViewById(R.id.listReminder);
        myReminders = new ArrayList<>();

        Intent intent = getIntent();
        String title = intent.getStringExtra("titleReminder");
        String date = intent.getStringExtra("dateReminder");
        String time = intent.getStringExtra("timeReminder");
        Bitmap image = intent.getParcelableExtra("imageReminder");
        Reminder remi = new Reminder(title, date, time, image);
        myReminders.add(remi);

        ReminderListAdapter adapter = new ReminderListAdapter(this, R.layout.list_view_layout, myReminders);

        listView.setAdapter(adapter);
//        ReminderListAdapter.notifyDataSetChanged();
    }

    private void addReminder() {
        Intent intent = new Intent(getApplicationContext(), AddReminder.class);
        startActivity(intent);
    }

}