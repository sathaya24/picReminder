package ch.tha.picturereminder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class changeReminder extends AppCompatActivity {
    private EditText titleExt;
    private EditText dateExt;
    private EditText timeExt;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_reminder);
        titleExt = (EditText) findViewById(R.id.nameRe);
        dateExt = (EditText) findViewById(R.id.dateRe);
        timeExt = (EditText) findViewById(R.id.timeRe);
        imageView = (ImageView) findViewById(R.id.imageRe);
        Button cameraBtn = findViewById(R.id.photoBtnRe);
        Button cancelBtn = findViewById(R.id.cancelBtnAdd);
        cancelBtn.setOnClickListener(view -> cancelChange());

        Intent intent = getIntent();
        titleExt.setText(intent.getStringExtra("titleItem"));
        dateExt.setText(intent.getStringExtra("dateItem"));
        timeExt.setText(intent.getStringExtra("timeItem"));
        imageView.setImageBitmap(intent.getParcelableExtra("imageItem"));
    }
    private void cancelChange(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}