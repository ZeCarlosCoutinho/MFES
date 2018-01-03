package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;
import junit.framework.TestCase;

@SuppressWarnings("all")
public class CarTest extends TestCase {
	public CarTest(String fnName) {
        super(fnName);
    }
	
  public void testSetDriver() {

    Driver driver1 = new Driver("Leonardo DiCaprio", "American");
    Driver driver2 = new Driver("Il Padrino", "Italian");
    Manager manager = new Manager("Ezio Auditore", "Italian");
    Team team = new Team("The Creed", manager);
    Car car = new Car("Ford", "Fiesta", 2002L, 500L, "4wd", 1000L, team, 1L);
    car.setDriver(driver1);
    assertTrue(Utils.equals(car.driver, driver1));
    car.setDriver(driver2);
    assertTrue(Utils.equals(car.driver, driver2));
  }

  public void testSetCoDriver() {

    Manager manager = new Manager("Ezio Auditore", "Italian");
    Team team = new Team("The Creed", manager);
    Car car = new Car("Ford", "Fiesta", 2002L, 500L, "4wd", 1000L, team, 1L);
    CoDriver codriver1 = new CoDriver("Il Padrino", "Italian");
    CoDriver codriver2 = new CoDriver("Leonardo DiCaprio", "American");
    car.setCoDriver(codriver1);
    assertTrue(Utils.equals(car.co_driver, codriver1));
    car.setCoDriver(codriver2);
    assertTrue(Utils.equals(car.co_driver, codriver2));
  }

  public void testAddMechanic() {

    Manager manager = new Manager("Ezio Auditore", "Italian");
    Team team = new Team("The Creed", manager);
    Car car = new Car("Ford", "Fiesta", 2002L, 500L, "4wd", 1000L, team, 1L);
    Mechanic mechanic1 = new Mechanic("Il Padrino", "Italian");
    Mechanic mechanic2 = new Mechanic("Leonardo DiCaprio", "American");
    assertTrue(Utils.equals(car.mechanics.size(), 0L));
    car.addMechanic(mechanic1);
    assertTrue(Utils.equals(car.mechanics.size(), 1L));
    assertTrue(SetUtil.inSet(mechanic1, car.mechanics));
    car.addMechanic(mechanic2);
    assertTrue(Utils.equals(car.mechanics.size(), 2L));
    Boolean andResult_69 = false;

    if (SetUtil.inSet(mechanic1, car.mechanics)) {
      if (SetUtil.inSet(mechanic2, car.mechanics)) {
        andResult_69 = true;
      }
    }

    assertTrue(andResult_69);
  }

  public CarTest() {}

  public String toString() {

    return "CarTest{}";
  }
}
