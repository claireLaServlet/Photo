package com.example.photo;

import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity  {

    private SeekBar seekbar;
    private TextView txt;
    private int nbImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.seekbar = (SeekBar)findViewById(R.id.seekBar1);
        this.txt = (TextView)findViewById(R.id.txt_nb_seekbar) ;

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //on change le nombre d'image à charger affiché, (rajouter 1)
                int fakeProgress = progress+1;
                txt.setText(String.valueOf(fakeProgress));
                nbImages = seekbar.getProgress();
                Log.i("TTTTTT", String.valueOf(nbImages));
            }
        });

        final Button btnChargerImDist = (Button) findViewById(R.id.btn_chargerImageDistante);
        final Button btnGalerie = (Button)findViewById(R.id.btn_affGalerie);

        btnChargerImDist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //renvoie vers la nouvelle page
                Intent intent = new Intent(MainActivity.this, AffImageDistante.class);
                startActivity(intent);
            }
        });

        btnGalerie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //renvoie vers la nouvelle page
               // Log.i("RRRRRRRRRRRRRRRRRRRRRRRRRRR", String.valueOf(nbImages));
                Intent intent = new Intent(MainActivity.this, AffGalerie.class);
                intent.putExtra("nbImage", String.valueOf(nbImages));
                startActivity(intent);
            }
        });




        //gestionSeekBar();
        //gestionBtn();

    }

    public void gestionBtn()
    {
        final Button btnChargerImDist = (Button) findViewById(R.id.btn_chargerImageDistante);
        final Button btnGalerie = (Button)findViewById(R.id.btn_affGalerie);

        btnChargerImDist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //renvoie vers la nouvelle page
                Intent intent = new Intent(MainActivity.this, AffImageDistante.class);
                Log.i("RRRRRRRRRRRRRRRRRRRRRRRRRRR", String.valueOf(nbImages));
                intent.putExtra("nbImage", String.valueOf(nbImages));
                startActivity(intent);
            }
        });

        btnGalerie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //renvoie vers la nouvelle page
                Intent intent = new Intent(MainActivity.this, AffGalerie.class);
                startActivity(intent);
            }
        });
    }

    public void gestionSeekBar(){
        this.seekbar = (SeekBar)findViewById(R.id.seekBar1);
        this.txt = (TextView)findViewById(R.id.txt_nb_seekbar) ;

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //on change le nombre d'image à charger affiché, (rajouter 1)
                int fakeProgress = progress+1;
                txt.setText(String.valueOf(fakeProgress));
                nbImages = seekbar.getProgress();
                Log.i("TTTTTT", String.valueOf(nbImages));
            }
        });
    }



}
