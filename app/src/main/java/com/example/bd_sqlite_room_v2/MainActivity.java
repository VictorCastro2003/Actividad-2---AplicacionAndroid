package com.example.bd_sqlite_room_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText cajaUsuario, cajaContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cajaUsuario = findViewById(R.id.caja_usuario);
        cajaContraseña = findViewById(R.id.caja_contraseña);


    }//onCreate

    //metodo enlazado al EVENTO CLIC DEL BOTON ACCEDER

    public void abrirMenu(View v){
        Toast.makeText(getApplicationContext(),
                "MAGIA",
                Toast.LENGTH_LONG).show();

        //sacar usuario y contraseña de BD
        //verificar si existe en BD
        //en caso correcto, abrir MENU

        Intent i = new Intent(this, ActivityMenu.class);
        startActivity(i);

    }

}//class