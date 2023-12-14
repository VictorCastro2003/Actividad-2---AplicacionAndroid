package bd;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import controladores.AlumnoDAO;
import entidades.Alumno;


@Database(entities = {Alumno.class}, version = 1, exportSchema = false)
public abstract class EscuelaBD extends RoomDatabase {

    public abstract AlumnoDAO alumnoDAO();

    private static EscuelaBD INSTANCE;
    public static EscuelaBD getAppDatabase(Context context){
        if(INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), EscuelaBD.class, "escuela").build();
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

}
