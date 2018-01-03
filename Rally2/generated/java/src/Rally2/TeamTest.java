package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;
import junit.framework.TestCase;

@SuppressWarnings("all")
public class TeamTest extends TestCase {
	public TeamTest(String fnName) {
        super(fnName);
    }
	
  public void testTeam() {

    Manager manager = new Manager("Malcom Wilson", "British");
    Team team = new Team("M-Sport", manager);
    Sponsor sponsor_ford = new Sponsor("Ford");
    Sponsor sponsor_castrol = new Sponsor("Castrol EDGE");
    Sponsor sponsor_michelin = new Sponsor("Michelen");
    Sponsor sponsor_onebet = new Sponsor("OneBet");
    Sponsor sponsor_msrt = new Sponsor("MS-RT");
    team.addCar("Ford", "Fiesta WRC", 2017L, 380L, "4WD", 1190L, 1L);
    team.addCar("Ford", "Fiesta WRC", 2017L, 380L, "4WD", 1190L, 2L);
    team.addCar("Ford", "Fiesta WRC", 2017L, 380L, "4WD", 1190L, 3L);
    assertTrue(Utils.equals(team.cars.size(), 3L));
    team.addDriver("Sébastien Ogier", "French");
    team.assignDriver(team.getCar(1L), (Driver) team.getMember("Sébastien Ogier"));
    team.addCoDriver("Julien Ingrassia", "French");
    team.assignCoDriver(team.getCar(1L), (CoDriver) team.getMember("Julien Ingrassia"));
    team.addDriver("Ott Tänak", "Estonian");
    team.assignDriver(team.getCar(2L), (Driver) team.getMember("Ott Tänak"));
    team.addCoDriver("Martin Järveoja", "Estonian");
    team.assignCoDriver(team.getCar(2L), (CoDriver) team.getMember("Martin Järveoja"));
    team.addDriver("Elfyn Evans", "British");
    team.assignDriver(team.getCar(3L), (Driver) team.getMember("Elfyn Evans"));
    team.addCoDriver("Daniel Barritt", "British");
    team.assignCoDriver(team.getCar(3L), (CoDriver) team.getMember("Daniel Barritt"));
    team.addMechanic("William Lawrence", "British");
    team.addMechanic("Reiner Buchwald", "German");
    team.assignMechanic(team.getCar(1L), (Mechanic) team.getMember("William Lawrence"));
    team.assignMechanic(team.getCar(1L), (Mechanic) team.getMember("Reiner Buchwald"));
    team.addMechanic("Roderick Gilbert", "Scottish");
    team.assignMechanic(team.getCar(2L), (Mechanic) team.getMember("Roderick Gilbert"));
    team.addMechanic("Felipe Warner", "Brazilian");
    team.assignMechanic(team.getCar(3L), (Mechanic) team.getMember("Felipe Warner"));
    assertTrue(Utils.equals(team.team_members.size(), 11L));
    assertTrue(Utils.equals(team.getCar(1L).driver.name, "Sébastien Ogier"));
    assertTrue(Utils.equals(team.getCar(1L).co_driver.name, "Julien Ingrassia"));
    assertTrue(Utils.equals(team.getCar(1L).mechanics.size(), 2L));
    assertTrue(
        Utils.equals(
            team.getCar(1L).mechanics,
            SetUtil.set(team.getMember("William Lawrence"), team.getMember("Reiner Buchwald"))));
    team.addSponsor(sponsor_ford);
    team.addSponsor(sponsor_castrol);
    team.addSponsor(sponsor_michelin);
    team.addSponsor(sponsor_onebet);
    team.addSponsor(sponsor_msrt);
    assertTrue(Utils.equals(team.sponsors.size(), 5L));
    assertTrue(
        Utils.equals(
            team.sponsors,
            SetUtil.set(
                sponsor_ford, sponsor_castrol, sponsor_michelin, sponsor_onebet, sponsor_msrt)));
    assertTrue(Utils.equals(team.getCar(100L), null));
    assertTrue(Utils.equals(team.getMember(""), null));
  }

  public void testTeamResults() {

    Manager manager = new Manager("Malcom Wilson", "British");
    Team team = new Team("M-Sport", manager);
    Manager manager2 = new Manager("Tommi Mäkinen", "Finish");
    Team team2 = new Team("TOYOTA GAZOO RACING WRT", manager2);
    SpecialStage event =
        new SpecialStage("Vodafone Rally de Portugal", "Portugal", new Date(), new Date());
    AtoB event2 =
        new AtoB(
            "Pikes Peak International Hill Climb",
            "Pikes Peak, Colorado, USA",
            new Date(),
            new Date());
    VDMSeq team_results = null;
    VDMSeq team2_results = null;
    team.addCar("Ford", "Fiesta WRC", 2017L, 380L, "4WD", 1190L, 1L);
    team.addCar("Ford", "Fiesta WRC", 2017L, 380L, "4WD", 1190L, 2L);
    team2.addCar("Toyota", "Yaris WRC", 2017L, 380L, "4WD", 1190L, 10L);
    team2.addCar("Toyota", "Yaris WRC", 2017L, 380L, "4WD", 1190L, 11L);
    event.addStage("SS1 LOUSADA", 1L, 3360L, new Date());
    event.addStage("SS2 VIANA DO CASTELO 1", 2L, 26700L, new Date());
    event.addStage("SS3 CAMINHA 1", 3L, 18100L, new Date());
    event2.addTeam(team);
    event2.addTeam(team2);
    event.addTeam(team);
    event.addTeam(team2);
    event.getStage(1L).addStageResult(team.getCar(1L), 157300L);
    event.getStage(1L).addStageResult(team.getCar(2L), 147200L);
    event.getStage(1L).addStageResult(team2.getCar(10L), 145800L);
    event.getStage(1L).addStageResult(team2.getCar(11L), 115400L);
    event.getStage(2L).addStageResult(team.getCar(1L), 939922L);
    event.getStage(2L).addStageResult(team.getCar(2L), 929227L);
    event.getStage(2L).addStageResult(team2.getCar(10L), 941544L);
    event.getStage(2L).addStageResult(team2.getCar(11L), 940578L);
    event.getStage(3L).addStageResult(team.getCar(1L), 643099L);
    event.getStage(3L).addStageResult(team.getCar(2L), 645199L);
    event.getStage(3L).addStageResult(team2.getCar(10L), 658434L);
    event.getStage(3L).addStageResult(team2.getCar(11L), 637224L);
    event2.addRound(team.getCar(1L), 546157L, new Date());
    event2.addRound(team.getCar(1L), 546249L, new Date());
    event2.addRound(team.getCar(1L), 548578L, new Date());
    event2.addRound(team.getCar(2L), 547122L, new Date());
    event2.addRound(team.getCar(2L), 557935L, new Date());
    event2.addRound(team.getCar(2L), 548239L, new Date());
    event2.addRound(team2.getCar(10L), 559935L, new Date());
    event2.addRound(team2.getCar(10L), 554679L, new Date());
    event2.addRound(team2.getCar(10L), 562486L, new Date());
    event2.addRound(team2.getCar(11L), 551356L, new Date());
    event2.addRound(team2.getCar(11L), 566036L, new Date());
    event2.addRound(team2.getCar(11L), 541092L, new Date());
    team_results = team.getEventResults();
    team2_results = team2.getEventResults();
    Boolean andResult_30 = false;

    if (Utils.equals(((CarResult) Utils.get(team_results, 1L)).car.number, 1L)) {
      Boolean andResult_31 = false;

      if (Utils.equals(
          ((CarResult) Utils.get(team_results, 1L)).event.name,
          "Pikes Peak International Hill Climb")) {
        if (Utils.equals(((CarResult) Utils.get(team_results, 1L)).position, 2L)) {
          andResult_31 = true;
        }
      }

      if (andResult_31) {
        andResult_30 = true;
      }
    }

    assertTrue(andResult_30);

    Boolean andResult_32 = false;

    if (Utils.equals(((CarResult) Utils.get(team_results, 2L)).car.number, 2L)) {
      Boolean andResult_33 = false;

      if (Utils.equals(
          ((CarResult) Utils.get(team_results, 2L)).event.name,
          "Pikes Peak International Hill Climb")) {
        if (Utils.equals(((CarResult) Utils.get(team_results, 2L)).position, 3L)) {
          andResult_33 = true;
        }
      }

      if (andResult_33) {
        andResult_32 = true;
      }
    }

    assertTrue(andResult_32);

    Boolean andResult_34 = false;

    if (Utils.equals(((CarResult) Utils.get(team_results, 3L)).car.number, 1L)) {
      Boolean andResult_35 = false;

      if (Utils.equals(
          ((CarResult) Utils.get(team_results, 3L)).event.name, "Vodafone Rally de Portugal")) {
        if (Utils.equals(((CarResult) Utils.get(team_results, 3L)).position, 3L)) {
          andResult_35 = true;
        }
      }

      if (andResult_35) {
        andResult_34 = true;
      }
    }

    assertTrue(andResult_34);

    Boolean andResult_36 = false;

    if (Utils.equals(((CarResult) Utils.get(team_results, 4L)).car.number, 2L)) {
      Boolean andResult_37 = false;

      if (Utils.equals(
          ((CarResult) Utils.get(team_results, 4L)).event.name, "Vodafone Rally de Portugal")) {
        if (Utils.equals(((CarResult) Utils.get(team_results, 4L)).position, 2L)) {
          andResult_37 = true;
        }
      }

      if (andResult_37) {
        andResult_36 = true;
      }
    }

    assertTrue(andResult_36);

    Boolean andResult_38 = false;

    if (Utils.equals(((CarResult) Utils.get(team2_results, 1L)).car.number, 10L)) {
      Boolean andResult_39 = false;

      if (Utils.equals(
          ((CarResult) Utils.get(team2_results, 1L)).event.name,
          "Pikes Peak International Hill Climb")) {
        if (Utils.equals(((CarResult) Utils.get(team2_results, 1L)).position, 4L)) {
          andResult_39 = true;
        }
      }

      if (andResult_39) {
        andResult_38 = true;
      }
    }

    assertTrue(andResult_38);

    Boolean andResult_40 = false;

    if (Utils.equals(((CarResult) Utils.get(team2_results, 2L)).car.number, 11L)) {
      Boolean andResult_41 = false;

      if (Utils.equals(
          ((CarResult) Utils.get(team2_results, 2L)).event.name,
          "Pikes Peak International Hill Climb")) {
        if (Utils.equals(((CarResult) Utils.get(team2_results, 2L)).position, 1L)) {
          andResult_41 = true;
        }
      }

      if (andResult_41) {
        andResult_40 = true;
      }
    }

    assertTrue(andResult_40);

    Boolean andResult_42 = false;

    if (Utils.equals(((CarResult) Utils.get(team2_results, 3L)).car.number, 10L)) {
      Boolean andResult_43 = false;

      if (Utils.equals(
          ((CarResult) Utils.get(team2_results, 3L)).event.name, "Vodafone Rally de Portugal")) {
        if (Utils.equals(((CarResult) Utils.get(team2_results, 3L)).position, 4L)) {
          andResult_43 = true;
        }
      }

      if (andResult_43) {
        andResult_42 = true;
      }
    }

    assertTrue(andResult_42);

    Boolean andResult_44 = false;

    if (Utils.equals(((CarResult) Utils.get(team2_results, 4L)).car.number, 11L)) {
      Boolean andResult_45 = false;

      if (Utils.equals(
          ((CarResult) Utils.get(team2_results, 4L)).event.name, "Vodafone Rally de Portugal")) {
        if (Utils.equals(((CarResult) Utils.get(team2_results, 4L)).position, 1L)) {
          andResult_45 = true;
        }
      }

      if (andResult_45) {
        andResult_44 = true;
      }
    }

    assertTrue(andResult_44);
  }

  public void testGetCar() {

    Manager manager = new Manager("Malcom Wilson", "British");
    Team team = new Team("M-Sport", manager);
    team.addCar("Ford", "Fiesta WRC", 2017L, 380L, "4WD", 1190L, 1L);
    team.addCar("Ford", "Fiesta WRC", 2017L, 380L, "4WD", 1190L, 2L);
    Boolean andResult_46 = false;

    if (Utils.equals(team.getCar(2L).make, "Ford")) {
      Boolean andResult_47 = false;

      if (Utils.equals(team.getCar(2L).model, "Fiesta WRC")) {
        Boolean andResult_48 = false;

        if (Utils.equals(team.getCar(2L).year, 2017L)) {
          Boolean andResult_49 = false;

          if (Utils.equals(team.getCar(2L).bhp, 380L)) {
            Boolean andResult_50 = false;

            if (Utils.equals(team.getCar(2L).weight, 1190L)) {
              Boolean andResult_51 = false;

              if (Utils.equals(team.getCar(2L).traction, "4WD")) {
                Boolean andResult_52 = false;

                if (Utils.equals(team.getCar(2L).team, team)) {
                  if (Utils.equals(team.getCar(2L).number, 2L)) {
                    andResult_52 = true;
                  }
                }

                if (andResult_52) {
                  andResult_51 = true;
                }
              }

              if (andResult_51) {
                andResult_50 = true;
              }
            }

            if (andResult_50) {
              andResult_49 = true;
            }
          }

          if (andResult_49) {
            andResult_48 = true;
          }
        }

        if (andResult_48) {
          andResult_47 = true;
        }
      }

      if (andResult_47) {
        andResult_46 = true;
      }
    }

    assertTrue(andResult_46);

    Boolean andResult_53 = false;

    if (Utils.equals(team.getCar(1L).make, "Ford")) {
      Boolean andResult_54 = false;

      if (Utils.equals(team.getCar(1L).model, "Fiesta WRC")) {
        Boolean andResult_55 = false;

        if (Utils.equals(team.getCar(1L).year, 2017L)) {
          Boolean andResult_56 = false;

          if (Utils.equals(team.getCar(1L).bhp, 380L)) {
            Boolean andResult_57 = false;

            if (Utils.equals(team.getCar(1L).weight, 1190L)) {
              Boolean andResult_58 = false;

              if (Utils.equals(team.getCar(1L).traction, "4WD")) {
                Boolean andResult_59 = false;

                if (Utils.equals(team.getCar(1L).team, team)) {
                  if (Utils.equals(team.getCar(1L).number, 1L)) {
                    andResult_59 = true;
                  }
                }

                if (andResult_59) {
                  andResult_58 = true;
                }
              }

              if (andResult_58) {
                andResult_57 = true;
              }
            }

            if (andResult_57) {
              andResult_56 = true;
            }
          }

          if (andResult_56) {
            andResult_55 = true;
          }
        }

        if (andResult_55) {
          andResult_54 = true;
        }
      }

      if (andResult_54) {
        andResult_53 = true;
      }
    }

    assertTrue(andResult_53);

    assertTrue(Utils.equals(team.getCar(3L), null));
  }

  public void testGetMember() {

    Manager manager = new Manager("Malcom Wilson", "British");
    Team team = new Team("M-Sport", manager);
    team.addDriver("Sébastien Ogier", "French");
    team.addCoDriver("Julien Ingrassia", "French");
    team.addMechanic("Felipe Warner", "Brazilian");
    Boolean andResult_60 = false;

    if (Utils.equals(team.getMember("Sébastien Ogier").name, "Sébastien Ogier")) {
      if (Utils.equals(team.getMember("Sébastien Ogier").nationality, "French")) {
        andResult_60 = true;
      }
    }

    assertTrue(andResult_60);

    Boolean andResult_61 = false;

    if (Utils.equals(team.getMember("Julien Ingrassia").name, "Julien Ingrassia")) {
      if (Utils.equals(team.getMember("Julien Ingrassia").nationality, "French")) {
        andResult_61 = true;
      }
    }

    assertTrue(andResult_61);

    Boolean andResult_62 = false;

    if (Utils.equals(team.getMember("Felipe Warner").name, "Felipe Warner")) {
      if (Utils.equals(team.getMember("Felipe Warner").nationality, "Brazilian")) {
        andResult_62 = true;
      }
    }

    assertTrue(andResult_62);

    assertTrue(Utils.equals(team.getMember("Reiner Buchwalds"), null));
  }

  public TeamTest() {}

  public String toString() {

    return "TeamTest{}";
  }
}
