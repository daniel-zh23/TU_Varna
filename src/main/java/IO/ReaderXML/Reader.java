package IO.ReaderXML;

import Errors.FileAlreadyOpenedException;
import IO.Contracts.FileReaderC;
import Messages.OutputMessages;
import Models.Table;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.*;

public class Reader<T> implements FileReaderC<T> {
    private String fileLocation;
    private boolean hasFile = false;

    public Reader() {
        fileLocation = null;
    }

    @Override
    public T open(String fileLocation) throws FileAlreadyOpenedException, JAXBException, FileNotFoundException {
        if (hasFile){
            throw new FileAlreadyOpenedException(OutputMessages.fileAlreadyOpened);
        }
        JAXBContext context = JAXBContext.newInstance(Table.class);
        Unmarshaller unMarshaller = context.createUnmarshaller();
        this.fileLocation = fileLocation;
        hasFile = true;
        T result = (T) unMarshaller.unmarshal(new FileInputStream(fileLocation));
        return result;
    }

    @Override
    public void save(T data) throws Exception{
        JAXBContext context = JAXBContext.newInstance(Table.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(data, new FileOutputStream(fileLocation));
    }

    @Override
    public void saveAs(String data, String fileLocation) {

    }

    @Override
    public String close() {
        hasFile = false;
        return String.format(OutputMessages.fileClosed, fileLocation);
    }

    @Override
    public void createFile(String fileLocation) throws Exception{
        File file = new File(fileLocation);
        if (file.createNewFile()) {
            hasFile = true;
            this.fileLocation = fileLocation;
        }
    }
}
