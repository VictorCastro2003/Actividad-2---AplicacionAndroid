package controladores;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import entidades.Alumno;

@Dao
public interface AlumnoDAO {

    //-------------------- ALTAS -------------------------
    @Insert
    public void agregarAlumno(Alumno a);
    @Insert
    public void agregarAlumnos(Alumno...alumnos);

    //-------------------- BAJAS -------------------------
    @Delete
    public void eliminarAlumno(Alumno a); //borrar sin SQL
    @Query("DELETE FROM alumno WHERE numControl = :nc")
    public void eliminarAlumnoPorNumControl(String nc); //borrar SQL

    //-------------------- CAMBIOS -------------------------
    @Update
    public void modificarAlumno(Alumno a);
    @Query("UPDATE alumno SET nombre = :nombre, Primer_Ap = :primerAp, Segundo_Ap = :segundoAp, edad = :edad, semestre = :semestre, carrera = :carrera WHERE numControl = :numControl")
    void modificarAlumnoPorNumControl(String nombre, String primerAp, String segundoAp, byte edad, byte semestre, String carrera, String numControl);

    //-------------------- CONSULTAS -------------------------
    @Query("SELECT * from Alumno")
    public List<Alumno> obtenerTodos();

    @Query("SELECT * FROM alumno WHERE numControl = :numControl")
    public Alumno buscarAlumnoPorNumControl(String numControl);

}
