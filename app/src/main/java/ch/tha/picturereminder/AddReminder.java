package ch.tha.picturereminder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddReminder extends AppCompatActivity {
    private EditText titleTxt;
    private EditText dateTxt;
    private EditText timeTxt;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        titleTxt = (EditText) findViewById(R.id.nameRe);
        dateTxt = (EditText) findViewById(R.id.dateRe);
        timeTxt = (EditText) findViewById(R.id.timeRe);
        Button cancelBtn = findViewById(R.id.cancelBtnAdd);
        Button createBtn = findViewById(R.id.createBtn);

        dateTxt.setOnClickListener(view -> popDatePickerDialog());
        cancelBtn.setOnClickListener(view -> onClickCancel());
        timeTxt.setOnClickListener(view -> poptimePickerDialog());
        createBtn.setOnClickListener(view -> onClickCreate());
    }

    private void popDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(AddReminder.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateTxt.setText(dayOfMonth + "." + (month + 1) + "." + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void poptimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(AddReminder.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeTxt.setText(hourOfDay + ":" + minute);
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }

    private void onClickCancel() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        titleTxt.setText("");
        timeTxt.setText("");
        dateTxt.setText("");
        startActivity(intent);
    }

    private void onClickCreate() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        String titleStr = titleTxt.getText().toString();
        String dateStr = dateTxt.getText().toString();
        String timeStr = timeTxt.getText().toString();

        intent.putExtra("titelReminder", titleStr);
        intent.putExtra("dateReminder", dateStr);
        intent.putExtra("timeReminder", timeStr);
        startActivity(intent);
    }
}