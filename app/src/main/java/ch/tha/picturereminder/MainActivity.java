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
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Reminder> myReminders;
    private Reminder reminder;
    public static final String PREFERENCE_REMINDER = "preference_reminder";
    private ReminderListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(view -> addReminder());
        listView = (ListView) findViewById(R.id.listReminder);
        loadReminders();
        adapter = new ReminderListAdapter(this, R.layout.list_view_layout, myReminders);
        addReminderItem();
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
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myReminders);
        editor.putString("myReminders", json);
        editor.apply();
        Toast.makeText(this, "reminder is saved", Toast.LENGTH_SHORT).show();
    }

    private void addReminderItem() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("titleReminder");
        String date = intent.getStringExtra("dateReminder");
        String time = intent.getStringExtra("timeReminder");
        Bitmap image = intent.getParcelableExtra("imageReminder");
        reminder = new Reminder(title, date, time, image);
        myReminders.add(reminder);
        adapter.notifyDataSetChanged();
        saveReminder();
    }

    private void loadReminders() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("myReminders", null);
        Type type = new TypeToken<ArrayList<Reminder>>() {
        }.getType();
        myReminders = gson.fromJson(json, type);

        if (myReminders == null) {
            myReminders = new ArrayList<>();
        }
    }
}