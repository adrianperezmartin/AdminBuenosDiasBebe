package com.example.adrian.adminbuenosdiasbebe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public int dia, mes, anio;
    public String imagen;
    public static edu.val.cice.buenosdias.BuenosDias buenosDias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Listeners para los diferentes botones de la aplicacion
        addListenerOnButton();

    }

    public void addListenerOnButton() {
        ImageButton imgbtnSeleccionImagen = (ImageButton) findViewById(R.id.seleccionFoto);
        Button btnSeleccionFecha = (Button) findViewById(R.id.seleccionFecha);
        Button btnSubirImagen = (Button) findViewById(R.id.subirImagen);

        //Listener del boton donde seleccionamos la imagen
        imgbtnSeleccionImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(intent, 500);
            }
        });

        //Listener del boton para seleccionar la fecha
        btnSeleccionFecha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Fragment para crear el calendario donde seleccionamos la fecha
                DialogFragment fragmentCalendar = new CalendarioDialog();
                fragmentCalendar.show(getSupportFragmentManager(),"calendario");
            }
        });

        //Listener del boton para realizar la subida
        btnSubirImagen.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                new UploadImage().execute();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Log.d("MENSAJE", "El usuario seleccionó una foto");
            Uri uri = data.getData();
            //CONTENT PROVIDER
            Log.d("MENSAJE", "URI: "+uri.toString());

            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ImageView imageView = (ImageView)findViewById(R.id.seleccionFoto);
                imageView.setImageBitmap(bitmap);

                Log.d("MENSAJE", "Foto seteada");

            }catch (Exception e){
                Log.e("ERROR", "ERROR: ", e);
            }
        }



    }
}
