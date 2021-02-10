package parkingApp.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer carId;

    @Column(name = "date_in")
    private long dateIn;

    @Column(name = "date_out")
    private long dateOut;

    @OneToOne(mappedBy = "car")
    private Parking parking = new Parking();

    public Car(long dateIn) {
        this.dateIn = dateIn;
    }

    public Car() {
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public long getDateIn() {
        return dateIn;
    }

    public void setDateIn(long dateIn) {
        this.dateIn = dateIn;
    }

    public long getDateOut() {
        return dateOut;
    }

    public void setDateOut(long dateOut) {
        this.dateOut = dateOut;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", parking=" + parking +
                '}';
    }
}
