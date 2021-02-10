package pl.comp;

import pl.comp.model.SudokuBoard;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Easy extends Difficulty {
    private Set<Integer> randomNumbers = new HashSet<>();

    @Override
    public SudokuBoard eraseFields(SudokuBoard entryBoard) {
        while (randomNumbers.size() < 43) {
            Random rand = new Random();
            randomNumbers.add(rand.nextInt(81));
        }
        Iterator<Integer> itr = randomNumbers.iterator();
        while (itr.hasNext()) {
            Integer number = itr.next();
            entryBoard.setNumber(number / 9,number % 9, 0);
        }
        return entryBoard;
    }
}
