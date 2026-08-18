package fr.dawan.dawanmaps.data.remote;

import java.util.List;

import fr.dawan.dawanmaps.model.TrainingCenter;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Contrat du service web REST de Dawan.
 * Retrofit transforme cette interface en code HTTP réel.
 */
public interface DawanApi {

    /**
     * Interroge le web service Dawan et retourne la liste des centres de formation.
     *
     * @return un appel asynchrone fournissant une liste de {@link TrainingCenter}
     */
    @GET("public/location/") // verbe HTTP GET sur le chemin relatif. Retrofit le combine avec la base URL.
    Call<List<TrainingCenter>> getTrainingCenters(); //appel asynchrone qui doit retourner une liste de TrainingCenter. Le JSON est automatiquement converti par Gson.
}
