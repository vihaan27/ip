import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Chip {
    public static final int MAX_TASKS = 100;
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static final String DIRECTORY = "data";
    public static final String FILE_PATH = "data/chip.txt";
    public static int taskCount = 0;

    public static void main(String[] args) {
        String greeting = "Hi! I'm Chip. What can I do for you?";
        String exit = "Bye. See you again soon!";

        System.out.println(greeting);
        loadSavedTasks();

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
    
    public static void printList(ArrayList<Task> tasks, int taskCount) {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());

        }
    }

    public static void loadSavedTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No saved tasks found.");
            return;
        }

        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                addSavedTask(s.nextLine());
            }
        } catch (FileNotFoundException f) {
            System.out.println("No saved tasks found.");
        }
    }

    public static void addSavedTask(String taskString) {
        String[] details = taskString.split("\\|");
        Task taskToAdd;
        switch (details[0]) {
        case "T":
            taskToAdd = new ToDo(details[2]);
            break;
        case "E":
            taskToAdd = new Event(details[2], details[3], details[4]);
            break;
        case "D":
            taskToAdd = new Deadline(details[2], details[3]);
        default:
            taskToAdd = null;
            System.out.println("Invalid task");
            return;
        }
        if (details[1].equals("1")) {
            taskToAdd.setDone(true);
        }
        tasks.add(taskToAdd);
        taskCount++;
    }

    public static void saveTaskToFile(Task t) {
        try {
            ensureFileExists();
            appendToFile(FILE_PATH, t.toSaveFormat());
        } catch (IOException e) {
            System.out.println("Failed to save task to file");
            return;
        }
    }

    private static void ensureFileExists() throws IOException {
        File dir = new File(DIRECTORY);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                System.out.println("Failed to create directory");
                return;
            } // Create directory if it doesn't exist
        }

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                System.out.println("Failed to create file");
                return;
            } // Create file if it doesn't exist
        }
    }

    public static void appendToFile(String file_path, String text) throws IOException {
        FileWriter fw = new FileWriter(file_path, true);
        fw.write(text + System.lineSeparator());
        fw.close();
    }


    public static void addTask(Task t) {
        tasks.add(t);
        taskCount++;
        saveTaskToFile(t);
        System.out.println("Successfully added item!");
        System.out.println(tasks.get(taskCount - 1).toString());
        System.out.println("Your list contains " + taskCount + " tasks");
    }

    public static void deleteTask(String input) {
        String[] words = input.split(" ");
        try {
            int taskIndex = Integer.parseInt(words[1]);
            if (taskIndex < 0 || taskIndex > taskCount) {
                throw new InvalidTaskIndexException(taskIndex);
            }
            String taskString = tasks.get(taskIndex - 1).toString();
            tasks.remove(taskIndex - 1);
            taskCount--;
            System.out.println("Alright! I've deleted the task: ");
            System.out.println("    " + taskString);
            System.out.println("Your list now contains " + taskCount + " tasks.");
        } catch (IndexOutOfBoundsException i) {
            System.out.println("Please enter a valid task number.");
        } catch (NumberFormatException n) {
            System.out.println("Oops! Please enter a valid numerical task number.");
        } catch (InvalidTaskIndexException e) {
            System.out.println(e.getMessage());
        }
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
            tasks.get(taskIndex - 1).setDone(true);
        } catch (IndexOutOfBoundsException i) {
            System.out.println("Please enter a valid task number.");
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
            tasks.get(taskIndex - 1).setDone(false);
        } catch (IndexOutOfBoundsException i) {
            System.out.println("Please enter a valid task number.");
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
        case "delete":
            deleteTask(input);
            break;
        default:
            throw new InvalidCommandException();
        }
    }
}
