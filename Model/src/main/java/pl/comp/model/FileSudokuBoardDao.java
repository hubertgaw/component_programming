package pl.comp.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.comp.model.exceptions.NoSuchFileException;
import pl.comp.model.exceptions.ReadFromFileException;
import pl.comp.model.exceptions.WriteToFileException;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;


public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private final String name;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private static final Logger logger =
            LogManager.getLogger(FileSudokuBoardDao.class.getName());
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");

    public FileSudokuBoardDao(String name) {
        this.name = name;
    }

    @Override
    public SudokuBoard read() throws NoSuchFileException, ReadFromFileException {
        SudokuBoard board = new SudokuBoard();
        try {
            fileInputStream = new FileInputStream(name); // FileInputStream implements AutoCloseable
            int b = fileInputStream.read();
            loop:
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (b == -1) {
                        break loop;
                    }
                    char sign = (char)b;
                    Integer number = Integer.parseInt(String.valueOf(sign));
                    board.setNumber(i,j,number);
                    b = fileInputStream.read();
                }
            }
            //ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            //board = (SudokuBoard) ois.readObject();

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            logger.error(bundle.getObject("fileNotFoundMessage").toString());
            throw new NoSuchFileException(bundle.getObject("fileNotFoundMessage").toString(), e);

        } catch (IOException e) {
            //e.printStackTrace();
            logger.error(bundle.getObject("readFileErrorMessage").toString());
            throw new ReadFromFileException(bundle.getObject("readFileErrorMessage").toString());
        } /* catch (ClassNotFoundException e) {
            e.printStackTrace();
        } */
        return board;
    }

    @Override
    public boolean write(SudokuBoard obj) throws WriteToFileException {
        try {
            fileOutputStream = new FileOutputStream(name);//FileOutputStream implementsAutoCloseable
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    Character bufor = Character.forDigit(obj.getNumber(i,j),10);
                    fileOutputStream.write(bufor);
                }
            }
            //ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            //oos.writeObject(obj);
            return true;
        } catch (IOException e) {
            logger.error(bundle.getObject("writeFileErrorMessage").toString());
            throw new WriteToFileException(bundle.getObject("writeFileErrorMessage").toString(), e);
            //e.printStackTrace();
        }
    }

    /*
    @Override //checkstyle mowi aby unikac tej metody,
    //jednak zgodnie z poleceniem na Wikampie mielismy ja nadpisac
    //dlatego nadpisalismy ja, ale musielismy zakomentowac aby chekstyle przechodzil
    protected void finalize() throws Throwable {
        try {
            fileInputStream.close();
            fileOutputStream.close();
            logger.info("files closed using finalize method");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     */
}
