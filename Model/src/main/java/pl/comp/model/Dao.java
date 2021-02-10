package pl.comp.model;

import pl.comp.model.exceptions.NoSuchFileException;
import pl.comp.model.exceptions.ReadFromFileException;
import pl.comp.model.exceptions.WriteToDatabaseException;
import pl.comp.model.exceptions.WriteToFileException;

public interface Dao<T> {
    T read() throws NoSuchFileException, ReadFromFileException;

    boolean write(T obj) throws WriteToFileException, WriteToDatabaseException;
}
