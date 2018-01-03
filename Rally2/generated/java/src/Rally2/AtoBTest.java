package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;
import junit.framework.TestCase;

@SuppressWarnings("all")
public class AtoBTest extends TestCase {
	public AtoBTest(String fnName) {
        super(fnName);
    }
	
  public void testAddRound() {

    Date date = new Date(2017L, 12L, 28L, 19L, 30L, 0L);
    Date end_date = new Date(2017L, 12L, 31L, 20L, 0L, 0L);
    Manager manager = new Manager("Ezio Auditore", "Italian");
    Team team = new Team("The Creed", manager);
    Car car = new Car("Ford", "Fiesta", 2002L, 500L, "4wd", 1000L, team, 1L);
    Number round_time = 6600L;
    AtoB atob = new AtoB("Super Mario Mountain Climb", "Italy", date, end_date);
    Round round = new Round(round_time, date, car);
    atob.addRound(car, round_time, date);
    assertTrue(round.isequal(((Round) Utils.get(atob.round, 1L))));
  }

  public void testGetCarRounds() {

    Date date1 = new Date(2017L, 12L, 28L, 19L, 30L, 0L);
    Date date2 = new Date(2017L, 12L, 28L, 20L, 30L, 0L);
    Date end_date = new Date(2017L, 12L, 31L, 20L, 0L, 0L);
    Manager manager = new Manager("Ezio Auditore", "Italian");
    Team team = new Team("The Creed", manager);
    Car car1 = new Car("Ford", "Fiesta", 2002L, 500L, "4wd", 1000L, team, 1L);
    Car car2 = new Car("Citroen", "Xsara", 2000L, 450L, "4wd", 1450L, team, 1L);
    Number round_time1 = 6600L;
    Number round_time2 = 7000L;
    Number round_time3 = 6650L;
    AtoB atob = new AtoB("Super Mario Mountain Climb", "Italy", date1, end_date);
    VDMSeq result = null;
    atob.addRound(car1, round_time1, date1);
    atob.addRound(car2, round_time2, date1);
    atob.addRound(car1, round_time3, date2);
    result = atob.getCarRounds(car1);
    assertTrue(Utils.equals(result.size(), 2L));
    assertTrue(((Round) Utils.get(result, 1L)).isequal(((Round) Utils.get(atob.round, 1L))));
    assertTrue(((Round) Utils.get(result, 2L)).isequal(((Round) Utils.get(atob.round, 3L))));
  }

  public void testSortRounds() {

    Date date1 = new Date(2017L, 12L, 28L, 19L, 30L, 0L);
    Date date2 = new Date(2017L, 12L, 28L, 20L, 30L, 0L);
    Date end_date = new Date(2017L, 12L, 31L, 20L, 0L, 0L);
    Manager manager = new Manager("Ezio Auditore", "Italian");
    Team team = new Team("The Creed", manager);
    Car car1 = new Car("Ford", "Fiesta", 2002L, 500L, "4wd", 1000L, team, 1L);
    Car car2 = new Car("Citroen", "Xsara", 2000L, 450L, "4wd", 1450L, team, 1L);
    Number round_time1 = 6600L;
    Number round_time2 = 7000L;
    Number round_time3 = 6650L;
    Number round_time4 = 5505L;
    AtoB atob = new AtoB("Super Mario Mountain Climb", "Italy", date1, end_date);
    Round round1 = new Round(round_time1, date1, car1);
    Round round2 = new Round(round_time2, date1, car2);
    Round round3 = new Round(round_time3, date2, car1);
    Round round4 = new Round(round_time4, date2, car2);
    atob.addRound(car1, round_time1, date1);
    atob.addRound(car2, round_time2, date1);
    atob.addRound(car1, round_time3, date2);
    atob.addRound(car2, round_time4, date2);
    atob.sortRounds();
    assertTrue(Utils.equals(atob.round.size(), 4L));
    assertTrue(round1.isequal(((Round) Utils.get(atob.round, 2L))));
    assertTrue(round2.isequal(((Round) Utils.get(atob.round, 4L))));
    assertTrue(round3.isequal(((Round) Utils.get(atob.round, 3L))));
    assertTrue(round4.isequal(((Round) Utils.get(atob.round, 1L))));
  }

  public void testGetFinalRounds() {

    Date date1 = new Date(2017L, 12L, 28L, 19L, 30L, 0L);
    Date end_date = new Date(2017L, 12L, 31L, 20L, 0L, 0L);
    Manager manager1 = new Manager("Ezio Auditore", "Italian");
    Manager manager2 = new Manager("Il Padrino", "Italian");
    Team team1 = new Team("The Creed", manager1);
    Team team2 = new Team("The Templars", manager2);
    Car car1 = new Car("Ford", "Fiesta", 2002L, 500L, "4wd", 1000L, team1, 1L);
    Car car2 = new Car("Citroen", "Xsara", 2000L, 450L, "4wd", 1450L, team1, 2L);
    Car car3 = new Car("Fiat", "Punto", 1999L, 300L, "2wd", 900L, team2, 3L);
    Number round_time1 = 6600L;
    Number round_time2 = 7000L;
    Number round_time3 = 6650L;
    Number round_time4 = 5505L;
    Number round_time5 = 4500L;
    Number round_time6 = 6690L;
    AtoB atob = new AtoB("Super Mario Mountain Climb", "Italy", date1, end_date);
    Round round1 = new Round(round_time1, date1, car1);
    Round round2 = new Round(round_time2, date1, car2);
    Round round3 = new Round(round_time3, date1, car3);
    Round round4 = new Round(round_time4, date1, car1);
    Round round5 = new Round(round_time5, date1, car2);
    Round round6 = new Round(round_time6, date1, car3);
    VDMSeq results = null;
    atob.round = SeqUtil.seq(round1, round2, round3, round4, round5, round6);
    results = atob.getFinalResults();
    assertTrue(Utils.equals(results.size(), 3L));
    assertTrue(Utils.equals(((Round) Utils.get(results, 1L)), round5));
    assertTrue(Utils.equals(((Round) Utils.get(results, 2L)), round4));
    assertTrue(Utils.equals(((Round) Utils.get(results, 3L)), round3));
  }

  public void testGetRound() {

    Date date1 = new Date(2017L, 12L, 28L, 19L, 30L, 0L);
    Date date2 = new Date(2017L, 12L, 28L, 19L, 35L, 0L);
    Date date3 = new Date(2017L, 12L, 28L, 19L, 40L, 0L);
    Date date4 = new Date(2017L, 12L, 28L, 19L, 45L, 0L);
    Date date5 = new Date(2017L, 12L, 28L, 19L, 50L, 0L);
    Date date6 = new Date(2017L, 12L, 28L, 19L, 55L, 0L);
    Date end_date = new Date(2017L, 12L, 31L, 20L, 0L, 0L);
    Manager manager1 = new Manager("Ezio Auditore", "Italian");
    Manager manager2 = new Manager("Il Padrino", "Italian");
    Team team1 = new Team("The Creed", manager1);
    Team team2 = new Team("The Templars", manager2);
    Car car1 = new Car("Ford", "Fiesta", 2002L, 500L, "4wd", 1000L, team1, 1L);
    Car car2 = new Car("Citroen", "Xsara", 2000L, 450L, "4wd", 1450L, team1, 2L);
    Car car3 = new Car("Fiat", "Punto", 1999L, 300L, "2wd", 900L, team2, 3L);
    Number round_time1 = 6600L;
    Number round_time2 = 7000L;
    Number round_time4 = 5505L;
    Number round_time5 = 4500L;
    AtoB atob = new AtoB("Super Mario Mountain Climb", "Italy", date1, end_date);
    Round round1 = new Round(round_time1, date1, car1);
    Round round2 = new Round(round_time2, date2, car2);
    Round round4 = new Round(round_time4, date4, car1);
    Round round5 = new Round(round_time5, date5, car2);
    atob.round = SeqUtil.seq(round1, round2, round4, round5);
    assertTrue(Utils.equals(atob.getRound(car1, date1), round1));
    assertTrue(Utils.equals(atob.getRound(car2, date2), round2));
    assertTrue(Utils.equals(atob.getRound(car1, date4), round4));
    assertTrue(Utils.equals(atob.getRound(car2, date5), round5));
    assertTrue(Utils.equals(atob.getRound(car3, date3), null));
    assertTrue(Utils.equals(atob.getRound(car3, date6), null));
  }

  public AtoBTest() {}

  public String toString() {

    return "AtoBTest{}";
  }
}
