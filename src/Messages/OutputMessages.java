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
}
