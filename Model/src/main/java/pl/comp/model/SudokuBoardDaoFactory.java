package pl.comp.model;

import java.io.File;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SudokuBoardDaoFactory {
    public Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    public Dao<SudokuBoard> getDatabaseDao() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(
                        "SudokuBoards");
        EntityManager entityManager =
                entityManagerFactory.createEntityManager();
        JpaSudokuBoardDao jpaSudokuBoardDao = new JpaSudokuBoardDao(entityManager);
        return jpaSudokuBoardDao;
    }
}
