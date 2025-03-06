import java.util.ArrayList;

public class Ui {
    private final String GREETING = "Hi! I'm Chip. What can I do for you?";
    private final String EXIT = "Bye. See you again soon!";

    public Ui(){
    }

    public void printList(ArrayList<Task> tasks){
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public void printMessage(String message){
        System.out.println(message);
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

    public void printChipExceptionMessage(ChipException e){
        System.out.println(e.getMessage());
    }

    public void printInvalidIndexMessage(){
        System.out.println("Please enter a valid task number.");
    }

    public void printNumberFormatMessage(){
        System.out.println("Oops! Please enter a valid numerical task number.");
    }

    public void confirmTaskAdded(String taskString, int taskCount){
        System.out.println("Successfully added item!");
        System.out.println(taskString);
        System.out.println("Your list contains " + taskCount + " tasks");
    }

    public void confirmTaskDeleted(String taskString, int taskCount){
        System.out.println("Alright! I've deleted the task: ");
        System.out.println("-> " + taskString);
        System.out.println("Your list now contains " + taskCount + " tasks.");
    }

    public void confirmTaskMarked(String taskString, int taskIndex){
        System.out.println("Alright! I've marked task number " + taskIndex + ":");
        System.out.println("-> " + taskString);
    }

    public void confirmTaskUnmarked(String taskString, int taskIndex){
        System.out.println("Alright! I've unmarked task number " + taskIndex + ":");
        System.out.println("-> " + taskString);
    }
}
