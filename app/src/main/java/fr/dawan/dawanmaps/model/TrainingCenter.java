package fr.dawan.dawanmaps.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Objet métier représentant un centre de formation Dawan.
 * Les annotations {@code @SerializedName} relient les champs JSON aux attributs Java.
 *
 * Entité Room représentant un centre de formation Dawan.
 * Chaque instance correspond à une ligne de la table "training_center".
 */

@Entity(tableName = "training_center") //Room crée une table SQL nommée training_center. Chaque attribut devient une colonne.
public class TrainingCenter implements Serializable {

    @PrimaryKey // clé primaire unique pour identifier chaque ligne.
    @SerializedName("id")
    private final int id;

    @SerializedName("name")
    private final String name;

    @SerializedName("address")
    private final String address;

    @SerializedName("latitude")
    private final double latitude;

    @SerializedName("longitude")
    private final double longitude;

    @SerializedName("zipCode")
    private final String zipCode;

    @SerializedName("city")
    private final String city;

    @SerializedName("country")
    private final String country;

    @SerializedName("furtherInfo")
    private final String furtherInfo;

    @SerializedName("mapUrl")
    private final String mapUrl;

    @SerializedName("office")
    private final boolean office;

    @SerializedName("isPmi")
    private final boolean isPmi;

    /**
     * Construit un centre de formation.
     *
     * Room l'utilise pour recréer un objet à partir d'une ligne de la base.
     * Gson l'utilise aussi pour reconstruire l'objet depuis le JSON, et
     * comme l'objet est "final" (aucun setter), il reste immuable.
     *
     * On choisit de faire "final" + un constructeur complet plutôt que des setters pour garantir l'immuabilité de l'objet.
     * Une fois crée il ne peut plus être modifié, ce qui est une bonne pratique pour les objets métier.
     *
     * @param id         identifiant unique du centre
     *      * @param name       nom du centre
     *      * @param address    adresse complète du centre
     *      * @param latitude   latitude géographique
     *      * @param longitude  longitude géographique
     *      * @param zipCode    code postal
     *      * @param city       ville
     *      * @param country    pays
     *      * @param furtherInfo information complémentaire (peut être null)
     *      * @param mapUrl     lien vers la carte Google (peut être null)
     *      * @param office     true si le centre dispose de locaux
     *      * @param isPmi      true si le centre est éligible au dispositif PMI/CPF
     */
    public TrainingCenter(int id, String name, String address, double latitude, double longitude, String zipCode, String city, String country, String furtherInfo, String mapUrl, boolean office, boolean isPmi) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.furtherInfo = furtherInfo;
        this.mapUrl = mapUrl;
        this.office = office;
        this.isPmi = isPmi;
    }

    // ---- Getters (lecture seule, bonne pratique : objet immuable car pas de setters) ----

    public int getId() { return id; }

    public String getName() { return name; }

    public String getAddress() { return address; }

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }

    public String getZipCode() { return zipCode; }

    public String getCity() { return city; }

    public String getCountry() { return country; }

    public String getFurtherInfo() { return furtherInfo; }

    public String getMapUrl() { return mapUrl; }

    public boolean isOffice() { return office; }

    public boolean isPmi() { return isPmi; }
}
