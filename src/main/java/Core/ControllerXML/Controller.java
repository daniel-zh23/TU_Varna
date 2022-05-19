package Core.ControllerXML;

import Core.Contracts.ControllerC;
import Errors.FileAlreadyOpenedException;
import IO.Contracts.FileReaderC;
import IO.ReaderXML.Reader;
import Messages.OutputMessages;
import Models.Table;
import jakarta.xml.bind.UnmarshalException;

import java.io.FileNotFoundException;
import java.util.Locale;

public class Controller implements ControllerC {
    private Table table;
    private FileReaderC fileReader;

    public Controller(){
        this.fileReader = new Reader<Table>(); //Creates an instance of the file reader
    }
    @Override
    public String open(String fileLocation) {
        try {
            this.table = (Table) this.fileReader.open(fileLocation); //Tries to open the file and unmarshal the table
        }
        catch (Exception e) { //Catches if any exceptions are thrown and return the proper message to the user
            if (e instanceof FileAlreadyOpenedException){
                return OutputMessages.fileAlreadyOpened;
            }
            if (e instanceof FileNotFoundException) {
                try {
                    this.fileReader.createFile(fileLocation);
                    this.table = new Table();
                    return String.format(OutputMessages.fileNotFoundNewCreated, fileLocation);
                }
                catch (Exception e1) {
                    return OutputMessages.fileCannotBeCreated;
                }
            }
            if (e instanceof UnmarshalException && e.getMessage() != null){
                return OutputMessages.fileDifferentData;
            }
            else {
                this.table = new Table();
                return OutputMessages.fileOpenedEmpty;
            }
        }
        return String.format(OutputMessages.fileOpened, fileLocation);
    }

    @Override
    public String close() {
        if (this.table != null) {
            this.table = null;
            return this.fileReader.close();
        }
        return OutputMessages.noFileOpened;
    }

    @Override
    public String saveAs(String fileLocation) {
        try {
            if (table != null){
                this.fileReader.saveAs(this.table, fileLocation);
                return String.format(OutputMessages.fileSavedAsSuccessfully, fileLocation);
            }
            throw new Exception();
        }
        catch (Exception e) {
            return OutputMessages.noFileOpened;
        }
    }

    @Override
    public String save() {
        try {
            this.fileReader.save(this.table);
            return String.format(OutputMessages.fileSavedSuccessfully, this.fileReader.getFileLocation());
        }
        catch (Exception e) {
            return OutputMessages.noFileOpened;
        }
    }

    @Override
    public String print() {
        if (this.table != null) {
            return this.table.toString();
        }
        return OutputMessages.noTable;
    }

    @Override
    public String edit(String cell, String data) {
        data = data.toUpperCase(Locale.ROOT);
        try {
            if (this.table != null) {
                String[] cellData = cell.split("");
                this.table.editCell(Integer.parseInt(cellData[1]), Integer.parseInt(cellData[3]), data);
                return String.format(OutputMessages.successfulEdit, cell);
            }
            return OutputMessages.noTable;
        }
        catch (Exception e) {
            return OutputMessages.invalidCell;
        }
    }
}
