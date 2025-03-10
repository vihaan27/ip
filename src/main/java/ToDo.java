public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat(){
        String isDoneString = isDone() ? "1" : "0";
        return "T|"+ isDoneString + "|" + description;
    }
}
