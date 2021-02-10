package parkingApp.logic;

import parkingApp.exceptions.NoParkingSpaceAvailableException;
import parkingApp.exceptions.ParkingWasNotPaidException;
import parkingApp.model.Car;
import parkingApp.model.Parking;
import parkingApp.model.PlacesAvailable;
import parkingApp.repository.RepositoryCar;
import parkingApp.repository.RepositoryParking;
import parkingApp.repository.RepositoryPlacesAvailable;


import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Logic {
    Parking parking = new Parking();
    RepositoryCar repositoryCar = new RepositoryCar();
    RepositoryParking repositoryParking = new RepositoryParking();
    RepositoryPlacesAvailable repositoryPlacesAvailable=new RepositoryPlacesAvailable();

    public Logic() {
    }

    public int enterIntoParking() throws NoParkingSpaceAvailableException {
        if (repositoryPlacesAvailable.fiindById().getPlacesAvailable()> 0) {
            repositoryPlacesAvailable.decreasePlacesAvailable();
            Integer carId = repositoryCar.addCar();
            Car carById = repositoryCar.findCarById(carId);
            repositoryParking.addCarIntoParking(new Parking(carById));
            return carId;//salveaza si returneaza
        } else {
            throw new NoParkingSpaceAvailableException("We are sorry, the parking is full!");
        }
    }

    public long getMinutes(int carId) {
        Car car = repositoryCar.findCarById(carId);
        long databaseTime = car.getDateIn();
        long difference = System.currentTimeMillis() - databaseTime;
        return TimeUnit.MILLISECONDS.toMinutes(difference);
    }

    public double amountToPay(long timeInMinutes) {
        double fee = 0.10;
        double amountToPay;
        if (timeInMinutes < 15) {
            amountToPay = 2;
        } else if (timeInMinutes < 1440) {
            amountToPay = 300;
        } else
            amountToPay = timeInMinutes * fee;
        return amountToPay;
    }

    public boolean getOutOfParking(double amountToPay, double amountPaid, int carId) throws ParkingWasNotPaidException {
        if (amountToPay <= amountPaid && !repositoryParking.fiindIfIsPaid(carId)) {
            repositoryParking.updateParking(carId, true, amountPaid);
            repositoryPlacesAvailable.increasePlacesAvailable();
            repositoryCar.addDateOut(carId);
            return true;
        } else {
            throw new ParkingWasNotPaidException("The amount is not paid!");
        }
    }
}
