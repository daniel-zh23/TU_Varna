package Core;

import Core.Contracts.ControllerC;
import Core.Contracts.EngineC;
import IO.ConsoleController.ControllerIO;
import IO.Contracts.FileReaderC;
import IO.ReaderXML.Reader;
import Messages.OutputMessages;
import Models.Table;

public class Engine implements EngineC {
    private IO.Contracts.Reader consoleController;
    private ControllerC controller;

    public Engine () {
        consoleController = new ControllerIO();
        controller = new Controller();
    }

    @Override
    public void run() {
        consoleController.write(OutputMessages.startMessage);
        while (true) {
            consoleController.write("");
            String[] line = consoleController.read().split(" ");
            String command = line[0];

            if (command.equals("exit")){
                consoleController.write(OutputMessages.exitMessage);
                break;
            }

            if (command.equals("open")){
                    consoleController.write(controller.open(line[1]));
            }
            else if (command.equals("close")) {
                controller.close();
            }
            else if (command.equals("save")) {

            }
            else if (command.equals("saveas")) {

            }
            else if (command.equals("help")){
                consoleController.write(OutputMessages.helpMessage);
            }
            else {
                consoleController.write(OutputMessages.invalidCommandMessage);
            }
        }
    }
}
