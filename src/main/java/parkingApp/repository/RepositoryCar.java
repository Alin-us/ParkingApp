package parkingApp.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import parkingApp.model.Car;



public class RepositoryCar {
    private Session session;
    private Transaction transaction;

    public Integer addCar() {//returneaza primary key ul respectiv id ul de indentificare al masinii
        openSession();
        Integer id = (Integer)session.save(new Car(System.currentTimeMillis()));
        closeSession();
        return id;
    }

    public void deleteCarById(int carId) {
        Car car = findCarById(carId);
        openSession();
        session.delete(car);
        closeSession();
    }

    public Car findCarById(int carId) {
        openSession();
        Car car = session.find(Car.class, carId);
        closeSession();
        return car;
    }
    public void  addDateOut(int carId){
        Car car=findCarById(carId);
        car.setDateOut(System.currentTimeMillis());
        openSession();
        session.update(car);
        closeSession();
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
