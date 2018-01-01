package Rally2;

import java.util.*;
import org.overture.codegen.runtime.*;
import junit.framework.*;
import junit.textui.TestRunner;

@SuppressWarnings("all")
public class Testing {
	public static Test suite() {
        TestSuite suite = new TestSuite();
        
        suite.addTest(new AtoBTest("testAddRound"));
        suite.addTest(new AtoBTest("testGetCarRounds"));
        suite.addTest(new AtoBTest("testGetFinalRounds"));
        suite.addTest(new AtoBTest("testGetRound"));
        suite.addTest(new AtoBTest("testSortRounds"));
        
        suite.addTest(new CarTest("testSetCoDriver"));
        suite.addTest(new CarTest("testSetDriver"));
        suite.addTest(new CarTest("testAddMechanic"));
        
        suite.addTest(new RoundTest("testRoundCreation"));
        suite.addTest(new RoundTest("testIsEqual"));
        
        suite.addTest(new DateTest("testIsBefore"));
        suite.addTest(new DateTest("testIsEqual"));
        
        suite.addTest(new StageTest("testSortStageResults"));
        suite.addTest(new StageTest("testGetStageResult"));
        suite.addTest(new StageTest("testStage"));
        suite.addTest(new StageTest("testAddStageResult"));
        
        suite.addTest(new TeamTest("testTeam"));
        suite.addTest(new TeamTest("testTeamResults"));
        suite.addTest(new TeamTest("testGetMember"));
        suite.addTest(new TeamTest("testGetCar"));
        
        suite.addTest(new SpecialStageTest("testStageSort"));
        suite.addTest(new SpecialStageTest("testGetStage"));
        suite.addTest(new SpecialStageTest("testAddStage"));
        suite.addTest(new SpecialStageTest("testGetResults"));
        return suite;
	}
	
  public static void main() {

    new TestRunner().run(suite());
  }

  public Testing() {}

  public String toString() {

    return "Testing{}";
  }
}
