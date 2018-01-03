package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;
import junit.framework.TestCase;

@SuppressWarnings("all")
public class SpecialStageTest extends TestCase {
	public SpecialStageTest(String fnName) {
        super(fnName);
    }
	
  public void testGetStage() {

    Manager manager = new Manager("Malcom Wilson", "British");
    Manager manager2 = new Manager("Tommi Mäkinen", "Finish");
    Team team = new Team("M-Sport", manager);
    Team team2 = new Team("TOYOTA GAZOO RACING WRT", manager2);
    SpecialStage event =
        new SpecialStage("Vodafone Rally de Portugal", "Portugal", new Date(), new Date());
    Stage stage1 = new Stage("SS1 LOUSADA", 3360L, 1L, new Date(), event);
    Stage stage2 = new Stage("SS2 VIANA DO CASTELO 1", 26700L, 2L, new Date(), event);
    Stage stage3 = new Stage("SS3 CAMINHA 1", 18100L, 3L, new Date(), event);
    Car car1 = new Car("Ford", "Fiesta WRC", 2017L, 380L, "4WD", 1190L, team, 1L);
    Car car2 = new Car("Ford", "Fiesta WRC", 2017L, 380L, "4WD", 1190L, team, 2L);
    Car car3 = new Car("Toyota", "Yaris WRC", 2017L, 380L, "4WD", 1190L, team2, 10L);
    Car car4 = new Car("Toyota", "Yaris WRC", 2017L, 380L, "4WD", 1190L, team2, 11L);
    event.stages = SetUtil.set(stage1, stage2, stage3);
    team.cars = SetUtil.set(car1, car2);
    team2.cars = SetUtil.set(car3, car4);
    event.teams = SetUtil.set(team, team2);
    team.events = SetUtil.set(event);
    team2.events = SetUtil.set(event);
    Boolean andResult_63 = false;

    if (Utils.equals(event.getStage(1L).number, 1L)) {
      Boolean andResult_64 = false;

      if (Utils.equals(event.getStage(1L).distance, 3360L)) {
        if (Utils.equals(event.getStage(1L).name, "SS1 LOUSADA")) {
          andResult_64 = true;
        }
      }

      if (andResult_64) {
        andResult_63 = true;
      }
    }

    assertTrue(andResult_63);

    Boolean andResult_65 = false;

    if (Utils.equals(event.getStage(2L).number, 2L)) {
      Boolean andResult_66 = false;

      if (Utils.equals(event.getStage(2L).distance, 26700L)) {
        if (Utils.equals(event.getStage(2L).name, "SS2 VIANA DO CASTELO 1")) {
          andResult_66 = true;
        }
      }

      if (andResult_66) {
        andResult_65 = true;
      }
    }

    assertTrue(andResult_65);

    Boolean andResult_67 = false;

    if (Utils.equals(event.getStage(3L).number, 3L)) {
      Boolean andResult_68 = false;

      if (Utils.equals(event.getStage(3L).distance, 18100L)) {
        if (Utils.equals(event.getStage(3L).name, "SS3 CAMINHA 1")) {
          andResult_68 = true;
        }
      }

      if (andResult_68) {
        andResult_67 = true;
      }
    }

    assertTrue(andResult_67);

    assertTrue(Utils.equals(event.getStage(10L), null));
  }

  public void testStageSort() {

    Manager manager = new Manager("Malcom Wilson", "British");
    Manager manager2 = new Manager("Tommi Mäkinen", "Finish");
    Team team = new Team("M-Sport", manager);
    Team team2 = new Team("TOYOTA GAZOO RACING WRT", manager2);
    SpecialStage event =
        new SpecialStage("Vodafone Rally de Portugal", "Portugal", new Date(), new Date());
    Stage stage1 = new Stage("SS1 LOUSADA", 3360L, 1L, new Date(), event);
    Stage stage2 = new Stage("SS2 VIANA DO CASTELO 1", 26700L, 2L, new Date(), event);
    Stage stage3 = new Stage("SS3 CAMINHA 1", 18100L, 3L, new Date(), event);
    Car car1 = new Car("Ford", "Fiesta WRC", 2017L, 380L, "4WD", 1190L, team, 1L);
    Car car2 = new Car("Ford", "Fiesta WRC", 2017L, 380L, "4WD", 1190L, team, 2L);
    Car car3 = new Car("Toyota", "Yaris WRC", 2017L, 380L, "4WD", 1190L, team2, 10L);
    Car car4 = new Car("Toyota", "Yaris WRC", 2017L, 380L, "4WD", 1190L, team2, 11L);
    StageResult stageResult1 = new StageResult(car1, 157300L);
    StageResult stageResult2 = new StageResult(car2, 147200L);
    StageResult stageResult3 = new StageResult(car3, 145800L);
    StageResult stageResult4 = new StageResult(car4, 115400L);
    StageResult stageResult5 = new StageResult(car1, 939922L);
    StageResult stageResult6 = new StageResult(car2, 929227L);
    StageResult stageResult7 = new StageResult(car3, 941544L);
    StageResult stageResult8 = new StageResult(car4, 940578L);
    StageResult stageResult9 = new StageResult(car1, 643099L);
    StageResult stageResult10 = new StageResult(car2, 645199L);
    StageResult stageResult11 = new StageResult(car3, 658434L);
    StageResult stageResult12 = new StageResult(car4, 637224L);
    event.stages = SetUtil.set(stage1, stage2, stage3);
    team.cars = SetUtil.set(car1, car2);
    team2.cars = SetUtil.set(car3, car4);
    event.teams = SetUtil.set(team, team2);
    team.events = SetUtil.set(event);
    team2.events = SetUtil.set(event);
    stage1.stage_results = SeqUtil.seq(stageResult1, stageResult2, stageResult3, stageResult4);
    stage2.stage_results = SeqUtil.seq(stageResult5, stageResult6, stageResult7, stageResult8);
    stage3.stage_results = SeqUtil.seq(stageResult9, stageResult10, stageResult11, stageResult12);
    for (Iterator iterator_11 = event.stages.iterator(); iterator_11.hasNext(); ) {
      Stage stage = (Stage) iterator_11.next();
      stage.sortResults();
      long toVar_7 = stage.stage_results.size() - 1L;

      for (Long sr = 1L; sr <= toVar_7; sr++) {
        assertTrue(
            ((StageResult) Utils.get(stage.stage_results, sr)).stage_time.longValue()
                < ((StageResult) Utils.get(stage.stage_results, sr.longValue() + 1L))
                    .stage_time.longValue());
      }
    }
  }

  public void testGetResults() {

    Manager manager = new Manager("Malcom Wilson", "British");
    Manager manager2 = new Manager("Tommi Mäkinen", "Finish");
    Team team = new Team("M-Sport", manager);
    Team team2 = new Team("TOYOTA GAZOO RACING WRT", manager2);
    SpecialStage event =
        new SpecialStage("Vodafone Rally de Portugal", "Portugal", new Date(), new Date());
    VDMSeq results = null;
    VDMSeq results_answers_times = SeqUtil.seq(1693202L, 1721626L, 1740321L, 1745778L);
    VDMSeq results_answers_pos = SeqUtil.seq(11L, 2L, 1L, 10L);
    Stage stage1 = new Stage("SS1 LOUSADA", 3360L, 1L, new Date(), event);
    Stage stage2 = new Stage("SS2 VIANA DO CASTELO 1", 26700L, 2L, new Date(), event);
    Stage stage3 = new Stage("SS3 CAMINHA 1", 18100L, 3L, new Date(), event);
    Car car1 = new Car("Ford", "Fiesta WRC", 2017L, 380L, "4WD", 1190L, team, 1L);
    Car car2 = new Car("Ford", "Fiesta WRC", 2017L, 380L, "4WD", 1190L, team, 2L);
    Car car3 = new Car("Toyota", "Yaris WRC", 2017L, 380L, "4WD", 1190L, team2, 10L);
    Car car4 = new Car("Toyota", "Yaris WRC", 2017L, 380L, "4WD", 1190L, team2, 11L);
    StageResult stageResult1 = new StageResult(car1, 157300L);
    StageResult stageResult2 = new StageResult(car2, 147200L);
    StageResult stageResult3 = new StageResult(car3, 145800L);
    StageResult stageResult4 = new StageResult(car4, 115400L);
    StageResult stageResult5 = new StageResult(car1, 939922L);
    StageResult stageResult6 = new StageResult(car2, 929227L);
    StageResult stageResult7 = new StageResult(car3, 941544L);
    StageResult stageResult8 = new StageResult(car4, 940578L);
    StageResult stageResult9 = new StageResult(car1, 643099L);
    StageResult stageResult10 = new StageResult(car2, 645199L);
    StageResult stageResult11 = new StageResult(car3, 658434L);
    StageResult stageResult12 = new StageResult(car4, 637224L);
    event.stages = SetUtil.set(stage1, stage2, stage3);
    team.cars = SetUtil.set(car1, car2);
    team2.cars = SetUtil.set(car3, car4);
    event.teams = SetUtil.set(team, team2);
    team.events = SetUtil.set(event);
    team2.events = SetUtil.set(event);
    stage1.stage_results = SeqUtil.seq(stageResult1, stageResult2, stageResult3, stageResult4);
    stage2.stage_results = SeqUtil.seq(stageResult5, stageResult6, stageResult7, stageResult8);
    stage3.stage_results = SeqUtil.seq(stageResult9, stageResult10, stageResult11, stageResult12);
    results = event.getResults().stage_results;
    long toVar_8 = results.size();

    for (Long n = 1L; n <= toVar_8; n++) {
      assertTrue(
          Utils.equals(
              ((StageResult) Utils.get(results, n)).car.number,
              ((Number) Utils.get(results_answers_pos, n))));
      assertTrue(
          Utils.equals(
              ((StageResult) Utils.get(results, n)).stage_time,
              ((Number) Utils.get(results_answers_times, n))));
    }
  }

  public void testAddStage() {

    SpecialStage event =
        new SpecialStage("Vodafone Rally de Portugal", "Portugal", new Date(), new Date());
    String name1 = "SS1 LOUSADA";
    String name2 = "SS2 VIANA DO CASTELO 1";
    Number distance1 = 3360L;
    Number distance2 = 26700L;
    Stage stage1 = new Stage(name1, distance1, 1L, new Date(), event);
    Stage stage2 = new Stage(name2, distance2, 2L, new Date(), event);
    event.addStage(name1, 1L, distance1, new Date());
    assertTrue(Utils.equals(event.stages.size(), 1L));
    event.addStage(name2, 2L, distance2, new Date());
    assertTrue(Utils.equals(event.stages.size(), 2L));
  }

  public SpecialStageTest() {}

  public String toString() {

    return "SpecialStageTest{}";
  }
}
