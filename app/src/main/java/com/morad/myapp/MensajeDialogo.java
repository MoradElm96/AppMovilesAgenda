package com.morad.myapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import morad.myapp.R;

public class MensajeDialogo extends DialogFragment {
    //extendemos de dialgFragment para obtener sus propiedades y atributos
    //atributo mensaje

    private String mensaje;

    //constructor
    public MensajeDialogo(String mensaje) {
        this.mensaje = mensaje;
    }


    //implementamos el oncrerateDialog
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //nosa permitira crear un dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //mostramos el atributo mensaje
        builder.setMessage(mensaje);
        //en caso positivo
        builder.setPositiveButton(getResources().getString(R.string.dialogoConfirmar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                respuesta.confirmar(MensajeDialogo.this);//aqui llamamoas al metodo

            }
        });
        //en caso negativo
        builder.setNegativeButton(getResources().getString(R.string.dialogoCancelar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                respuesta.cancelar(MensajeDialogo.this);//aqui llamamoas al metodo

            }
        });
        return builder.create();

    }

    //declaramos metodo de tipo interfaz
    public interface Respuestas {
        public void confirmar(DialogFragment dialog);

        public void cancelar(DialogFragment dialog);
    }

    //variable respuesta
    private Respuestas respuesta;

    //metodo respuesta y le pasamos la respuesta
    public void ejecutarRespuesta(Respuestas r) {
        respuesta = r;
    }
}
