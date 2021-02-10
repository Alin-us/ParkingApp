package parkingApp.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import parkingApp.model.Parking;
import parkingApp.model.PlacesAvailable;

public class RepositoryPlacesAvailable {
    Session session;
    Transaction transaction;

    public void addPlacesAvailable(PlacesAvailable placesAvailable) {
        openSession();
        session.save(placesAvailable);
        closeSession();

    }

    public void decreasePlacesAvailable() {
        PlacesAvailable placesAvailable=fiindById();
        placesAvailable.setPlacesAvailable(placesAvailable.getPlacesAvailable()-1);
        openSession();
        session.update(placesAvailable);
        closeSession();
    }
    public  void increasePlacesAvailable(){
        PlacesAvailable placesAvailable=fiindById();
        placesAvailable.setPlacesAvailable(placesAvailable.getPlacesAvailable()+1);
        openSession();
        session.update(placesAvailable);
        closeSession();
    }
    public PlacesAvailable fiindById(){
        openSession();
        PlacesAvailable placesAvailable=session.find(PlacesAvailable.class,1);
        closeSession();
        return placesAvailable;
    }

    public void openSession() {
        session = HibernateUtils.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    public void closeSession() {
        transaction.commit();
        session.close();
    }
}
