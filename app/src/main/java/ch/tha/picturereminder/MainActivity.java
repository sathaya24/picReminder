package ch.tha.picturereminder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Reminder> myReminders;
    private Reminder reminder;
    private SharedPreferences preferences;
    public  static final String PREFERENCE_REMINDER="preference_reminder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(view -> addReminder());

        this.preferences = getPreferences(MODE_PRIVATE);

        listView = (ListView) findViewById(R.id.listReminder);
        myReminders = new ArrayList<>();

        Intent intent = getIntent();
        String title = intent.getStringExtra("titleReminder");
        String date = intent.getStringExtra("dateReminder");
        String time = intent.getStringExtra("timeReminder");
        Bitmap image = intent.getParcelableExtra("imageReminder");
        reminder = new Reminder(title, date, time, image);
        myReminders.add(reminder);

        ReminderListAdapter adapter = new ReminderListAdapter(this, R.layout.list_view_layout, myReminders);

        listView.setAdapter(adapter);
//        ReminderListAdapter.notifyDataSetChanged();
    }

    private void addReminder() {
        Intent intent = new Intent(getApplicationContext(), AddReminder.class);
        startActivity(intent);
    }

    private void saveReminder(){
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(reminder);
        editor.putString("myReminder",json);
        editor.apply();
    }

}