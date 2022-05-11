package Messages;

public final class OutputMessages {
    //Command Messages
    public static final String startMessage = "Type \"help\" to see a list of commands.";
    public static final String helpMessage = """
            The following commands are supported:
            open <file>                   opens <file>
            close                         closes currently opened file
            save                          saves the currently open file
            saveas <file>                 saves the currently open file in <file>
            print                         prints the current table on the console
            edit <rowAndColumn> <content> edits a cell in format R<rowNumber>C<columnNumber>
            help                          prints this information
            exit                          exists the program\s""";
    public static final String invalidCommandMessage = "Invalid command type \"help\" to see a list of commands.";
    public static final String exitMessage = "Exiting the program...";
    public static final String commandRequireTwoParameters = "Command \"%s\" require 2 parameters.";
    //File Messages - open
    public static final String fileOpened = "Successfully opened %s.";
    public static final String fileNotFoundNewCreated = "File was not found so new file was created with path %s.";
    public static final String fileAlreadyOpened = "File is already opened, please close it before opening new file.";
    public static final String fileClosed = "Successfully closed %s.";
    public static final String fileCannotBeCreated = "File cannot be created.";
    public static final String fileOpenedEmpty = "File is opened, but empty. New table created.";
    public static final String fileDifferentData = "File contents are in different data format.";
    //File Messages - save
    public static final String fileSavedSuccessfully = "Successfully saved %s";
    public static final String fileSavedAsSuccessfully = "Successfully saved file %s";
    //File Messages - print
    public static final String noTable = "There is not currently open table.";
    //File Messages - close
    public static final String noFileOpened = "There is no opened file.";
}
