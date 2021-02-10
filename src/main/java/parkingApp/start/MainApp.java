package parkingApp.start;

import org.hibernate.Session;
import org.hibernate.Transaction;
import parkingApp.exceptions.NoParkingSpaceAvailableException;
import parkingApp.exceptions.ParkingWasNotPaidException;
import parkingApp.logic.Logic;
import parkingApp.model.Car;
import parkingApp.model.Parking;
import parkingApp.model.PlacesAvailable;
import parkingApp.repository.HibernateUtils;
import parkingApp.repository.RepositoryCar;
import parkingApp.repository.RepositoryParking;
import parkingApp.repository.RepositoryPlacesAvailable;
import sun.rmi.runtime.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class MainApp {
    static RepositoryPlacesAvailable repositoryPlacesAvailable=new RepositoryPlacesAvailable();
    static Logic logic = new Logic();
    public static void main(String[] args) throws NoParkingSpaceAvailableException, ParkingWasNotPaidException {
        //config
      HibernateUtils.getSessionFactory();

//        //INTRARE
//        System.out.println("Locuri libere: " +repositoryPlacesAvailable.fiindById().getPlacesAvailable());
//        int idMasina = logic.enterIntoParking();
//        //eticheta
//        System.out.println("Id masina: " + idMasina);//nr bilet
//        Date data = new Date(System.currentTimeMillis());
//        SimpleDateFormat spl = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
//        System.out.println("Ati intrat la data: " + spl.format(data));//data
//        System.out.println("Locuri libere: " +repositoryPlacesAvailable.fiindById().getPlacesAvailable());

        //IESIRE
        //introduce id ul
        System.out.println("Introduceti va rog nr ticketului: ");
        Scanner scanner = new Scanner(System.in);
        int ticket = scanner.nextInt();
        //ii cauta cat are de platit
        long minute = logic.getMinutes(ticket);
        double sumaDePlatit = logic.amountToPay(minute);
        System.out.println("Aveti de plata:" + sumaDePlatit + " lei");
        System.out.println("Introduceti suma:");
        Scanner scanner1 = new Scanner(System.in).useLocale(Locale.US);
        double sumaPlatita = scanner1.nextDouble();
        System.out.println(logic.getOutOfParking(sumaDePlatit, sumaPlatita, ticket));


    }


}


