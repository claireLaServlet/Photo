package com.example.photo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class AffGalerie extends AppCompatActivity implements GestureDetector.OnGestureListener {


    private ArrayList<File> photos;
    private ViewPager gallery;
    private ImageView iv_img;
    //private int nbSelection;
    private int nbImages;
    private GestureDetector mGestureDestructor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aff_galerie);

        //on recupere l'intent
        Intent thisIntent = getIntent();
        //on recupere les parametres passés
        String nbSelection = thisIntent.getExtras().getString("nbImage");
        nbImages = Integer.parseInt(nbSelection);


        try {
            RecupererListeImages rli = new RecupererListeImages();
            rli.execute();
            rli.get();
            LinearLayout g_gal = findViewById(R.id.layout_gal);
            int i = 1;
            photos = RecupererListeImages.getList();
            for (File f : photos) {
                if (i <= nbImages) {

                    g_gal.addView ( insertPhoto(Uri.fromFile(f)) );
                    i++;
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    private View insertPhoto(final Uri chemin)
    {
        //on défini un layout
        LinearLayout layout = new LinearLayout(getApplicationContext());
        layout.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
        layout.setGravity(Gravity.CENTER);

        //une imageView avec le context de l'app en paramètre
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(195, 195));
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);


        //on affecte la Vignette à la vue
        imageView.setImageURI(chemin);

        //sur le click de la vignette on affiche la grande image, et on rend visible l'imageView
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_img.setImageURI(chemin);
                iv_img.setVisibility(View.VISIBLE);
            }
        });
        //on ajoute l'imageView au layout créé au dessus
        layout.addView(imageView);
        //on renvoi le tout
        return layout;
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


    public static class RecupererListeImages extends AsyncTask<Void, Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(Void... voids) {
            return null;
        }

        public static ArrayList<File> getList()
        {
            File[] files=null;
            ArrayList<File> listPhoto= new ArrayList<File>();
            File liste_file = new File("/mnt/sdcard/DCIM/Camera");

            files = liste_file.listFiles();


            for (File file_photo : files) {
                if(file_photo.getName().contains("."))
                {
                    listPhoto.add(file_photo);
                   // Log.i("LLLLLLLLLLLLLLL",  file_photo.getName());
                }

            }
            return listPhoto;
        }
    }
}
