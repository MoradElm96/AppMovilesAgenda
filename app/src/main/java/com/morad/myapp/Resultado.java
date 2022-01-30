package com.morad.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import morad.myapp.R;


public class Resultado extends AppCompatActivity {

    //definimos el resultado
    TextView textViewResultado;
    Button botonVolver;
    EditText editTxtRetorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        //localizamos la variable
        textViewResultado = findViewById(R.id.txtResultado);
        botonVolver = findViewById(R.id.buttonVolver);
        editTxtRetorno = findViewById(R.id.editTextRespuesta);

        //recuperamos los datos con objeto bundle

        Bundle datos = getIntent().getExtras();//manojo de datos
        int valor1 = datos.getInt("valor1");//hace referencia al name de la clase anterior
        int valor2 = datos.getInt("valor2");
        String operacion = datos.getString("operacion");

        //logica de la calculadora

        switch (operacion) {
            case "Sumar +":
                int suma = valor1 + valor2;
                textViewResultado.setText(valor1 + "+" + valor2 + "=" + suma);
                break;
            case "Restar -":
                int resta = valor1 - valor2;
                textViewResultado.setText(valor1 + "-" + valor2 + "=" + resta);
                break;
            case "Multiplicar *":
                int multiplicacion = valor1 * valor2;
                textViewResultado.setText(valor1 + "*" + valor2 + "=" + multiplicacion);
                break;
            case "Division /":
                int division = valor1 / valor2;
                textViewResultado.setText(valor1 + "/" + valor2 + "=" + division);
                break;

        }

    }

    public void volver(View vista) {


            Intent intento8 = new Intent(this, Calculadora.class);
            //pasamos los parametros convertidos a entero
            intento8.putExtra("valor6", editTxtRetorno.getText().toString());

           // startActivity(intento8);
        //nueva correcion para evitar consumo innecesario al pasar parametros
          startActivityForResult(intento8, 10);

    }

}