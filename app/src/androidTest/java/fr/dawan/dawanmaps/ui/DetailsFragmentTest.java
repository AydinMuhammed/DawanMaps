package fr.dawan.dawanmaps.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import android.os.Bundle;

import fr.dawan.dawanmaps.R;
import fr.dawan.dawanmaps.model.TrainingCenter;

/**
 * Tests Espresso du fragment de détails.
 * Vérifie que l'adresse complète d'un centre s'affiche correctement.
 */
@RunWith(AndroidJUnit4.class)
public class DetailsFragmentTest {

    /** Centre factice simulant une réponse de l'API. */
    private TrainingCenter fakeCenter() {
        return new TrainingCenter(
                7,                 // id
                "Paris",           // name
                "11 rue Antoine Bourdelle", // address
                48.842800140381,   // latitude
                2.318579912186,    // longitude
                "75015",           // zipCode
                "Paris",           // city
                "France",          // country
                "dans le 15e arrondissement", // furtherInfo
                null,              // mapUrl
                true,              // office
                true);             // isPmi
    }

    /** Vérifie que l'adresse complète du centre s'affiche. */
    @Test
    public void displaysAddress() {
        Bundle args = new Bundle();
        args.putSerializable("arg_center", fakeCenter());

        FragmentScenario.launchInContainer(
                DetailsFragment.class,   // la Classe (pas une instance)
                args,                    // les arguments du fragment
                R.style.Theme_DawanMaps  // le thème requis pour oublier la fabrique
        );

        onView(withId(R.id.detail_address))
                .check(matches(withText("11 rue Antoine Bourdelle")));
    }
}
