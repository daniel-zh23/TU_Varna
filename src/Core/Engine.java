package Core;

import Core.Contracts.EngineC;
import Messages.OutputMessages;

import java.util.Scanner;

public class Engine implements EngineC {
    private Scanner reader;

    public Engine () {
        reader = new Scanner(System.in);
    }

    @Override
    public void run() {
        System.out.println(OutputMessages.startMessage);
        while (true) {
            System.out.print("> ");
            String[] line = reader.nextLine().split(" ");
            String command = line[0];
            if (command.equals("exit")){
                break;
            }

            if (command.equals("open")){

            }
            else if (command.equals("close")) {

            }
            else if (command.equals("save")) {

            }
            else if (command.equals("saveas")) {

            }
            else if (command.equals("help")){
                System.out.println(OutputMessages.helpMessage);
            }
            else {
                System.out.println(OutputMessages.invalidCommandMessage);
            }
        }
    }
}
