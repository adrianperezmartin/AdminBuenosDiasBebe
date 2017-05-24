package com.example.adrian.adminbuenosdiasbb;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cice on 8/4/17.
 */

public class UploadImageTime extends AsyncTask<Bitmap, Float, String>{

    private final static String URL_SERVIDOR = "http://192.168.3.18:8080/DniApp/SubirImagen";
    private Context context;
    private ProgressBar pb;
    private  TextView tv;

    public UploadImageTime (Context context, ProgressBar pb, TextView tv){
        this.context = context;
        this.pb = pb;
        this.tv = tv;
    }

    @Override
    protected void onProgressUpdate(Float... values) {
        int valor = Math.round(values[0]);
        tv.setText("PROGRESO "+valor+ " /100");
        pb.setProgress(valor);
    }

    @Override
    protected String doInBackground(Bitmap... bitmaps) {
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

            /*OutputStream os = httpURLConnection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            osw.write(img_codificada);

            osw.close();*/

            int imgsize = img_codificada.length();
            int bloque = 128;
            int escrito = 0;
            int faltan = imgsize;

            OutputStreamWriter osw = new OutputStreamWriter(httpURLConnection.getOutputStream());

            while(faltan>=bloque){
                osw.write(img_codificada, escrito, bloque);
                faltan = faltan - bloque;
                escrito = escrito + bloque;
                publishProgress(new Float(escrito*100/imgsize));
            }
            if(faltan > 0)
                osw.write(img_codificada, escrito, faltan);

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
        pb.setProgress(100);
        tv.setText("PROGRESO 100 /100");

    }
}
