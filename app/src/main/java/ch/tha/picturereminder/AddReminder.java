package ch.tha.picturereminder;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Calendar;

//TODO: take Picture
//todo: save picture
//todo: sharedPreference
//todo: notification

public class AddReminder extends AppCompatActivity {
    private EditText titleTxt;
    private EditText dateTxt;
    private EditText timeTxt;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private TextView errorTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        titleTxt = (EditText) findViewById(R.id.nameRe);
        dateTxt = (EditText) findViewById(R.id.dateRe);
        timeTxt = (EditText) findViewById(R.id.timeRe);
        Button cancelBtn = findViewById(R.id.cancelBtnAdd);
        Button createBtn = findViewById(R.id.createBtn);
        Button cameraBtn = findViewById(R.id.photoBtn);

        dateTxt.setOnClickListener(view -> popDatePickerDialog());
        cancelBtn.setOnClickListener(view -> onClickCancel());
        timeTxt.setOnClickListener(view -> poptimePickerDialog());
        createBtn.setOnClickListener(view -> onClickCreate());
        cameraBtn.setOnClickListener(view -> takePicture());

        //ask for the permission
        if (ContextCompat.checkSelfPermission(AddReminder.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AddReminder.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //TODO:THE NEW WAY
        try {
            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException e) {
            errorTxt.setText("The camera ain't working ;(");
        }
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