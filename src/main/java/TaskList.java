import java.util.ArrayList;

/**
 * Stores tasks list and handles adding/deletion and manipulation of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;
    private final Storage storage;
    private final Ui ui;

    public TaskList(Storage storage, Ui ui){
        this.tasks = storage.loadSavedTasks();
        taskCount = tasks.size();
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Prints task list or prints message if list is empty.
     */
    public void printList() {
        if (taskCount == 0){
            ui.printMessage("No tasks added yet.");
        } else {
            ui.printList(tasks);
        }
    }

    /**
     * Finds all tasks in list containing a keyword given in input.
     * Prints exception message if keyword is invalid (missing or more than one word).
     * Prints list of tasks found, prints message if no tasks were found.
     * @param input string input from user.
     */
    public void findTasks(String input){
        try {
            String[] details = input.split(" ");
            if (details.length != 2) {
                throw new InvalidKeywordException();
            }

            String keyword = details[1].toLowerCase();
            ArrayList<Task> foundTasks = new ArrayList<Task>();

            for (Task task : tasks) {
                if (task.getDescription().toLowerCase().contains(keyword)) {
                    foundTasks.add(task);
                }
            }

            if (foundTasks.isEmpty()) {
                ui.printMessage("Uh oh! No tasks were found containing the given keyword.");
            } else {
                ui.printMessage("Here are the matching tasks in your list: ");
                ui.printList(foundTasks);
            }
        } catch (ChipException e){
            ui.printChipExceptionMessage(e);
        }
    }

    /**
     * Adds task to tasks list and updates saved tasks in file via Storage.
     * Prints confirmation that task has been added.
     * @param t Task to be added to list
     */
    public void addTask(Task t) {
        tasks.add(t);
        taskCount++;
        storage.saveTasksToFile(tasks);
        ui.confirmTaskAdded(tasks.get(taskCount - 1).toString(), taskCount);
    }

    /**
     * Instantiates and adds to list a toDo object with description given in input.
     * Prints exception message if description is empty.
     * @param input String input from user
     */
    public void addToDo(String input) {
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
            ui.printChipExceptionMessage(e);
        }
    }

    /**
     * Instantiates and adds to list an Event object with given description, from date and to date.
     * Prints exception message if required fields are empty or input does not follow the specified format.
     * @param input String input from user
     */
    public void addEvent(String input) {
        try {
            if (input.trim().equals("event")){
                throw new EmptyDescriptionException();
            }

            String[] details = input.substring(5).split("/");
            if (details.length < 3 || details[0].trim().isEmpty() || details[1].trim().isEmpty() || details[2].trim().isEmpty()){
                throw new InvalidTaskFormatException();
            }

            String description = details[0];
            int fromIndex = details[1].indexOf("from");
            int toIndex = details[2].indexOf("to");
            if (fromIndex == -1 || toIndex == -1){
                throw new InvalidTaskFormatException();
            }
            String from = details[1].substring(fromIndex + 4);
            String to = details[2].substring(toIndex + 2);

            if (from.trim().isEmpty() || to.trim().isEmpty()){
                throw new InvalidTaskFormatException();
            }

            Event event = new Event(description.trim(), from.trim(), to.trim());
            addTask(event);

        } catch (ChipException e){
            ui.printChipExceptionMessage(e);
        }
    }

    /**
     * Instantiates and adds to list a Deadline object with given description and by date.
     * Prints exception if required fields are empty or input does not follow the specified format.
     * @param input String input from user
     */
    public void addDeadline(String input) {
        try {
            if (input.trim().equals("deadline")){
                throw new EmptyDescriptionException();
            }

            String[] details = input.substring(9).split("/by");
            if (details.length < 2 || details[0].trim().isEmpty() || details[1].trim().isEmpty()){
                throw new InvalidTaskFormatException();
            }

            String description = details[0];
            String by = details[1];
            Deadline deadline = new Deadline(description.trim(), by.trim());
            addTask(deadline);
        } catch (ChipException e){
            ui.printChipExceptionMessage(e);
        }
    }

    /**
     * Deletes the task at the index given in input from list.
     * Prints exception messages if the index is invalid (not in the list or not numerical).
     * Prints confirmation if task is successfully deleted.
     * @param input String input from user.
     */
    public void deleteTask(String input) {
        String[] words = input.split(" ");
        try {
            int taskIndex = Integer.parseInt(words[1]);
            if (taskIndex < 0 || taskIndex > taskCount) {
                throw new InvalidTaskIndexException(taskIndex);
            }
            String taskString = tasks.get(taskIndex - 1).toString();
            tasks.remove(taskIndex - 1);
            taskCount--;
            storage.saveTasksToFile(tasks);
            ui.confirmTaskDeleted(taskString, taskCount);
        } catch (IndexOutOfBoundsException i) {
            ui.printInvalidIndexMessage();
        } catch (NumberFormatException n) {
            ui.printNumberFormatMessage();
        } catch (InvalidTaskIndexException e) {
            ui.printChipExceptionMessage(e);
        }
    }

    /**
     * Marks a task as done by a given task index.
     * Prints exception message for an invalid index (not in list or not numerical).
     * Prints confirmation message if successfully marked.
     * @param input string user input.
     */
    public void markTask(String input) {
        String[] words = input.split(" ");
        try {
            int taskIndex = Integer.parseInt(words[1]);
            if (taskIndex < 0 || taskIndex > taskCount) {
                throw new InvalidTaskIndexException(taskIndex);
            }
            tasks.get(taskIndex - 1).setDone(true);
            storage.saveTasksToFile(tasks);
            ui.confirmTaskMarked(tasks.get(taskIndex - 1).toString(), taskIndex);
        } catch (IndexOutOfBoundsException i) {
            ui.printInvalidIndexMessage();
        } catch (NumberFormatException n) {
            ui.printNumberFormatMessage();
        } catch (InvalidTaskIndexException e) {
            ui.printChipExceptionMessage(e);
        }
    }

    /**
     * Unmarks a task as done by a given task index.
     * Prints exception message for an invalid index (not in list or not numerical).
     * Prints confirmation message if successfully unmarked.
     * @param input string user input.
     */
    public void unmarkTask(String input) {
        String[] words = input.split(" ");
        try {
            int taskIndex = Integer.parseInt(words[1]);
            if (taskIndex < 0 || taskIndex > taskCount) {
                throw new InvalidTaskIndexException(taskIndex);
            }
            tasks.get(taskIndex - 1).setDone(false);
            storage.saveTasksToFile(tasks);
            ui.confirmTaskUnmarked(tasks.get(taskIndex - 1).toString(), taskIndex);
        } catch (IndexOutOfBoundsException i) {
            ui.printInvalidIndexMessage();
        } catch (NumberFormatException n) {
            ui.printNumberFormatMessage();
        } catch (InvalidTaskIndexException e) {
            ui.printChipExceptionMessage(e);
        }
    }
}
