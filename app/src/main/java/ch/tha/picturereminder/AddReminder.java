package ch.tha.picturereminder;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddReminder extends AppCompatActivity {
    private EditText title;
    private EditText date;
    private EditText time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        title = (EditText) findViewById(R.id.nameRe);
        date = (EditText) findViewById(R.id.dateRe);
        time = (EditText) findViewById(R.id.timeRe);
    }
}