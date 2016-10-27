// package tetris;
//
// import org.junit.Before;
// import org.junit.Test;
//
// import static org.hamcrest.CoreMatchers.equalTo;
// import static org.hamcrest.MatcherAssert.assertThat;
//
// public class GridTest {
//
// private Grid grid;
//
// @Before
// public void set_up_grid() {
// this.grid = new Grid(new DummyController(), 10, 20);
// }
//
// @Test
// public void test_if_grid_is_empty_at_initialization() {
// assertThat("grid is empty when initialized", isEmpty(this.grid),
// equalTo(true));
// }
//
// @Test
// public void test_width_of_grid() {
// assertThat("width of grid is equal to requested width", this.grid.width(),
// equalTo(10));
// }
//
// @Test
// public void test_height_of_grid() {
// assertThat("height of grid is equal to requested width", this.grid.height(),
// equalTo(20));
// }
//
// @Test
// public void test_register_shape_to_grid() {
// Grid grid = new Grid(new DummyController(), 10, 20);
// Coordinate coordinate = new Coordinate(5, 5);
// DummyShape shape = new DummyShape(coordinate);
// grid.registerTetromino(shape);
//
// assertThat("grid is not empty when a tetromino is registered", isEmpty(grid),
// equalTo(false));
// }
//
// @Test
// public void test_clear_board() {
// Coordinate coordinate = new Coordinate(5, 5);
// DummyShape shape = new DummyShape(coordinate);
// grid.registerTetromino(shape);
// grid.clearBoard();
//
// assertThat("grid is empty after clearing", isEmpty(grid), equalTo(true));
// }
//
// @Test
// public void test_clear_line() {
// grid.registerTetromino(new DummyShape(new Coordinate(0, 0)));
// grid.registerTetromino(new DummyShape(new Coordinate(2, 0)));
// grid.registerTetromino(new DummyShape(new Coordinate(4, 0)));
// grid.registerTetromino(new DummyShape(new Coordinate(6, 0)));
// grid.registerTetromino(new DummyShape(new Coordinate(8, 0)));
//
// assertThat("two lines are cleared when a full row of O-shaped tetrominos "
// + "is placed at the bottom of the grid", grid.clearLines(), equalTo(2));
// }
//
// private boolean isEmpty(Grid grid) {
// for (int x = 0; x < grid.width(); x++) {
// for (int y = 1; y < grid.height(); y++) {
// if (grid.get(x, y) != 0) {
// return false;
// }
// }
// }
// return true;
// }
//
// }
