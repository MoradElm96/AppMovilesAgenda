package com.morad.myapp;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import morad.myapp.R;

public class Linterna extends AppCompatActivity {

    MediaPlayer mp;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linterna);

        //declaramos aqui para evitar menos memoria
        ImageButton btn = (ImageButton) findViewById(R.id.buttonLinterna);
        //permiso para usar el flash, se maneja desde el camara manajer

        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        final String [] cameraId= {null};
        final Boolean[] encendino = {false};

        mp = MediaPlayer.create(this, R.raw.sonidoboton );

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!encendino[0]){

                    try{
                        cameraId[0]=cameraManager.getCameraIdList()[0];
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                            cameraManager.setTorchMode(cameraId[0], true);//hace que se encienda
                            btn.setImageResource(R.drawable.on);
                            mp.start();

                            encendino[0] = true;//transformar en final



                        }



                    }catch(CameraAccessException e ){
                        //para controlar el error
                        e.printStackTrace();
                    }
                }else {
                    try{
                        cameraId[0]=cameraManager.getCameraIdList()[0];
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                            cameraManager.setTorchMode(cameraId[0], false); //hace que se apague
                            btn.setImageResource(R.drawable.off);
                            mp.start();
                            encendino[0] = false;//transformar en final

                        }

                    }catch(CameraAccessException e ){
                        //para controlar el error
                        e.printStackTrace();
                    }
                    mp.start();


                }



            }
        });


    }
}
