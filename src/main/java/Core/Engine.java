package Core;

import Core.Contracts.ControllerC;
import Core.Contracts.EngineC;
import Core.ControllerXML.Controller;
import IO.ConsoleController.ControllerIO;
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
        label:
        while (true) {
            consoleController.write("");
            String[] line = consoleController.read().split(" ");
            String command = line[0];
            if ((line[0].equals("saveas") || line[0].equals("open")) && line.length < 2){
                consoleController.write(String.format(OutputMessages.commandRequireTwoParameters, line[0]));
                continue;
            }

            switch (command) {
                case "exit":
                    consoleController.write(OutputMessages.exitMessage);
                    break label;
                case "open":
                    consoleController.write(controller.open(line[1]));
                    break;
                case "close":
                    consoleController.write(controller.close());
                    break;
                case "save":
                    consoleController.write(controller.save());
                    break;
                case "saveas":
                    consoleController.write(controller.saveAs(line[1]));
                    break;
                case "print":
                    consoleController.write(controller.print());
                    break;
                case "edit":
                    consoleController.write(controller.edit(line[1], line[2]));
                    break;
                case "help":
                    consoleController.write(OutputMessages.helpMessage);
                    break;
                default:
                    consoleController.write(OutputMessages.invalidCommandMessage);
                    break;
            }
        }
    }
}
