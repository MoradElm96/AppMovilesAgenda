package com.morad.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

import morad.myapp.R;

public class VerPersonas extends AppCompatActivity {

    //construye el adaptador y otra lista
    ArrayList<Contacto> listaPersonas;
    RecyclerView recyclerViewPersonas;
    BaseDeDatosSql baseDeDatosSql;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_personas);
        baseDeDatosSql= new BaseDeDatosSql(this,"basePersoonass", null, 1);

        listaPersonas = new ArrayList<>();


        recyclerViewPersonas = (RecyclerView) findViewById(R.id.recyclerPersonas);//nos traera el elemento de item_personas
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        consultarListaPersonas();

        //instanciamos nueva lista

        AdaptadorDatos adaptadorDatos= new AdaptadorDatos(listaPersonas);
        recyclerViewPersonas.setAdapter(adaptadorDatos);




    }

    public void consultarListaPersonas(){ // consulta por nombre
        SQLiteDatabase bd= baseDeDatosSql.getWritableDatabase();
        //ahora consulta

        Contacto contacto= null;

            //referencia a cursos
        Cursor fila= bd.rawQuery("select * FROM personas"  , null);
        //cursos para posicionarse el primero que encuentre
        //recorremos la consulta
        while(fila.moveToNext()){
            contacto= new Contacto();

            contacto.setNombre(fila.getString(0));
            contacto.setApellido(fila.getString(1));

            contacto.setTelefono(fila.getInt(2));
            contacto.setCorreo(fila.getString(3));

            listaPersonas.add(contacto);
}

        bd.close();
    }


}

