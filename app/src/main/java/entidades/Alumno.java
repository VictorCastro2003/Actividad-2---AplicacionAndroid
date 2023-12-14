package entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Alumno {

    @PrimaryKey
    @NonNull
    public String numControl;

    @NonNull
    @ColumnInfo(name = "Nombre")
    public String nombre;

    @NonNull
    @ColumnInfo(name = "Primer_Ap")
    public String primerAp;

    @NonNull
    @ColumnInfo(name = "Segundo_Ap")
    public String segundoAp;

    @NonNull
    @ColumnInfo(name = "Edad")
    public byte edad;

    @NonNull
    @ColumnInfo(name = "Semestre")
    public byte semestre;

    @NonNull
    @ColumnInfo(name = "Carrera")
    public String carrera;

    public Alumno(@NonNull String numControl, @NonNull String nombre, @NonNull String primerAp, @NonNull String segundoAp, byte edad, byte semestre, @NonNull String carrera) {
        this.numControl = numControl;
        this.nombre = nombre;
        this.primerAp = primerAp;
        this.segundoAp = segundoAp;
        this.edad = edad;
        this.semestre = semestre;
        this.carrera = carrera;
    }

    @NonNull
    public String getNumControl() {
        return numControl;
    }

    public void setNumControl(@NonNull String numControl) {
        this.numControl = numControl;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getPrimerAp() {
        return primerAp;
    }

    public void setPrimerAp(@NonNull String primerAp) {
        this.primerAp = primerAp;
    }

    @NonNull
    public String getSegundoAp() {
        return segundoAp;
    }

    public void setSegundoAp(@NonNull String segundoAp) {
        this.segundoAp = segundoAp;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public byte getSemestre() {
        return semestre;
    }

    public void setSemestre(byte semestre) {
        this.semestre = semestre;
    }

    @NonNull
    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(@NonNull String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "numControl='" + numControl + '\'' +
                ", nombre='" + nombre + '\'' +
                ", primerAp='" + primerAp + '\'' +
                ", segundoAp='" + segundoAp + '\'' +
                ", edad=" + edad +
                ", semestre=" + semestre +
                ", carrera='" + carrera + '\'' +
                '}';
    }
}
