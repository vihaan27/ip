import java.util.ArrayList;

/**
 * Handles CLI interactions with user such as printing text outputs.
 */
public class Ui {
    private final String GREETING = "Hi! I'm Chip. What can I do for you?";
    private final String EXIT = "Bye. See you again soon!";

    public Ui(){
    }

    /**
     * Prints a list of tasks by printing each task's index number and String representation.
     * @param tasks list of tasks to print.
     */
    public void printList(ArrayList<Task> tasks){
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Prints a given String message to the user.
     * @param message String to be printed.
     */
    public void printMessage(String message){
        System.out.println(message);
    }

    /**
     * Prints greeting message.
     */
    public void printGreeting(){
        System.out.println(GREETING);
    }

    /**
     * Prints exit message.
     */
    public void printExit(){
        System.out.println(EXIT);
    }

    /**
     * Prints line separator.
     */
    public void printLineSeparator(){
        System.out.println("==========================================================");
    }

    /**
     * Prints marker to signify that user input is needed.
     */
    public void printInputMarker(){
        System.out.print("~ ");
    }

    /**
     * Prints the exception message for a given ChipException.
     * @param e ChipException to print message for.
     */
    public void printChipExceptionMessage(ChipException e){
        System.out.println(e.getMessage());
    }

    /**
     * Prints message indicating that the user has inputted an invalid index number for their task list.
     */
    public void printInvalidIndexMessage(){
        System.out.println("Please enter a valid task number.");
    }

    /**
     * Prints message indicating that user has not entered a valid number for a required numerical field.
     */
    public void printNumberFormatMessage(){
        System.out.println("Oops! Please enter a valid numerical task number.");
    }

    /**
     * Prints confirmation that a task has been added to the list.
     * Displays the task and the updated number of tasks in the list.
     * @param taskString String description of  added task to print.
     * @param taskCount updated task count to print.
     */
    public void confirmTaskAdded(String taskString, int taskCount){
        System.out.println("Successfully added item!");
        System.out.println(taskString);
        System.out.println("Your list contains " + taskCount + " tasks");
    }

    /**
     * Prints confirmation that a task has been deleted from the list.
     * Displays the deleted task and updated number of tasks in the list.
     * @param taskString String description of deleted task to print.
     * @param taskCount updated task count to print.
     */
    public void confirmTaskDeleted(String taskString, int taskCount){
        System.out.println("Alright! I've deleted the task: ");
        System.out.println("-> " + taskString);
        System.out.println("Your list now contains " + taskCount + " tasks.");
    }

    /**
     * Prints confirmation that a task has been marked as done.
     * @param taskString String description of marked task to print.
     * @param taskIndex Index of marked task to print.
     */
    public void confirmTaskMarked(String taskString, int taskIndex){
        System.out.println("Alright! I've marked task number " + taskIndex + ":");
        System.out.println("-> " + taskString);
    }

    /**
     * Prints confirmation that a task has been unmarked as done.
     * @param taskString String description of unmarked task to print.
     * @param taskIndex Index of marked task to print.
     */
    public void confirmTaskUnmarked(String taskString, int taskIndex){
        System.out.println("Alright! I've unmarked task number " + taskIndex + ":");
        System.out.println("-> " + taskString);
    }
}
