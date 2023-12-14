package com.example.bd_sqlite_room_v2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import bd.EscuelaBD;
import entidades.Alumno;

public class ActivityCambios extends Activity {

    EditText editTextNumControl, editTextNombre, editTextPrimerAp, editTextSegundoAp, editTextEdad, editTextSemestre, editTextCarrera;
    TextView textViewModificar;
    Button buttonSearch, buttonConfirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);

        editTextNumControl = findViewById(R.id.editTextNumControl);
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextPrimerAp = findViewById(R.id.editTextPrimerAp);
        editTextSegundoAp = findViewById(R.id.editTextSegundoAp);
        editTextEdad = findViewById(R.id.editTextEdad);
        editTextSemestre = findViewById(R.id.editTextSemestre);
        editTextCarrera = findViewById(R.id.editTextCarrera);
        textViewModificar = findViewById(R.id.textView12);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonConfirm = findViewById(R.id.buttonConfirm);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarAlumno();
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarAlumno();
            }
        });
    }

    private void modificarAlumno() {
        Log.i("MSJ->", "metodo modificar");

        EscuelaBD bd = EscuelaBD.getAppDatabase(getBaseContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String numControl = editTextNumControl.getText().toString();
                    String nombre = editTextNombre.getText().toString();
                    String primerAp = editTextPrimerAp.getText().toString();
                    String segundoAp = editTextSegundoAp.getText().toString();
                    byte edad = Byte.parseByte(editTextEdad.getText().toString());
                    byte semestre = Byte.parseByte(editTextSemestre.getText().toString());
                    String carrera = editTextCarrera.getText().toString();

                    if (nombre.isEmpty() || primerAp.isEmpty() || segundoAp.isEmpty() || carrera.isEmpty()) {
                        throw new IllegalArgumentException("Todos los campos son obligatorios.");
                    }

                    Alumno alumnoExistente = bd.alumnoDAO().buscarAlumnoPorNumControl(numControl);

                    if (alumnoExistente != null) {
                        if (!numControl.equals(alumnoExistente.getNumControl())) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),
                                            "Error: No se permite modificar el Número de Control.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            bd.alumnoDAO().modificarAlumnoPorNumControl(nombre, primerAp, segundoAp, edad, semestre, carrera, numControl);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),
                                            "REGISTRO MODIFICADO CORRECTAMENTE",
                                            Toast.LENGTH_SHORT).show();

                                    limpiarCampos();
                                }
                            });
                        }
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Error: El alumno con Número de Control " + numControl + " no existe.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (NumberFormatException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Error: Verifica que los campos numéricos contengan solo números.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (IllegalArgumentException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    private void buscarAlumno() {
        Log.i("MSJ->", "metodo buscar");

        EscuelaBD bd = EscuelaBD.getAppDatabase(getBaseContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String numControl = editTextNumControl.getText().toString();

                    Alumno alumno = bd.alumnoDAO().buscarAlumnoPorNumControl(numControl);

                    if (alumno != null) {
                        mostrarDatosAlumno(alumno);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "ALUMNO ENCONTRADO",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Alumno no encontrado",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (NumberFormatException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Error: El campo Número de Control debe contener solo números.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    private void mostrarDatosAlumno(Alumno alumno) {
        editTextNombre.setText(alumno.getNombre());
        editTextPrimerAp.setText(alumno.getPrimerAp());
        editTextSegundoAp.setText(alumno.getSegundoAp());
        editTextEdad.setText(String.valueOf(alumno.getEdad()));
        editTextSemestre.setText(String.valueOf(alumno.getSemestre()));
        editTextCarrera.setText(alumno.getCarrera());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewModificar.setVisibility(View.VISIBLE);
                buttonConfirm.setText("MODIFICAR");
            }
        });
    }

    private void limpiarCampos() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                editTextNumControl.setText("");
                editTextNombre.setText("");
                editTextPrimerAp.setText("");
                editTextSegundoAp.setText("");
                editTextEdad.setText("");
                editTextSemestre.setText("");
                editTextCarrera.setText("");
                textViewModificar.setVisibility(View.INVISIBLE);
                buttonConfirm.setText("CONFIRMAR");
            }
        });
    }
}
