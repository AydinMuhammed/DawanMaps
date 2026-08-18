package fr.dawan.dawanmaps;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import fr.dawan.dawanmaps.ui.MapFragment;

/**
 * Activité principale de l'application.
 * Ne contient qu'un conteneur de fragments ; le premier fragment affiché
 * est la carte OpenStreetMap ({@link MapFragment}).
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Affiche le fragment carte dès l'ouverture (une seule fois).
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new MapFragment())
                    .commit();
        }
    }
}