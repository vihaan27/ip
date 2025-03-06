public class Parser {
    private final TaskList taskList;

    public Parser(TaskList taskList){
        this.taskList = taskList;
    }

    public void processInput(String input) throws InvalidCommandException {
        String[] words = input.split(" ");
        switch (words[0]) {
        case "list":
            taskList.printList();
            break;
        case "bye":
            break;
        case "mark":
            taskList.markTask(input);
            break;
        case "unmark":
            taskList.unmarkTask(input);
            break;
        case "todo":
            taskList.addToDo(input);
            break;
        case "deadline":
            taskList.addDeadline(input);
            break;
        case "event":
            taskList.addEvent(input);
            break;
        case "delete":
            taskList.deleteTask(input);
            break;
        default:
            throw new InvalidCommandException();
        }
    }
}
