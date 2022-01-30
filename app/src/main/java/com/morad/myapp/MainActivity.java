package com.morad.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;

import android.media.MediaPlayer;
import android.os.Bundle;

import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Toast;

import morad.myapp.R;


public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(this, R.raw.sonidoboton );


    }










    //crear metodo para mostrar y ocultar el menu
    //colocar nombre forzosamente
    //crear objeto de tipo menu, se agrega libreria
    public boolean onCreateOptionsMenu(Menu menu){
        //hacer uso de
        //r de carpeta res
          getMenuInflater().inflate(R.menu.overflow, menu);
          return true;

    }

    //metodo para funciones correspondientes a las opciones
    //nombre forzoso, recibir cual es el item seleccionado

    public  boolean onOptionsItemSelected(MenuItem item){
        //crear variable int para perimitir que obtenga el  item seleccionado
        int id= item.getItemId();//recupera el item que se esta selecionando

        if(id == R.id.item1){
            Toast.makeText( this, "se ha presionado la opcion 1 Informacion", Toast.LENGTH_LONG).show();
            mp.start();
            ejecutarInfo(null);//porque no hay vista que pasar porque no la necesitamos



        }else if(id == R.id.item2){
            Toast.makeText( this, "se ha presionado la opcion 2 ", Toast.LENGTH_LONG).show();
            mp.start();
          ejecutarVerPersonas(null);




        }else if(id == R.id.item3){
           // Toast.makeText( this, "Has pulsado Salir, nos vemos pronto!!!", Toast.LENGTH_LONG).show();
                mp.start();
                salirApp();

        }else if(id == R.id.item4){
            mp.start();
            //con este intent  abrimos los ajustes del movil
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            startActivity(intent);

        }
        //retornar

        return  super.onOptionsItemSelected(item) ;//ver para que se usa

    }






    public void ejecutarVerPersonas(View vista) {
        Intent intent6 = new Intent(this, VerPersonas.class);
        mp.start();//para reproducir el sonido
        //decir que comience la actividad
        startActivity(intent6);
    }





    //metodo salir de la app
    public  void salirApp(){
        //aqui hacemos uso del dialogo
        //llamamos al objeto y al constructor le pasamos el mensaje que esta guardado en values string
        MensajeDialogo dialogo = new MensajeDialogo(getResources().getString(R.string.dialogoSalir));//con esto hacemos referencia a los string para el idioma
        dialogo.show(getSupportFragmentManager(), "dialogo2");
        //capturamos implementando el metodo ejecutar respuesta
        dialogo.ejecutarRespuesta(new MensajeDialogo.Respuestas() { //implementamos la interfaz respuesta, genera este codigo

            @Override
            public void confirmar(DialogFragment dialog) {
                //aqui se proceden a programar las acciones
                Toast.makeText( MainActivity.this, "Has pulsado Salir, nos vemos pronto!!!", Toast.LENGTH_LONG).show();
                finish();


            }

            @Override
            public void cancelar(DialogFragment dialog) {
                //como es cancelar no necesario


            }
        });
        //acordarse de guardar datos

    }
    //metodo para ejecutar el layaout informacion
    public  void ejecutarInfo(View vista){
        //trabajar con intenciones
        //el this es para referencia a la clase donde estamos nosotros
        Intent intencion1 = new Intent(this, InformacionClase.class);
        mp.start();//para reproducir el sonido
        //decir que comience la actividad
        startActivity(intencion1);

    }

    public  void ejecutarLinterna(View vista){
        Intent intencion4 = new Intent(this, Linterna.class);
        mp.start();
        startActivity(intencion4);
    }

    public  void ejecutarBase(View vista){
        //trabajar con intenciones
        //el this es para referencia a la clase donde estamos nosotros
        Intent intencion2 = new Intent(this, Crud.class);
        //decir que comience la actividad
        mp.start();
        startActivity(intencion2);

    }
    public void ejecutarCalculadora(View vista){
        Intent intencion5 = new Intent(this,Calculadora.class);
        mp.start();
        startActivity(intencion5);
    }




}