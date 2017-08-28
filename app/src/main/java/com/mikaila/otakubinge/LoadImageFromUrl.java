package com.mikaila.otakubinge;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * <h1>LoadImageFromUrl</h1>
 * Use AsyncTask to load bitmap image of anime
 *
 * @author  Mikaila Smith
 * @version 1.0
 * @since   2017-06-01
 */
public class LoadImageFromUrl extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;

    public LoadImageFromUrl(ImageView imageView){
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String...urls){
        String urlOfImage = urls[0];
        Bitmap logo = null;
        try{
            InputStream is = new URL(urlOfImage).openStream();
            logo = BitmapFactory.decodeStream(is);
        }catch(Exception e){ // Catch the download exception
            e.printStackTrace();
        }
        return logo;
    }

    protected void onPostExecute(Bitmap result){
        imageView.setImageBitmap(result);
    }
}
