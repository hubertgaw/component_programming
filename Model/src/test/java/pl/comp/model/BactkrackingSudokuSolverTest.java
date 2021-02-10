package pl.comp.model;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BactkrackingSudokuSolverTest {
    private static final Logger logger =
            LogManager.getLogger(BactkrackingSudokuSolverTest.class.getName());

    @Test
    public void solveTest() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard();
        assertFalse(board.checkBoard());//Od razu po inicjalizacji sa zera, wiec powinno zwrocic falsz.
        solver.solve(board);
        assertTrue(board.checkBoard());//sprawdzamy czy uklad na planszy jest ok
        //System.out.println(board.toString());
        logger.info(board.toString());
    }

    //Czy dwa kolejne wywolania wygeneruja rozny uklad:
    @Test
    public void TheSameTest() {
        SudokuBoard example1 = new SudokuBoard();
        SudokuBoard example2 = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(example1);
        solver.solve(example2);

        boolean flag = true;
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if (example1.getNumber(i,j) != example2.getNumber(i,j)) {
                    flag = false;
                    break;
                }
            }
        }
        assertFalse(flag);
    }

}
