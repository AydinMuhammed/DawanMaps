package fr.dawan.dawanmaps.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Fabrique l'instance unique de {@link DawanApi}.
 * Pattern "Singleton" : une seule instance partagée dans toute l'application.
 */
public class ApiClient {

    /** URL de base du web service Dawan (doit se terminer par "/"). */
    private static final String BASE_URL = "https://dawan.org/";

    private static volatile DawanApi instance = null;

    /** Constructeur privé : interdit d'instancier cette classe. */
    private ApiClient() {
    }

    /**
     * Retourne l'unique instance de DawanApi (créée une seule fois).
     *
     * @return l'API utilisable pour interroger le service
     */
    public static DawanApi getApi() {
        // Double vérification pour la sécurité en multi-thread.
        if (instance == null) {
            synchronized (ApiClient.class) {
                if (instance == null) {
                    instance = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(DawanApi.class);
                }
            }
        }
        return instance;
    }

}
