package Core;

import Core.Contracts.ControllerC;
import Core.Contracts.EngineC;
import Core.ControllerXML.Controller;
import IO.ConsoleController.ControllerIO;
import Messages.OutputMessages;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Engine implements EngineC {
    private IO.Contracts.Reader outputController;
    private ControllerC controller;

    public Engine () {
        outputController = new ControllerIO(); //Creating instance of the output controller(in this case console controller)
        controller = new Controller(); //Creating instance of the main program controller
    }

    @Override
    public void run() {
        outputController.write(OutputMessages.startMessage); //Writing the startup message
        label:
        while (true) { //Commands loop
            outputController.write("");
            String[] line = outputController.read().split(" "); //Reading line input and splitting it
            String command = line[0]; //Getting the first word in the array(the command)
            if ((line[0].equals("saveas") || line[0].equals("open")) && line.length < 2){ //Checks if save or open commands have enough parameters
                outputController.write(String.format(OutputMessages.commandRequireMoreParameters, line[0], 2)); //Output the correct message
                continue;
            }
            if (line[0].equals("edit") && line.length < 3){ //Checks if edit command has enough parameters
                outputController.write(String.format(OutputMessages.commandRequireMoreParameters, line[0], 3)); //Output the correct message
                continue;
            }

            switch (command) { //Depends on the command calls the necessary function from the controller and prints the output
                case "exit":
                    outputController.write(OutputMessages.exitMessage);
                    break label;
                case "open":
                    outputController.write(controller.open(line[1]));
                    break;
                case "close":
                    outputController.write(controller.close());
                    break;
                case "save":
                    outputController.write(controller.save());
                    break;
                case "saveas":
                    outputController.write(controller.saveAs(line[1]));
                    break;
                case "print":
                    outputController.write(controller.print());
                    break;
                case "edit":
                    outputController.write(controller.edit(line[1], Arrays.stream(line).skip(2).collect(Collectors.joining(" "))));
                    break;
                case "help":
                    outputController.write(OutputMessages.helpMessage);
                    break;
                default:
                    outputController.write(OutputMessages.invalidCommandMessage);
                    break;
            }
        }
    }
}
