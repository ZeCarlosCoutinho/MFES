package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;
import junit.framework.TestCase;

@SuppressWarnings("all")
public class RoundTest extends TestCase {
	public RoundTest(String fnName) {
        super(fnName);
    }
	
  public void testRoundCreation() {

    Manager manager = new Manager("Ezio Auditore", "Italian");
    Team team = new Team("The Creed", manager);
    Car car = new Car("Ford", "Fiesta", 2002L, 500L, "4wd", 1000L, team, 1L);
    Date date = new Date(2017L, 12L, 28L, 19L, 30L, 0L);
    Number round_time = 6600L;
    Round round = new Round(round_time, date, car);
    assertTrue(Utils.equals(round.car, car));
    assertTrue(Utils.equals(round.date, date));
    assertTrue(Utils.equals(round.round_time, round_time));
  }

  public void testIsEqual() {

    Manager manager1 = new Manager("Ezio Auditore", "Italian");
    Manager manager2 = new Manager("Il Padrino", "Italian");
    Team team1 = new Team("The Creed", manager1);
    Team team2 = new Team("The Templars", manager2);
    Car car1 = new Car("Ford", "Fiesta", 2002L, 500L, "4wd", 1000L, team1, 1L);
    Car car2 = new Car("Fiat", "Punto", 1999L, 300L, "2wd", 900L, team2, 3L);
    Date date = new Date(2017L, 12L, 28L, 19L, 30L, 0L);
    Number round_time1 = 6600L;
    Number round_time2 = 5000L;
    Round round1 = new Round(round_time1, date, car1);
    Round round2 = new Round(round_time1, date, car1);
    Round round3 = new Round(round_time2, date, car1);
    Round round4 = new Round(round_time1, date, car2);
    assertTrue(round1.isequal(round2));
    assertFalse(round1.isequal(round3));
    assertFalse(round1.isequal(round4));
  }

  public RoundTest() {}

  public String toString() {

    return "RoundTest{}";
  }
}
