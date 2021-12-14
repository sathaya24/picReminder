package ch.tha.picturereminder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String>dummie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(view -> addReminder());

        listView = (ListView) findViewById(R.id.listReminder);
        dummie=new ArrayList<>();
        dummie.add("Test1");
        dummie.add("Test2");
        dummie.add("Test3");
        dummie.add("Test4");
        dummie.add("Test5");
        showReminder();

    }

    private void addReminder() {
        Intent intent = new Intent(getApplicationContext(), AddReminder.class);
        startActivity(intent);
    }

    private void showReminder(){
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,dummie);
        listView.setAdapter(arrayAdapter);

    }
}