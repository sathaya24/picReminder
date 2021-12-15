package ch.tha.picturereminder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ChangeReminder extends AppCompatActivity {
    private EditText titleExt;
    private EditText dateExt;
    private EditText timeExt;
    private ImageView imageView;
    static final int REQUEST_IMAGE_CAPTURE = 100;
    private TextView errorTxt;
    private Bitmap takenImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_reminder);
        titleExt = (EditText) findViewById(R.id.nameRe);
        dateExt = (EditText) findViewById(R.id.dateRe);
        timeExt = (EditText) findViewById(R.id.timeRe);
        imageView = (ImageView) findViewById(R.id.imageRe);
        errorTxt = (TextView) findViewById(R.id.errorRe);
        Button cameraBtn = findViewById(R.id.photoBtnRe);
        Button cancelBtn = findViewById(R.id.cancelBtnAdd);
        Button saveBtn = findViewById(R.id.saveBtn);
        Button deleteBtn = findViewById(R.id.deleteBtn);
        cancelBtn.setOnClickListener(view -> cancelChange());

        Intent intent = getIntent();
        titleExt.setText(intent.getStringExtra("titleItem"));
        dateExt.setText(intent.getStringExtra("dateItem"));
        timeExt.setText(intent.getStringExtra("timeItem"));
        takenImage = intent.getParcelableExtra("imageItem");
        imageView.setImageBitmap(takenImage);

        dateExt.setOnClickListener(view -> popDatePickerDialog());
        timeExt.setOnClickListener(view -> poptimePickerDialog());
        cameraBtn.setOnClickListener(view -> takePicture());
        saveBtn.setOnClickListener(view -> onClickSave());
        deleteBtn.setOnClickListener(view -> onClickDelete());

    }

    private void cancelChange() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void popDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(ChangeReminder.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateExt.setText(dayOfMonth + "." + (month + 1) + "." + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void onClickDelete() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Delete isn't working, we are working on it.", Toast.LENGTH_SHORT).show();
    }

    private void poptimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(ChangeReminder.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeExt.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            errorTxt.setText("The camera ain't working ;(");
        }
    }

    private void onClickSave() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("titleReminder", titleExt.getText().toString());
        intent.putExtra("dateReminder", dateExt.getText().toString());
        intent.putExtra("timeReminder", timeExt.getText().toString());
        intent.putExtra("imageReminder", takenImage);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            if (takenImage != imageBitmap) {
                takenImage = imageBitmap;
                imageView.setImageBitmap(imageBitmap);
            }
        }
    }
}