package parkingApp.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import parkingApp.model.Car;
import parkingApp.model.Parking;

import java.util.List;

public class RepositoryParking {
    private Session session;
    private Transaction transaction;


    public int addCarIntoParking(Parking parking) {
        openSession();
        Integer id = (Integer)session.save(parking);
        closeSession();
        return id;
    }

    public void deleteCarById(int carId) {
        openSession();
        session.delete(carId);
        closeSession();
    }

    public List<Parking> findAll() {
        openSession();
        Query q = session.createQuery("from Parking");
        List<Parking> parkingList = q.getResultList();
        closeSession();
        return parkingList;
    }

    public void updateParking(int carId, boolean isPaid, double amountPaid) {
        Parking parking = findCarById(carId);
        parking.setPaid(isPaid);
        parking.setPaymentAmount(amountPaid);
        openSession();
        session.update(parking);
        closeSession();
    }

    public Parking findCarById(int carId) {
        openSession();
        Query q = session.createQuery("from Parking  where carId=" + carId);
        Parking parking = (Parking) q.getSingleResult();
        closeSession();
        return parking;
    }
    public boolean fiindIfIsPaid(int carId){
        openSession();
        Query q=session.createQuery("from Parking where carId="+carId);
        Parking parking=(Parking)q.getSingleResult();
        return parking.isPaid();
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
