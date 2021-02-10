package parkingApp.model;

import javax.persistence.*;

@Entity
@Table(name = "placesAvailable")
public class PlacesAvailable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "places_Available")
    private int placesAvailable;

    public PlacesAvailable(int placesAvailable) {
        this.placesAvailable = placesAvailable;
    }

    public PlacesAvailable() {
    }

    public int getPlacesAvailable() {
        return placesAvailable;
    }

    public void setPlacesAvailable(int placesAvailable) {
        this.placesAvailable = placesAvailable;
    }
}
