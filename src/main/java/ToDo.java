/**
 * Represents a task of to-do type.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task, with a [T] marker for to-do task type.
     * @return a formatted string with information of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the to-do task in the specified format to save to file.
     * @return formatted string representation of to-do task in save format.
     */
    @Override
    public String toSaveFormat(){
        String isDoneString = isDone() ? "1" : "0";
        return "T|"+ isDoneString + "|" + description;
    }
}
