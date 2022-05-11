package IO.Contracts;

import Models.Table;

public interface FileReaderC<T> {
    T open(String fileLocation) throws Exception;
    void save(T data) throws Exception;
    void saveAs(T data, String fileLocation) throws Exception;
    String close();
    void createFile(String fileLocation) throws Exception;
    String getFileLocation();
}
