package com.morad.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import morad.myapp.R;

public class Calculadora extends AppCompatActivity { //heredar de activity

    //contendra algunos detalles sobre el desarrollador
    EditText edit1, edit2;
    Spinner sp1;
    TextView txtRetorno;
    TextView txtRetornoLAnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitycalculadora);// que muestre el layaout informacion
        edit1 = findViewById(R.id.editTextNumber2);
        edit2 = findViewById(R.id.editTextNumber3);
        txtRetorno = findViewById(R.id.textView2);
        // txtRetornoLAnd= findViewById(R.id.textView3);

        sp1 = findViewById(R.id.spinner);
        //configuramos el spiner
        String[] operaciones = {"Sumar +", "Restar -", "Multiplicar *", "Dividir /"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, operaciones);
        sp1.setAdapter(adapter);

        //ahora retornamos la respuesta
        Intent intento8 = getIntent();
        String valor = intento8.getStringExtra("valor6");
        txtRetorno.setText(valor);
        //txtRetornoLAnd.setText(valor);


      //ahora toca activarlo desde el main activity
    }

    public void mostrarResultado(View view) {
     //controlamos si esta vacio
        if (edit1.getText().toString().isEmpty()) {
            edit1.setError("este campo no puede estar vacio");
        } else if
            //sino contiene ningun caracter

        (edit2.getText().toString().isEmpty()) {
            edit2.setError("este campo no puede estar vacio");
        } else {


            Intent intento1 = new Intent(this, Resultado.class);
            //pasamos los parametros convertidos a enteros
            int valor1 = Integer.parseInt(edit1.getText().toString()); //lo que se introduce en los edit text
            int valor2 = Integer.parseInt(edit2.getText().toString());
            String operacion = sp1.getSelectedItem().toString();
            //para pasar a otra pantalla, clave y la variable
            intento1.putExtra("valor1", valor1);
            intento1.putExtra("valor2", valor2);
            intento1.putExtra("operacion", operacion);

            startActivity(intento1);
        }


    }
}