package com.example.adrian.adminbuenosdiasbebe;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cice on 8/4/17.
 */

public class UploadImage extends AsyncTask<Bitmap, Void, String>{

    private final static String URL_SERVIDOR = "http://femxa-ebtm.rhcloud.com/BuenosDiasBebe";
    private final Context context;

    public UploadImage(Context context){
        this.context = context;
    }

    @Override
    protected edu.val.cice.buenosdias.BuenosDias doInBackground(Bitmap... bitmaps) {
        String str_dev  = null;

        URL url = null;
        HttpURLConnection httpURLConnection = null;

        try{
            url = new URL(URL_SERVIDOR);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");

            Bitmap bitmap = bitmaps[0];

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] imagen_bytes = byteArrayOutputStream.toByteArray();
            String img_codificada = Base64.encodeToString(imagen_bytes, Base64.DEFAULT);

            OutputStream os = httpURLConnection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            osw.write(img_codificada);

            osw.close();

            int respuesta = httpURLConnection.getResponseCode();

            str_dev = (respuesta == 200) ? "OK" : null;


        }catch (Exception e){
            Log.e("MENSAJE", "ERROR: ", e);
        }

        return str_dev;
    }

    @Override
    protected void onPostExecute(String s) {
        if(s.equals("OK")){

        }
    }
}
