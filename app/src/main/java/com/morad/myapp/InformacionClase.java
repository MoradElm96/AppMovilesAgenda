package com.morad.myapp;

import android.app.Activity;
import android.os.Bundle;

import morad.myapp.R;

public class InformacionClase extends Activity { //heredar de activity

    //contendra algunos detalles sobre el desarrollador


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion);// que muestre el layaout informacion

     //ahora toca activarlo desde el main activity
    }



}
