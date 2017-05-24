package com.example.adrian.adminbuenosdiasbb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bitmap bitmap = null;

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.foto);

        //new UploadImage(this).execute(bitmap);
        ProgressBar pb = (ProgressBar)findViewById(R.id.progressbar);
        TextView tv = (TextView)findViewById(R.id.texto);

        new UploadImageTime(this, pb, tv).execute(bitmap);

    }

}
