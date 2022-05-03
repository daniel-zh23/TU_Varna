package IO.Contracts;


import Errors.FileAlreadyOpenedException;

import java.io.FileNotFoundException;

public interface FileReaderC<T> {
    T open(String fileLocation) throws Exception;
    void save(T data) throws Exception;
    void saveAs(String data, String fileLocation);
    String close();
    void createFile(String fileLocation) throws Exception;
}
