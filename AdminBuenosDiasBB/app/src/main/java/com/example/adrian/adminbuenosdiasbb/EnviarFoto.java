package com.example.adrian.adminbuenosdiasbb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Adrian on 22/05/2017.
 */

public class EnviarFoto extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_foto);

        Uri uri = getIntent().getData();

        Intent intentCompartir = new Intent();
        intentCompartir.setAction(Intent.ACTION_SEND);
        intentCompartir.putExtra(Intent.EXTRA_STREAM, uri);
        intentCompartir.setType("image/jpeg");

        startActivity(Intent.createChooser(intentCompartir, "ENVIAR FOTO CON ..."));
    }
}
