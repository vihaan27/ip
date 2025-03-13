/**
 * Represents a task of deadline type, with a specified deadline date.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task, with a [D] marker for deadline task type.
     * @return a formatted string with information of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the deadline task in the specified format to save to file.
     * @return formatted string representation of deadline task in save format.
     */
    @Override
    public String toSaveFormat(){
        String isDoneString = isDone() ? "1" : "0";
        return "D|"+ isDoneString + "|" + description + "|" + by;
    }
}
