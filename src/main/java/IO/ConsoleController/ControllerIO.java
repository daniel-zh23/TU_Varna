package IO.ConsoleController;

import IO.Contracts.Reader;

import java.util.Scanner;

public class ControllerIO implements Reader {
    private Scanner reader;

    public ControllerIO() {
        reader = new Scanner(System.in);
    }
    @Override
    public String read() { return reader.nextLine();}

    @Override
    public void write(String data) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        if (data.equals("")) {
            System.out.print("> ");
        }
        else {
            System.out.println(data);
        }
    }
}
