package ch.tha.picturereminder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Reminder>myReminders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(view -> addReminder());

        listView = (ListView) findViewById(R.id.listReminder);
        myReminders=new ArrayList<>();

        Intent intent = getIntent();
        String title = intent.getStringExtra("titleReminder");
        String date = intent.getStringExtra("dateReminder");
        String time = intent.getStringExtra("timeReminder");
        Bitmap image = intent.getParcelableExtra("imageReminder");

        Reminder reminder = new Reminder(title,date,time,image);

        myReminders.add(reminder);

        showReminder();

    }

    private void addReminder() {
        Intent intent = new Intent(getApplicationContext(), AddReminder.class);
        startActivity(intent);
    }

    private void showReminder(){
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,myReminders);
        listView.setAdapter(arrayAdapter);

    }
}