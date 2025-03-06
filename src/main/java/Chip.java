import java.util.Scanner;

public class Chip {
    public static final String DIRECTORY = "data";
    public static final String FILE_PATH = "data/chip.txt";
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList taskList;

    public Chip(String filePath, String directory){
        this.ui = new Ui();
        this.storage = new Storage(filePath, directory, ui);
        this.taskList = new TaskList(storage, ui);
        this.parser = new Parser(taskList);
    }

    public void run(){
        ui.printGreeting();
        Scanner in = new Scanner(System.in);
        String input;

        do {
            ui.printInputMarker();
            input = in.nextLine();
            try {
                parser.processInput(input);
            } catch (InvalidCommandException e) {
                ui.printChipExceptionMessage(e);
            }
        } while (!input.equals("bye"));

        ui.printExit();
    }

    public static void main(String[] args) {
        Chip chip = new Chip(FILE_PATH, DIRECTORY);
        chip.run();
    }
}
