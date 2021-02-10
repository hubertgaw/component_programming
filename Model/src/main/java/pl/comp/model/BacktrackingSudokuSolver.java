package pl.comp.model;

import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver {

    //flaga, która posłuży nam do sprawdzania czy board
    //jest pelne i do zerowania przed wywolaniem fillBoard:
    public boolean flag = false;

    // Ustawiamy wiersz i kolumne na wartosciach
    // nastepnej wolnej komorki, jesli nie ma wolnej
    // ustawiamy na -1
    private int[] freeCell(int row, int col, SudokuBoard board) {
        for (int i = 0; i < SudokuBoard.N; i++) {
            for (int j = 0; j < SudokuBoard.N; j++) {
                //cell is unassigned
                if (board.getNumber(i,j) == 0) {
                    row = i;
                    col = j;
                    int[] a = {row, col};
                    return a;
                }
            }
        }
        int[] a = {-1, -1};
        return a;
    }

    //sprawdzamy czy mozna dac iczbe w danej komorce
    //(zasady sudoku)
    private boolean valid(int n, int r, int c, SudokuBoard board) {
        //wiersze
        for (int i = 0; i < SudokuBoard.N; i++) {
            //there is a cell with same value
            if (board.getNumber(r,i) == n) {
                return false;
            }
        }
        //kolumny
        for (int i = 0; i < SudokuBoard.N; i++) {
            if (board.getNumber(i,c) == n) {
                return false;
            }
        }
        //kwadraty 3x3
        int rowStart = (r / 3) * 3;
        int colStart = (c / 3) * 3;
        for (int i = rowStart; i < rowStart + 3; i++) {
            for (int j = colStart; j < colStart + 3; j++) {
                if (board.getNumber(i,j) == n) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean solve(SudokuBoard board) {
        if (flag) {
            for (int i = 0; i < board.N; i++) {
                for (int j = 0; j < board.N; j++) {
                    board.setNumber(i,j,0);
                }
            }
            flag = false;
        }
        int row = 0;
        int col = 0;
        int[] a = freeCell(row, col, board);
        if (a[0] < 0) { //Jesli zwrocimy -1 to zwracamy true, sudoku ulozone
            return true;
        }
        row = a[0];
        col = a[1];
        Random rand = new Random();
        int start = rand.nextInt(9) + 1;
        for (int i = 0;i <= SudokuBoard.N;i++) {
            if (valid(start, row, col, board)) {
                board.setNumber(row,col,start);
                //backtracking
                if (solve(board)) {
                    flag = true;
                    return true;
                }
                //Jesli nie da sie wpisac tej liczby
                //to wpisujemy 0
                board.setNumber(row,col,0);
            }

        }
        return false;
    }
}

