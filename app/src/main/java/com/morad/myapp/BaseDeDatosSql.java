package com.morad.myapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//tiene que heredar de sql lite open helper
public class BaseDeDatosSql extends SQLiteOpenHelper {
    //es importante que herede de sql lite Helper, para que conozca lo necesario


    public BaseDeDatosSql(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }


//implementar estos dos metodos on create y onupgrade

    @Override
    public void onCreate(SQLiteDatabase baseDatos) {
//aqui se crean las tablas de la base de datos

        baseDatos.execSQL("CREATE TABLE Personas (nombre text primary key, apellido text, telefono integer, correo text)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase baseDatos, int oldVersion, int newVersion) {


    }


}
