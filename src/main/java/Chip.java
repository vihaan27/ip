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
        String input;

        do {
            System.out.print("~ ");
            input = in.nextLine();

            try {
                processInput(input);
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }

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

    public static void addToDo(String input) {
        String[] words = input.split(" ");
        try {
            if (words.length < 2) {
                throw new EmptyDescriptionException();
            }
            int descriptionStart = input.indexOf(words[1]);
            String description = input.substring(descriptionStart);
            ToDo toDo = new ToDo(description.trim());
            addTask(toDo);
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addEvent(String input) {
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

    public static void addDeadline(String input) {
        String[] words = input.split(" ");
        int descriptionStart = input.indexOf(words[1]);
        int descriptionEnd = input.indexOf("/by");
        String description = input.substring(descriptionStart, descriptionEnd - 1);
        String by = input.substring(descriptionEnd + 4);
        Deadline deadline = new Deadline(description.trim(), by.trim());
        addTask(deadline);
    }

    public static void markTask(String input) {
        String[] words = input.split(" ");
        try {
            int taskIndex = Integer.parseInt(words[1]);
            if (taskIndex < 0 || taskIndex > taskCount) {
                throw new InvalidTaskIndexException(taskIndex);
            }
            tasks[taskIndex - 1].setDone(true);
        } catch (IndexOutOfBoundsException i) {
            System.out.println("Please enter a task number.");
        } catch (NumberFormatException n) {
            System.out.println("Oops! Please enter a valid numerical task number.");
        } catch (InvalidTaskIndexException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void unmarkTask(String input) {
        String[] words = input.split(" ");
        try {
            int taskIndex = Integer.parseInt(words[1]);
            if (taskIndex < 0 || taskIndex > taskCount) {
                throw new InvalidTaskIndexException(taskIndex);
            }
            tasks[taskIndex - 1].setDone(false);
        } catch (IndexOutOfBoundsException i) {
            System.out.println("Please enter a task number.");
        } catch (NumberFormatException n) {
            System.out.println("Oops! Please enter a valid numerical task number.");
        } catch (InvalidTaskIndexException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void processInput(String input) throws InvalidCommandException {
        String[] words = input.split(" ");
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
            throw new InvalidCommandException();
        }
    }
}
