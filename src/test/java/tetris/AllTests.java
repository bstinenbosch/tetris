package tetris;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AbstractShapeTest.class, ControllerTest.class, GridTest.class, ScoreTest.class,
    TetrominoMovementHandlerTest.class, TickTest.class, LoggerTest.class, TetrominoTest.class })
public class AllTests {

}
