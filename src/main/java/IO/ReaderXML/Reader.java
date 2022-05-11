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
    public String getFileLocation() {
        return fileLocation;
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
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(data, new FileOutputStream(fileLocation));
    }

    @Override
    public void saveAs(T data, String fileLocation) throws JAXBException, IOException {
        createFile(fileLocation);
        JAXBContext context = JAXBContext.newInstance(Table.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(data, new FileOutputStream(fileLocation));
    }

    @Override
    public String close() {
        String result = String.format(OutputMessages.fileClosed, this.fileLocation);
        this.hasFile = false;
        this.fileLocation = null;
        return result;
    }

    @Override
    public void createFile(String fileLocation) throws IOException{
        File file = new File(fileLocation);
        if (file.createNewFile()) {
            hasFile = true;
            this.fileLocation = fileLocation;
        }
    }
}
