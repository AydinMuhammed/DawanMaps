package fr.dawan.dawanmaps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.not;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.dawan.dawanmaps.ui.MapFragment;

@RunWith(AndroidJUnit4.class)
public class MapActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /** Vérifie que la carte osmdroid est bien affichée au lancement. */
    @Test
    public void mapIsDisplayed() {
        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }

    /** Vérifie que l'indicateur de chargement a disparu après le chargement. */
    @Test
    public void loadingIndicatorIsHiddenAfterLoad() {
        onView(withId(R.id.loading)).check(matches(not(isDisplayed())));
    }
}
