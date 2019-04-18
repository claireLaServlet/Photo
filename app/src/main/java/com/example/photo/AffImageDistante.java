package com.example.photo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AffImageDistante extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private ImageView iv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affimagedis);

        chargerImgDistante cid = new chargerImgDistante();
        cid.execute();
        iv = (ImageView)findViewById(R.id.imagedist);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    private class chargerImgDistante extends AsyncTask<Void, Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(Void... voids) {
            //bitmap de retour
            Bitmap image = null;
            //tentative de recup de l'image externe

            try {
                //declaration de l'URL
                URL urlDeLimage = new URL("https://i0.wp.com/www.mascotarios.org/wp-content/uploads/2011/08/Bullterrier.jpg");
                HttpURLConnection con = (HttpURLConnection)urlDeLimage.openConnection();
                InputStream is = con.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(urlDeLimage.openConnection().getInputStream());
                //on recupere l'image et on lui affecte au bitmap
                image = (BitmapFactory.decodeStream(is));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return image;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
             //si on a qqch à afficher on l'affiche
            Bitmap bm = bitmap;
            if(bitmap!=null)
            {
                iv.setImageBitmap(bitmap);
                iv.setVisibility(View.VISIBLE);
            }
        }
    }


}
