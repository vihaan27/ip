public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }

    @Override
    public String toSaveFormat(){
        String isDoneString = isDone() ? "1" : "0";
        return "E|"+ isDoneString + "|" + description + "|" +startTime + "|" + endTime;
    }
}
