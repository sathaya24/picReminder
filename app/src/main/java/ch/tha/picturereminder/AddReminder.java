package ch.tha.picturereminder;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Calendar;

//todo: sharedPreference
//todo: notification

public class AddReminder extends AppCompatActivity {
    private EditText titleTxt;
    private EditText dateTxt;
    private EditText timeTxt;
    private TextView errorTxt;
    private ImageView imageView;
    static final int REQUEST_IMAGE_CAPTURE = 100;
    private Bitmap takenImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        titleTxt = (EditText) findViewById(R.id.nameRe);
        dateTxt = (EditText) findViewById(R.id.dateRe);
        timeTxt = (EditText) findViewById(R.id.timeRe);
        errorTxt = (TextView) findViewById(R.id.errorPhoto);
        imageView = (ImageView) findViewById(R.id.imageCamera);
        Button cancelBtn = findViewById(R.id.cancelBtnAdd);
        Button createBtn = findViewById(R.id.saveBtn);
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
        try {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            errorTxt.setText("The camera ain't working ;(");
        }
    }

    private void popDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddReminder.this, new DatePickerDialog.OnDateSetListener() {
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

        TimePickerDialog timePickerDialog = new TimePickerDialog(AddReminder.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeTxt.setText(String.format("%02d:%02d", hourOfDay, minute));
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
        intent.putExtra("titleReminder", titleTxt.getText().toString());
        intent.putExtra("dateReminder", dateTxt.getText().toString());
        intent.putExtra("timeReminder", timeTxt.getText().toString());
        intent.putExtra("imageReminder",takenImage);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            takenImage = imageBitmap;
            imageView.setImageBitmap(imageBitmap);
        }
    }
}