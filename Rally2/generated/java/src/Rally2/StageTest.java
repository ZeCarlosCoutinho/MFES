package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;
import junit.framework.TestCase;

@SuppressWarnings("all")
public class StageTest extends TestCase {
	public StageTest(String fnName) {
        super(fnName);
    }
	
  public void testStage() {

    Date date1 = new Date(2017L, 5L, 18L, 9L, 0L, 0L);
    Date date2 = new Date(2017L, 5L, 21L, 18L, 0L, 0L);
    Date date3 = new Date(2017L, 5L, 18L, 12L, 10L, 0L);
    SpecialStage event = new SpecialStage("Vodafone Rally de Portugal", "Portugal", date1, date2);
    Stage stage = new Stage("SS1 LOUSADA", 3360L, 1L, date3, event);
    Boolean andResult_70 = false;

    if (Utils.equals(stage.name, "SS1 LOUSADA")) {
      Boolean andResult_71 = false;

      if (Utils.equals(stage.distance, 3360L)) {
        Boolean andResult_72 = false;

        if (Utils.equals(stage.date, date3)) {
          if (Utils.equals(stage.event, event)) {
            andResult_72 = true;
          }
        }

        if (andResult_72) {
          andResult_71 = true;
        }
      }

      if (andResult_71) {
        andResult_70 = true;
      }
    }

    assertTrue(andResult_70);
  }

  public void testAddStageResult() {

    SpecialStage event =
        new SpecialStage("Vodafone Rally de Portugal", "Portugal", new Date(), new Date());
    Stage stage = new Stage("SS1 LOUSADA", 3360L, 1L, new Date(), event);
    Team team1 = new Team("M-Sport", new Manager());
    Team team2 = new Team("TOYOTA GAZOO RACING WRT", new Manager());
    Car car1 = new Car("Ford", "Fiesta WRC", 2017L, 380L, "4WD", 1190L, team1, 1L);
    Car car2 = new Car("Ford", "Fiesta WRC", 2017L, 380L, "4WD", 1190L, team1, 2L);
    Car car3 = new Car("Toyota", "Yaris WRC", 2017L, 380L, "4WD", 1190L, team2, 10L);
    Car car4 = new Car("Toyota", "Yaris WRC", 2017L, 380L, "4WD", 1190L, team2, 11L);
    event.addTeam(team1);
    event.addTeam(team2);
    stage.addStageResult(car1, 500L);
    stage.addStageResult(car2, 526L);
    stage.addStageResult(car3, 517L);
    stage.addStageResult(car4, 498L);
    Boolean andResult_73 = false;

    if (Utils.equals(((StageResult) Utils.get(stage.stage_results, 1L)).car, car1)) {
      if (Utils.equals(((StageResult) Utils.get(stage.stage_results, 1L)).stage_time, 500L)) {
        andResult_73 = true;
      }
    }

    assertTrue(andResult_73);

    Boolean andResult_74 = false;

    if (Utils.equals(((StageResult) Utils.get(stage.stage_results, 2L)).car, car2)) {
      if (Utils.equals(((StageResult) Utils.get(stage.stage_results, 2L)).stage_time, 526L)) {
        andResult_74 = true;
      }
    }

    assertTrue(andResult_74);

    Boolean andResult_75 = false;

    if (Utils.equals(((StageResult) Utils.get(stage.stage_results, 3L)).car, car3)) {
      if (Utils.equals(((StageResult) Utils.get(stage.stage_results, 3L)).stage_time, 517L)) {
        andResult_75 = true;
      }
    }

    assertTrue(andResult_75);

    Boolean andResult_76 = false;

    if (Utils.equals(((StageResult) Utils.get(stage.stage_results, 4L)).car, car4)) {
      if (Utils.equals(((StageResult) Utils.get(stage.stage_results, 4L)).stage_time, 498L)) {
        andResult_76 = true;
      }
    }

    assertTrue(andResult_76);
  }

  public void testSortStageResults() {

    SpecialStage event =
        new SpecialStage("Vodafone Rally de Portugal", "Portugal", new Date(), new Date());
    Stage stage = new Stage("SS1 LOUSADA", 3360L, 1L, new Date(), event);
    Team team1 = new Team("M-Sport", new Manager());
    Team team2 = new Team("TOYOTA GAZOO RACING WRT", new Manager());
    Car car1 = new Car("Ford", "Fiesta WRC", 2017L, 380L, "4WD", 1190L, team1, 1L);
    Car car2 = new Car("Ford", "Fiesta WRC", 2017L, 380L, "4WD", 1190L, team1, 2L);
    Car car3 = new Car("Toyota", "Yaris WRC", 2017L, 380L, "4WD", 1190L, team2, 10L);
    Car car4 = new Car("Toyota", "Yaris WRC", 2017L, 380L, "4WD", 1190L, team2, 11L);
    event.addTeam(team1);
    event.addTeam(team2);
    stage.addStageResult(car1, 500L);
    stage.addStageResult(car2, 526L);
    stage.addStageResult(car3, 517L);
    stage.addStageResult(car4, 498L);
    stage.sortResults();
    Boolean andResult_77 = false;

    if (Utils.equals(((StageResult) Utils.get(stage.stage_results, 1L)).car, car4)) {
      if (Utils.equals(((StageResult) Utils.get(stage.stage_results, 1L)).stage_time, 498L)) {
        andResult_77 = true;
      }
    }

    assertTrue(andResult_77);

    Boolean andResult_78 = false;

    if (Utils.equals(((StageResult) Utils.get(stage.stage_results, 2L)).car, car1)) {
      if (Utils.equals(((StageResult) Utils.get(stage.stage_results, 2L)).stage_time, 500L)) {
        andResult_78 = true;
      }
    }

    assertTrue(andResult_78);

    Boolean andResult_79 = false;

    if (Utils.equals(((StageResult) Utils.get(stage.stage_results, 3L)).car, car3)) {
      if (Utils.equals(((StageResult) Utils.get(stage.stage_results, 3L)).stage_time, 517L)) {
        andResult_79 = true;
      }
    }

    assertTrue(andResult_79);

    Boolean andResult_80 = false;

    if (Utils.equals(((StageResult) Utils.get(stage.stage_results, 4L)).car, car2)) {
      if (Utils.equals(((StageResult) Utils.get(stage.stage_results, 4L)).stage_time, 526L)) {
        andResult_80 = true;
      }
    }

    assertTrue(andResult_80);
  }

  public void testGetStageResult() {

    SpecialStage event =
        new SpecialStage("Vodafone Rally de Portugal", "Portugal", new Date(), new Date());
    Stage stage = new Stage("SS1 LOUSADA", 3360L, 1L, new Date(), event);
    Team team1 = new Team("M-Sport", new Manager());
    Team team2 = new Team("TOYOTA GAZOO RACING WRT", new Manager());
    Car car1 = new Car("Ford", "Fiesta WRC", 2017L, 380L, "4WD", 1190L, team1, 1L);
    Car car2 = new Car("Ford", "Fiesta WRC", 2017L, 380L, "4WD", 1190L, team1, 2L);
    Car car3 = new Car("Toyota", "Yaris WRC", 2017L, 380L, "4WD", 1190L, team2, 10L);
    Car car4 = new Car("Toyota", "Yaris WRC", 2017L, 380L, "4WD", 1190L, team2, 11L);
    event.addTeam(team1);
    event.addTeam(team2);
    stage.addStageResult(car1, 500L);
    stage.addStageResult(car2, 526L);
    stage.addStageResult(car3, 517L);
    stage.addStageResult(car4, 498L);
    assertTrue(Utils.equals(stage.getStageResult(1L).stage_time, 500L));
    assertTrue(Utils.equals(stage.getStageResult(2L).stage_time, 526L));
    assertTrue(Utils.equals(stage.getStageResult(10L).stage_time, 517L));
    assertTrue(Utils.equals(stage.getStageResult(11L).stage_time, 498L));
    assertTrue(Utils.equals(stage.getStageResult(15L), null));
  }

  public StageTest() {}

  public String toString() {

    return "StageTest{}";
  }
}
