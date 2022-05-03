package Messages;

public final class OutputMessages {
    public static final String startMessage = "Type \"help\" to see a list of commands.";
    public static final String helpMessage = """
            The following commands are supported:
            open <file>    opens <file>
            close          closes currently opened file
            save           saves the currently open file
            saveas <file>   saves the currently open file in <file>
            help           prints this information
            exit           exists the program\s""";
    public static final String invalidCommandMessage = "Invalid command type \"help\" to see a list of commands.";
    public static final String fileOpened = "Successfully opened %s.";
    public static final String fileNotFoundNewCreated = "File was not found so new file was created with path %s.";
    public static final String fileAlreadyOpened = "File is already opened, please close it before opening new file.";
    public static final String fileClosed = "Successfully closed %s.";
    public static final String fileCannotBeCreated = "File cannot be created.";
    public static final String exitMessage = "Exiting the program...";
    public static final String FileOpenedEmpty = "Successfully opened empty file (%s).";
}
