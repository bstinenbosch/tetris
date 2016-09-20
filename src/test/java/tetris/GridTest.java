package tetris;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GridTest {

    @Test
    public void testIfGridIsEmptyAtInitialization() {
	Grid grid = new Grid(10, 20);
	assertTrue(isEmpty(grid));
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
