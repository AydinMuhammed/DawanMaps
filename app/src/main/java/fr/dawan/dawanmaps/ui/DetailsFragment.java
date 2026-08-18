package fr.dawan.dawanmaps.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.dawan.dawanmaps.R;
import fr.dawan.dawanmaps.model.TrainingCenter;

/**
 * Fragment affichant les détails complets d'un centre de formation.
 * Reçoit un objet {@link TrainingCenter} via ses arguments (Bundle).
 */
public class DetailsFragment extends Fragment {

    private static final String ARG_CENTER = "arg_center";

    /**
     * Fabrique : crée le fragment et lui attache le centre à afficher.
     *
     * @param center le centre cliqué sur la carte
     * @return une instance configurée de DetailsFragment
     */
    public static DetailsFragment newInstance(TrainingCenter center) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CENTER, center);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TrainingCenter center =
                requireArguments().getSerializable(ARG_CENTER, TrainingCenter.class);

        TextView name = view.findViewById(R.id.detail_name);
        TextView address = view.findViewById(R.id.detail_address);
        TextView city = view.findViewById(R.id.detail_city);
        TextView country = view.findViewById(R.id.detail_country);
        TextView furtherInfo = view.findViewById(R.id.detail_further_info);
        TextView officeBadge = view.findViewById(R.id.detail_office);
        TextView pmiBadge = view.findViewById(R.id.detail_pmi);

        name.setText(center.getName());
        address.setText(center.getAddress());

        // Ligne "code postal - ville" (gère les champs éventuellement absents).
        city.setText(buildCityLine(center));
        country.setText(center.getCountry());

        // Affichage conditionnel de l'info complémentaire (peut être null).
        if (center.getFurtherInfo() != null) {
            furtherInfo.setText(center.getFurtherInfo());
        } else {
            furtherInfo.setVisibility(View.GONE);
        }

        // Badges statut.
        officeBadge.setText(center.isOffice() ? "Centre avec locaux" : "Formation à distance");
        pmiBadge.setText(center.isPmi() ? "Éligible CPF (PMI)" : "Non éligible CPF");
    }

    /** Concatène code postal, ville et pays en gérant les valeurs nulles. */
    private String buildCityLine(TrainingCenter center) {
        StringBuilder line = new StringBuilder();
        if (center.getZipCode() != null) {
            line.append(center.getZipCode()).append(" ");
        }
        if (center.getCity() != null) {
            line.append(center.getCity());
        }
        return line.toString().trim();
    }
}