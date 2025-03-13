/**
 * Represents a task of type event, with a specified start and end time.
 */
public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the event task, with a [E] marker for event task type.
     * @return a formatted string with information of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }

    /**
     * Returns a string representation of the event task in the specified format to save to file.
     * @return formatted string representation of event task in save format.
     */
    @Override
    public String toSaveFormat(){
        String isDoneString = isDone() ? "1" : "0";
        return "E|"+ isDoneString + "|" + description + "|" +startTime + "|" + endTime;
    }
}
