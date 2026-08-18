package fr.dawan.dawanmaps.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.List;

import fr.dawan.dawanmaps.R;
import fr.dawan.dawanmaps.data.TrainingCenterRepository;
import fr.dawan.dawanmaps.model.TrainingCenter;

/**
 * Fragment affichant tous les centres Dawan sur une carte OpenStreetMap.
 * Charge les localisations via le repository puis les positionne en épingles.
 * Au clic sur une épingle, ouvre le fragment de détails du centre.
 */
public class MapFragment extends Fragment {

    private MapView mapView;
    private ProgressBar loadingBar;
    private TrainingCenterRepository repository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = view.findViewById(R.id.map);
        loadingBar = view.findViewById(R.id.loading);

        // Tuiles OpenStreetMap classiques + contrôle multi-doigts (pinch).
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);

        repository = new TrainingCenterRepository(requireContext());
        repository.refresh(this::displayCenters);
    }

    /** Place une épingle par centre puis cadre la carte sur l'ensemble. */
    private void displayCenters(List<TrainingCenter> centers) {
        loadingBar.setVisibility(View.GONE);
        mapView.getOverlays().clear();

        List<GeoPoint> points = new ArrayList<>();

        for (TrainingCenter center : centers) {
            GeoPoint position = new GeoPoint(center.getLatitude(), center.getLongitude());
            points.add(position);

            Marker marker = new Marker(mapView);
            marker.setPosition(position);
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            marker.setTitle(center.getName());      // titre affiché au tap
            // On retire la bulle d'info par défaut pour que le tap déclenche UNIQUEMENT la navigation.
            marker.setInfoWindow(null);

            // Au clic sur l'épingle, ouvre le fragment de détails du centre.
            marker.setOnMarkerClickListener((markerClicked, map) -> {
                openDetails(center);
                return true; // "true" = j'ai bien consommé le clic (pas de double action)
            });

            mapView.getOverlays().add(marker);
        }

        // Zoom automatique pour que toutes les épingles soient visibles.
        if (!points.isEmpty()) {
            BoundingBox box = BoundingBox.fromGeoPoints(points);
            mapView.zoomToBoundingBox(box, true);
        }
    }

    /** Remplace la carte par le fragment de détails, en gardant la possibilité de revenir. */
    private void openDetails(TrainingCenter center) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, DetailsFragment.newInstance(center))
                .addToBackStack(null)   // le bouton Retour reviendra à la carte
                .commit();
    }

    // Cycle de vie obligatoire pour osmdroid (chargement correct des tuiles).
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
}