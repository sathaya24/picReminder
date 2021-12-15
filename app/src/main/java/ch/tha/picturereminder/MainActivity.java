package ch.tha.picturereminder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Reminder> myReminders;
    private Reminder reminder;
    private SharedPreferences preferences;
    public static final String PREFERENCE_REMINDER = "preference_reminder";
    private ReminderListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(view -> addReminder());

        this.preferences = getPreferences(MODE_PRIVATE);

        listView = (ListView) findViewById(R.id.listReminder);
        myReminders = new ArrayList<Reminder>();

        adapter = new ReminderListAdapter(this, R.layout.list_view_layout, myReminders);
        myReminders.add(addReminderItem());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ChangeReminder.class);
                Reminder remi = adapter.getItem(position);
                i.putExtra("titleItem", remi.getTitle());
                i.putExtra("dateItem", remi.getDate());
                i.putExtra("timeItem", remi.getTime());
                i.putExtra("imageItem", remi.getImage());
                startActivity(i);
            }
        });

    }

    private void addReminder() {
        Intent intent = new Intent(getApplicationContext(), AddReminder.class);
        startActivity(intent);
    }

    private void saveReminder() {
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(reminder);
        editor.putString("myReminder", json);
        editor.apply();
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "reminder is saved", Toast.LENGTH_SHORT).show();
    }

    private Reminder addReminderItem() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("titleReminder");
        String date = intent.getStringExtra("dateReminder");
        String time = intent.getStringExtra("timeReminder");
        Bitmap image = intent.getParcelableExtra("imageReminder");
        reminder = new Reminder(title, date, time, image);
        saveReminder();
        return reminder;
    }

    private void retrieveReminder() {
        Gson gson = new Gson();
        String json = preferences.getString("myReminder", "");
        Reminder remi = gson.fromJson(json, Reminder.class);
        myReminders.add(remi);
        adapter.notifyDataSetChanged();
    }
}