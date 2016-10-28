package tetris;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import common.Coordinate;
import tetris.shapes.decorators.MovableShape;

import javafx.embed.swing.JFXPanel;

import javax.swing.SwingUtilities;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GridTest {

    private Grid grid;

    @BeforeClass
    public static void initToolkit() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel();
            latch.countDown();
        });

        if (!latch.await(5L, TimeUnit.SECONDS))
            throw new ExceptionInInitializerError();
    }

    @Before
    public void set_up_grid() {
        View view = new View();
        Settings settings = new Settings();
        DummyController controller = new DummyController(view, settings);
        this.grid = new Grid(10, 20);
    }

    @Test
    public void test_if_grid_is_empty_at_initialization() {
        assertThat("grid is empty when initialized", isEmpty(this.grid), equalTo(true));
    }

    @Test
    public void test_width_of_grid() {
        assertThat("width of grid is equal to requested width", this.grid.width(), equalTo(10));
    }

    @Test
    public void test_height_of_grid() {
        assertThat("height of grid is equal to requested width", this.grid.height(), equalTo(20));
    }

    @Test
    public void test_register_shape_to_grid() {
        View view = new View();
        Settings settings = new Settings();
        DummyController controller = new DummyController(view, settings);
        Grid grid = new Grid(10, 20);
        Coordinate coordinate = new Coordinate(5, 5);
        MovableShape shape = new MovableShape(new DummyShapeO(), coordinate);
        grid.registerTetromino(shape);

        assertThat("grid is not empty when a shape is registered", isEmpty(grid), equalTo(false));
    }

    @Test
    public void test_clear_board() {
        Coordinate coordinate = new Coordinate(5, 5);
        MovableShape shape = new MovableShape(new DummyShapeO(), coordinate);
        grid.registerTetromino(shape);
        grid.clearBoard();

        assertThat("grid is empty after clearing", isEmpty(grid), equalTo(true));
    }

    private boolean isEmpty(Grid grid) {
        for (int x = 0; x < grid.width(); x++) {
            for (int y = 1; y < grid.height(); y++) {
                if (grid.get(x, y) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
