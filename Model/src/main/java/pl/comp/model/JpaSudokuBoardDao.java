package pl.comp.model;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.persistence.EntityManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.comp.model.exceptions.NoSuchFileException;
import pl.comp.model.exceptions.ReadFromFileException;
import pl.comp.model.exceptions.WriteToDatabaseException;
import pl.comp.model.exceptions.WriteToFileException;

public class JpaSudokuBoardDao implements Dao<SudokuBoard> {
    private EntityManager entityManager;
    private static final Logger logger =
            LogManager.getLogger(FileSudokuBoardDao.class.getName());
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");


    public JpaSudokuBoardDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //all boards from DB:
    public List<SudokuBoard> findAll() {
        return entityManager
                .createQuery("select a from SudokuBoard a", SudokuBoard.class)
                .getResultList();
    }


    //write to DB:
    public boolean write(SudokuBoard sudokuBoard) throws WriteToDatabaseException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(sudokuBoard);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            logger.error(bundle.getObject("writeDBErrorMessage").toString());
            throw new WriteToDatabaseException(bundle
                    .getObject("writeDBErrorMessage").toString(), e);

        }
    }

    //read first Board from DB:
    @Override
    public SudokuBoard read() {
        SudokuBoard sudokuBoard = entityManager.find(SudokuBoard.class, 1L);
        return sudokuBoard;
    }

    //read chosen Board from DB:
    public SudokuBoard read(Long id) {
        SudokuBoard sudokuBoard = entityManager.find(SudokuBoard.class, id);
        return sudokuBoard;
    }

    /*
    @Override //checkstyle mowi aby unikac tej metody,
    //jednak zgodnie z poleceniem na Wikampie mielismy ja nadpisac
    //dlatego nadpisalismy ja, ale musielismy zakomentowac aby chekstyle przechodzil
    protected void finalize() throws Throwable {
        entityManager.close();

        //logger.info("files closed using finalize method");
    }
    */

}

