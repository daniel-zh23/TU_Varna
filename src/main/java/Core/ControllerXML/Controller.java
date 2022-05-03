package Core.ControllerXML;

import Core.Contracts.ControllerC;
import Errors.FileAlreadyOpenedException;
import IO.Contracts.FileReaderC;
import IO.ReaderXML.Reader;
import Messages.OutputMessages;
import Models.Table;

import java.io.FileNotFoundException;

public class Controller implements ControllerC {
    private Table table;
    private FileReaderC fileReader;

    public Controller(){
        fileReader = new Reader<Table>();

    }
    @Override
    public String open(String fileLocation) {
        try {
            table = (Table)fileReader.open(fileLocation);

            return String.format(OutputMessages.fileOpened, fileLocation);
        }
        catch (Exception e) {
            if (e instanceof FileAlreadyOpenedException){
                return OutputMessages.fileAlreadyOpened;
            }
            if (e instanceof FileNotFoundException) {
                try {
                    fileReader.createFile(fileLocation);
                    return String.format(OutputMessages.fileNotFoundNewCreated, fileLocation);
                }
                catch (Exception e1) {
                    return OutputMessages.fileCannotBeCreated;
                }
            }
            return String.format(OutputMessages.fileOpened, fileLocation);
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void saveAs(Table data, String fileLocation) {

    }

    @Override
    public void save() {

    }
}
