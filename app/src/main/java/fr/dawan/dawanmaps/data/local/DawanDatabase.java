package fr.dawan.dawanmaps.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import fr.dawan.dawanmaps.model.TrainingCenter;

/**
 * La base de données SQLite de l'application (via Room).
 * Utilise le pattern Singleton : une seule instance pour toute l'app.
 */
@Database(entities = {TrainingCenter.class}, version = 1, exportSchema = false)
public abstract class DawanDatabase extends RoomDatabase {

    /** Nom du fichier SQLite créé sur l'appareil. */
    private static final String DB_NAME = "dawan_maps.db";

    private static volatile DawanDatabase instance = null;

    /** DAO exposé par la base. */
    public abstract TrainingCenterDao trainingCenterDao();

    /**
     * Retourne l'instance unique de la base.
     *
     * @param context contexte de l'application
     * @return la base de données
     */
    public static DawanDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (DawanDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    DawanDatabase.class,
                                    DB_NAME)
                            .build();
                }
            }
        }
        return instance;
    }

}
