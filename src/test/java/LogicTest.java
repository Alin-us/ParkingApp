import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import parkingApp.exceptions.NoParkingSpaceAvailableException;
import parkingApp.exceptions.ParkingWasNotPaidException;
import parkingApp.logic.Logic;
import parkingApp.model.Car;
import parkingApp.model.Parking;
import parkingApp.repository.RepositoryCar;
import parkingApp.repository.RepositoryParking;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)

public class LogicTest {
    RepositoryCar repositoryCar = mock(RepositoryCar.class);
    RepositoryParking repositoryParking = mock(RepositoryParking.class);
    @InjectMocks
    Logic logic = new Logic();

    @Test(expected = NoParkingSpaceAvailableException.class)//test parcare plina
    public void testEnterIntoParking() throws NoParkingSpaceAvailableException {
        Parking parking = new Parking();
        RepositoryCar repositoryCar = new RepositoryCar();
        RepositoryParking repositoryParking = new RepositoryParking();
        Logic logic = new Logic();
//        logic.setPlacesAvailable(0);
        logic.enterIntoParking();
    }

    @Test//verifica id ul
    public void idTestEnterIntoParking() throws NoParkingSpaceAvailableException {
        when(repositoryCar.addCar()).thenReturn(1);
        //      when(repositoryCar.findCarById(any())).thenReturn(new Car());
        when(repositoryParking.addCarIntoParking(any())).thenReturn(1);
        int id = logic.enterIntoParking();//asta astioneaza asupra bazai de date adica modofica in ea ceva sau doar testeaza?
        Assert.assertEquals("TestReusit", 7, id);
    }
    //o metoda care sa fie id= cel mai mare numar de pe coloana cu indexul din baza de date??

    @Test(expected = ParkingWasNotPaidException.class)//nu a platit destul
    public void testGetOutOfParking() throws ParkingWasNotPaidException {
        Parking parking = new Parking();
        RepositoryCar repositoryCar = new RepositoryCar();
        RepositoryParking repositoryParking = new RepositoryParking();
        Logic logic = new Logic();
        logic.getOutOfParking(12, 10, 1);
    }

}
