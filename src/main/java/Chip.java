import java.util.Scanner;

public class Chip {
    public static final int MAX_TASKS = 100;
    public static Task[] tasks = new Task[MAX_TASKS];
    public static int taskCount = 0;

    public static void main(String[] args) {
        String greeting = "Hi! I'm Chip. What can I do for you?";
        String exit = "Bye. Hope to see you again soon!";
        System.out.println(greeting);


        Scanner in = new Scanner(System.in);
        String input;
        do {
            System.out.print("~ ");
            input = in.nextLine();
            String[] words = input.split(" ");

            switch (words[0]) {
            case "list":
                printList(tasks, taskCount);
                break;
            case "bye":
                break;
            case "mark":
                if (words.length < 2) {
                    System.out.println("Please enter valid task number.");
                    break;
                }
                int taskToMark = Integer.parseInt(words[1]);
                if (taskToMark > 0 && taskToMark <= taskCount) {
                    tasks[taskToMark - 1].setDone(true);
                } else {
                    System.out.println("No item found at position " + taskToMark);
                }
                break;
            case "unmark":
                if (words.length < 2) {
                    System.out.println("Please enter valid task number.");
                    break;
                }
                int taskToUnmark = Integer.parseInt(words[1]);
                if (taskToUnmark > 0 && taskToUnmark <= taskCount) {
                    tasks[taskToUnmark - 1].setDone(false);
                } else {
                    System.out.println("No item found at position " + taskToUnmark);
                }
                break;
            case "todo":
                int start = input.indexOf(words[1]);
                String item = input.substring(start);
                ToDo toDo = new ToDo(item.trim());
                addTask(toDo);
                break;
            case "deadline":
                int descriptionStart = input.indexOf(words[1]);
                int descriptionEnd = input.indexOf("/by");
                String description = input.substring(descriptionStart, descriptionEnd - 1);
                String by = input.substring(descriptionEnd + 4);
                Deadline deadline = new Deadline(description.trim(), by.trim());
                addTask(deadline);
                break;
            case "event":
                int descStart = input.indexOf(words[1]);
                int descEnd = input.indexOf("/from");
                String desc = input.substring(descStart, descEnd - 1);
                int fromEnd = input.indexOf("/to");
                String from = input.substring(descEnd + 6, fromEnd);
                String to = input.substring(fromEnd + 4);
                Event event = new Event(desc.trim(), from.trim(), to.trim());
                addTask(event);
                break;
            default:
                System.out.println("I'm sorry, I didn't understand that :(");
                break;
            }
        } while (!input.equals("bye"));

        System.out.println(exit);
    }

    public static void addTask(Task t) {
        tasks[taskCount] = t;
        taskCount++;
        System.out.println("Successfully added item!");
        System.out.println(tasks[taskCount - 1].toString());
        System.out.println("Your list contains " + taskCount + " tasks");
    }

    public static void printList(Task[] tasks, int taskCount) {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i].toString());

        }
    }
}
