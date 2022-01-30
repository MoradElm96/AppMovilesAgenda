package com.morad.myapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.regex.Pattern;

import morad.myapp.R;


public class Crud extends AppCompatActivity {
    private static final int RC_PHOTO_PICKER = 10;


    //declaramos las variables de tipo editdetxt;

    EditText editTextTextNombre, editTextTextApellidos, editTextNumberTlfn, editTextTextMail;


    //creamos objeto de la clase de  base de datos
    BaseDeDatosSql baseDeDatosSql;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administar_personas);


      //referencia de los 4 objetos visuales
        editTextTextNombre = findViewById(R.id.editTextTextNombre);
        editTextTextApellidos = findViewById(R.id.editTextTextApellidos);
        editTextNumberTlfn = findViewById(R.id.editTextNumberTlfn);
        editTextTextMail = findViewById(R.id.editTextTextMail);


        //   imageViewcontacto= findViewById(R.id.imgViewContacto);
        //le indicamos los parametros ,nombre de la base de datos y la version
        baseDeDatosSql = new BaseDeDatosSql(this, "basePersoonass", null, 1);


    }
    //metodo para valodar mail

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public void agregar(View view) {

        //controlamos que los textos no esten vacios y el email bien escrito
        if (editTextTextNombre.getText().toString().isEmpty()) {
            editTextTextNombre.setError("Este campo no puede quedar vacio");
        } else if (editTextTextApellidos.getText().toString().isEmpty()) {
            editTextTextApellidos.setError("Este campo no puede quedar vacio");
        } else if (editTextNumberTlfn.getText().toString().isEmpty()) {
            editTextNumberTlfn.setError("Este campo no puede quedar vacio");
        } else if (editTextNumberTlfn.getText().toString().trim().length() < 9) {
            editTextNumberTlfn.setError("debe contener al menos 9 digitos");
        } else if (!validarEmail(editTextTextMail.getText().toString())) {
            editTextTextMail.setError("Email no válido");
        } else if (editTextTextMail.getText().toString().isEmpty()) {
            editTextTextMail.setError("Este campo no puede estar vacio");
        } else {

            //abrimos la conexion a la base de datos
            SQLiteDatabase bd = baseDeDatosSql.getWritableDatabase();

            //creamos objeto content para pasarle los datos de los campos de la table

            ContentValues registro = new ContentValues();
            //recogemos los parametros de los edit text, es  COMO UNA consulta preparada
            registro.put("nombre", editTextTextNombre.getText().toString());
            registro.put("apellido", editTextTextApellidos.getText().toString());
            registro.put("telefono", editTextNumberTlfn.getText().toString());
            registro.put("correo", editTextTextMail.getText().toString());


            //hace un insert, tenemos que pasarle el nombre de la tabla , el segundo para metro no lo usamos, y por ultimo el content value
            bd.insert("personas", null, registro); //la tabla, los campos y los valores

            //ahora procedemos a borrar los edit text para limpiar lo escrito de la pantalla
            editTextTextNombre.setText("");
            editTextTextApellidos.setText("");
            editTextNumberTlfn.setText("");
            editTextTextMail.setText("");

            //cerramos la base de datos
            bd.close();
            //mostramos mensaje para saber que se realizo correctamente
            Toast.makeText(this, "se almaceno el contacto", Toast.LENGTH_LONG).show();
            //con ayuda de datbase inspector de android estudio podemos ver los datos en el editor
        }

    }

    public void consultar(View view) { // consulta por nombre
        SQLiteDatabase bd = baseDeDatosSql.getWritableDatabase();
        //ahora consulta
        String nombre = editTextTextNombre.getText().toString();
        //objeto clase cursor
        //teniendo en cuenta el nombre
        Cursor fila = bd.rawQuery("select apellido,telefono,correo from personas where nombre='" + nombre + "'", null);
        //cursos para posicionarse el primero que encuentre

        if (fila.moveToFirst()) {// que se posicione la primera

            editTextTextApellidos.setText(fila.getString(0));//apellidos
            editTextNumberTlfn.setText(fila.getString(1));//telefono
            editTextTextMail.setText(fila.getString(2));//correo

            //ver datos en otra pantalla


        } else {
            Toast.makeText(this, "No existe la persona", Toast.LENGTH_LONG).show();
            //borramos los edit text
            editTextTextNombre.setText("");
            editTextTextApellidos.setText("");
            editTextNumberTlfn.setText("");
            editTextTextMail.setText("");

        }
        bd.close();
    }


    public void borrar(View view) {
        //borrar ingresando una nombre

        MensajeDialogo dialogo = new MensajeDialogo(getResources().getString(R.string.dialogoBorrar)); // con esto podemos traducirlo
        dialogo.show(getSupportFragmentManager(), "dialogo1");
        dialogo.ejecutarRespuesta(new MensajeDialogo.Respuestas() {
            @Override
            public void confirmar(DialogFragment dialog) {

                SQLiteDatabase bd = baseDeDatosSql.getWritableDatabase();
                String nombre = editTextTextNombre.getText().toString();
                int cantidad = bd.delete("personas", "nombre='" + nombre + "'", null); //cantidad de filas que se han borrado


                if (cantidad == 1) {
                    Toast.makeText(Crud.this, "Se elimino correctamente", Toast.LENGTH_LONG).show();
                    editTextTextNombre.setText("");
                    editTextTextApellidos.setText("");
                    editTextNumberTlfn.setText("");
                    editTextTextMail.setText("");

                } else {
                    Toast.makeText(Crud.this, "No existe esa persona ", Toast.LENGTH_LONG).show();
                }
                bd.close();
            }

            @Override
            public void cancelar(DialogFragment dialog) {

            }
        });


    }

    public void modificar(View view) {

        //para esta funcion hay que cosultar y despues borrar

        SQLiteDatabase bd = baseDeDatosSql.getWritableDatabase();
        String nombre = editTextTextNombre.getText().toString();

        ContentValues registro = new ContentValues();
        //recogemos los parametros de los edit text


        registro.put("apellido", editTextTextApellidos.getText().toString());
        registro.put("telefono", editTextNumberTlfn.getText().toString());
        registro.put("correo", editTextTextMail.getText().toString());


        int cantidad = bd.update("personas", registro, "nombre='" + nombre + "'", null);//devulve la cantidad de filas modificadas


        if (cantidad == 1) {
            Toast.makeText(this, "Se modifico correctamente", Toast.LENGTH_LONG).show();
            /*editTextTextPatente.setText("");
            editTextTextMarca.setText("");
            editTextNumberPrecio.setText("");
            editTextNumberModelo.setText("");*/


        } else {
            Toast.makeText(this, "No existe la personas ", Toast.LENGTH_LONG).show();

        }

        //cerramos la base de datos
        bd.close();

    }


}

