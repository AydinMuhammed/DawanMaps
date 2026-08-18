package fr.dawan.dawanmaps.data;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import fr.dawan.dawanmaps.data.local.DawanDatabase;
import fr.dawan.dawanmaps.data.local.TrainingCenterDao;
import fr.dawan.dawanmaps.data.remote.ApiClient;
import fr.dawan.dawanmaps.data.remote.DawanApi;
import fr.dawan.dawanmaps.model.TrainingCenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository : point d'entrée unique vers les données.
 * Il applique la stratégie "offline first" :
 *  - si le réseau répond, on stocke la liste dans Room puis on la retourne ;
 *  - si le réseau échoue, on lit la dernière copie enregistrée dans Room.
 */
public class TrainingCenterRepository {

    private final DawanApi api;
    private final TrainingCenterDao dao;
    private final Executor executor;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public TrainingCenterRepository(Context context) {
        this.api = ApiClient.getApi();
        this.dao = DawanDatabase.getInstance(context).trainingCenterDao();
        // Thread d'arrière-plan pour écrire dans SQLite (interdit sur le thread principal).
        this.executor = Executors.newSingleThreadExecutor();
    }

    /**
     * Récupère les centres : d'abord le réseau, sinon le cache local.
     *
     * @param callback interface appelée quand le chargement est terminé
     */
    public void refresh(RepositoryCallback callback) {
        api.getTrainingCenters().enqueue(new Callback<List<TrainingCenter>>() {
            @Override
            public void onResponse(Call<List<TrainingCenter>> call,
                                   Response<List<TrainingCenter>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<TrainingCenter> centers = response.body();
                    // Sauvegarde en base sur un thread séparé (Room l'exige).
                    executor.execute(() -> {
                        dao.deleteAll();
                        dao.insertAll(centers);
                        postResult(callback, centers);
                    });
                } else {
                    // Réponse serveur invalide -> repli sur le cache.
                    loadFromCache(callback);
                }
            }

            @Override
            public void onFailure(Call<List<TrainingCenter>> call, Throwable t) {
                // Erreur réseau -> repli sur le cache.
                loadFromCache(callback);
            }
        });
    }

    /** Lit la dernière liste enregistrée dans Room, en arrière-plan. */
    private void loadFromCache(RepositoryCallback callback) {
        executor.execute(() -> postResult(callback, dao.getAll()));
    }

    /** Renvoie le résultat sur le thread principal (obligatoire pour toucher l'UI). */
    private void postResult(RepositoryCallback callback, List<TrainingCenter> centers) {
        mainHandler.post(() -> callback.onSuccess(centers));
    }

    /** Contrat de retour asynchrone vers l'interface utilisateur. */
    public interface RepositoryCallback {
        void onSuccess(List<TrainingCenter> centers);
    }
}
