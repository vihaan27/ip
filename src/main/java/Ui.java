public class Ui {
    private final String GREETING = "Hi! I'm Chip. What can I do for you?";
    private final String EXIT = "Bye. See you again soon!";

    public Ui(){
    }

    public void printGreeting(){
        System.out.println(GREETING);
    }

    public void printExit(){
        System.out.println(EXIT);
    }

    public void printInputMarker(){
        System.out.print("~ ");
    }

}
