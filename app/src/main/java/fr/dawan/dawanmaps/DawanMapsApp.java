package fr.dawan.dawanmaps;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import org.osmdroid.config.Configuration;

/**
 * Classe d'initialisation globale de l'application.
 * S'exécute avant toute activité et configure la librairie osmdroid.
 */
public class DawanMapsApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // osmdroid a besoin d'un objet "SharedPreferences" pour stocker sa configuration.
        // On lui fournit un espace de stockage dédié à notre application.
        SharedPreferences prefs = getSharedPreferences("dawan_maps_prefs", Context.MODE_PRIVATE);
        Configuration.getInstance().load(this, prefs);
        // Identification du user-agent HTTP (les serveurs de tuiles l'exigent).
        Configuration.getInstance().setUserAgentValue(getPackageName());
    }
}
