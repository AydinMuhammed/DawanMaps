package fr.dawan.dawanmaps.data.local;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import fr.dawan.dawanmaps.model.TrainingCenter;

/**
 * DAO (Data Access Object) : interface des opérations SQL sur la table training_center.
 * Room génère automatiquement l'implémentation SQL.
 */
@Dao
public interface TrainingCenterDao {

    /** Insère ou met à jour une liste de centres (remplace si l'id existe déjà). */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TrainingCenter> centers);

    /** Retourne tous les centres triés par nom. */
    @Query("SELECT * FROM training_center ORDER BY name ASC")
    List<TrainingCenter> getAll();

    /** Supprime toutes les lignes de la table. */
    @Query("DELETE FROM training_center")
    void deleteAll();
}
