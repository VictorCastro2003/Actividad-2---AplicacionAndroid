package com.example.bd_sqlite_room_v2;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import bd.EscuelaBD;
import entidades.Alumno;

public class ActivityConsultas extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<Alumno> datos;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Obtener datos desde la base de datos local en SQLite
        // EscuelaBD bd = EscuelaBD.getAppDatabase(getBaseContext());
        EscuelaBD bd = EscuelaBD.getAppDatabase(getBaseContext());
        new Thread(new Runnable() {
            @Override
            public void run() {

                datos = (ArrayList<Alumno>) bd.alumnoDAO().obtenerTodos();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<Alumno> datos2 = new ArrayList<>();
                        datos2.add(new Alumno("1","1","1",
                                "1", (byte)1,(byte)1, "1"));
                        datos2.add(new Alumno("2","2","2",
                                "2", (byte)2,(byte)2, "2"));

                        adapter = new CustomAdapter(datos);
                        recyclerView.setAdapter(adapter);

                        //ArrayAdapter adaptador = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, listaAlumnos);
                        //listviewAlumnos.setAdapter(adaptador);
                    }
                });

            }
        }).start();
    }
}// clase ActivityConsultas

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    //private String[] localDataSet;

    private ArrayList<Alumno> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    //public CustomAdapter(String[] dataSet) {
    //    localDataSet = dataSet;
    //}
    public CustomAdapter(ArrayList<Alumno> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.getTextView().setText(localDataSet.get(position).toString());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}