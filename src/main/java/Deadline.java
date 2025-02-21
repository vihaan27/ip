public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSaveFormat(){
        String isDoneString = isDone() ? "1" : "0";
        return "D|"+ isDoneString + "|" + description + "|" + by;
    }
}
