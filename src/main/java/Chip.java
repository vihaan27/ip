import java.util.Scanner;

public class Chip {
    public static final int MAX_TASKS = 100;
    public static Task[] tasks = new Task[MAX_TASKS];
    public static int taskCount = 0;

    public static void main(String[] args) {
        String greeting = "Hi! I'm Chip. What can I do for you?";
        String exit = "Bye. See you again soon!";
        System.out.println(greeting);
        Scanner in = new Scanner(System.in);
        String input, description;
        int descriptionStart, descriptionEnd;

        do {
            System.out.print("~ ");
            input = in.nextLine();
            processInput(input);

        } while (!input.equals("bye"));

        System.out.println(exit);
    }

    public static void printList(Task[] tasks, int taskCount) {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i].toString());

        }
    }

    public static void addTask(Task t) {
        tasks[taskCount] = t;
        taskCount++;
        System.out.println("Successfully added item!");
        System.out.println(tasks[taskCount - 1].toString());
        System.out.println("Your list contains " + taskCount + " tasks");
    }

    public static void addToDo(String input){
        String[] words = input.split(" ");
        int descriptionStart = input.indexOf(words[1]);
        String description = input.substring(descriptionStart);
        ToDo toDo = new ToDo(description.trim());
        addTask(toDo);
    }

    public static void addEvent(String input){
        String[] words = input.split(" ");
        int descriptionStart = input.indexOf(words[1]);
        int descriptionEnd = input.indexOf("/from");
        String description = input.substring(descriptionStart, descriptionEnd - 1);
        int fromEnd = input.indexOf("/to");
        String from = input.substring(descriptionEnd + 6, fromEnd);
        String to = input.substring(fromEnd + 4);
        Event event = new Event(description.trim(), from.trim(), to.trim());
        addTask(event);
    }

    public static void addDeadline(String input){
        String[] words = input.split(" ");
        int descriptionStart = input.indexOf(words[1]);
        int descriptionEnd = input.indexOf("/by");
        String description = input.substring(descriptionStart, descriptionEnd - 1);
        String by = input.substring(descriptionEnd + 4);
        Deadline deadline = new Deadline(description.trim(), by.trim());
        addTask(deadline);
    }

    public static void markTask(String input){
        String words[] = input.split(" ");
        if (words.length < 2) {
            System.out.println("Please enter valid task number.");
            return;
        }
        int taskToMark = Integer.parseInt(words[1]);
        if (taskToMark > 0 && taskToMark <= taskCount) {
            tasks[taskToMark - 1].setDone(true);
        } else {
            System.out.println("No item found at position " + taskToMark);
        }
    }

    public static void unmarkTask(String input){
        String[] words = input.split(" ");
        if (words.length < 2) {
            System.out.println("Please enter valid task number.");
            return;
        }
        int taskToUnmark = Integer.parseInt(words[1]);
        if (taskToUnmark > 0 && taskToUnmark <= taskCount) {
            tasks[taskToUnmark - 1].setDone(false);
        } else {
            System.out.println("No item found at position " + taskToUnmark);
        }
    }


    public static void processInput(String input){
        String[] words = input.split(" ");
        String description;
        int descriptionStart, descriptionEnd;

        switch (words[0]) {
        case "list":
            printList(tasks, taskCount);
            break;
        case "bye":
            break;
        case "mark":
            markTask(input);
            break;
        case "unmark":
            unmarkTask(input);
            break;
        case "todo":
            addToDo(input);
            break;
        case "deadline":
            addDeadline(input);
            break;
        case "event":
            addEvent(input);
            break;
        default:
            System.out.println("I'm sorry, I didn't understand that :(");
            break;
        }
    }
}
