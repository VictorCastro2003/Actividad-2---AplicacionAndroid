package com.example.bd_sqlite_room_v2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import bd.EscuelaBD;

public class ActivityBajas extends Activity {

    EditText editTextControlNumber;
    Button btnEliminar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);

        editTextControlNumber = findViewById(R.id.editTextControlNumber);
        btnEliminar = findViewById(R.id.btnEliminar);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarAlumno();
            }
        });
    }

    private void eliminarAlumno() {
        String controlNumber = editTextControlNumber.getText().toString();

        if (controlNumber.isEmpty()) {
            Toast.makeText(this, "Ingrese un número de control válido", Toast.LENGTH_SHORT).show();
            return;
        }

        EscuelaBD bd = EscuelaBD.getAppDatabase(getBaseContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                bd.alumnoDAO().eliminarAlumnoPorNumControl(controlNumber);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Registro eliminado correctamente",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }
}


