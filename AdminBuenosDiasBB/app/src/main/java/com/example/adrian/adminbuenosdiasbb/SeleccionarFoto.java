package com.example.adrian.adminbuenosdiasbb;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by Adrian on 22/05/2017.
 */

public class SeleccionarFoto extends AppCompatActivity{

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            Log.d("MENSAJE", "El usuario seleccionó una foto");
            Uri uri = data.getData();
            //CONTENT PROVIDER
            Log.d("MENSAJE", "URI: "+uri.toString());

            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                ImageView imageView = (ImageView)findViewById(R.id.imagen);
                imageView.setImageBitmap(bitmap);

                Log.d("MENSAJE", "Foto seteada");
                //Intent explicito donde decimos que queremos pasar de esta clase a EnviarFoto
                Intent intent2 = new Intent(this, EnviarFoto.class);
                intent2.setData(uri);

                startActivity(intent2);

            }catch (Exception e){
                Log.e("ERROR", "ERROR: ", e);
            }
        }else{
            Log.d("MENSAJE", "El usuario NO seleccionó foto");
        }
    }
}
