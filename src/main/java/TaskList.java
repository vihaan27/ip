import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;
    private Storage storage;

    public TaskList(Storage storage){
        this.tasks = storage.loadSavedTasks();
        taskCount = tasks.size();
        this.storage = storage;
    }

    public void printList() {
        if (taskCount == 0){
            System.out.println("No tasks added yet.");
            return;
        }

        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());

        }
    }

    public void addTask(Task t) {
        tasks.add(t);
        taskCount++;
        storage.saveTasksToFile(tasks);
        System.out.println("Successfully added item!");
        System.out.println(tasks.get(taskCount - 1).toString());
        System.out.println("Your list contains " + taskCount + " tasks");
    }

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
            System.out.println("Alright! I've deleted the task: ");
            System.out.println("-> " + taskString);
            System.out.println("Your list now contains " + taskCount + " tasks.");
        } catch (IndexOutOfBoundsException i) {
            System.out.println("Please enter a valid task number.");
        } catch (NumberFormatException n) {
            System.out.println("Oops! Please enter a valid numerical task number.");
        } catch (InvalidTaskIndexException e) {
            System.out.println(e.getMessage());
        }
    }

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
            System.out.println(e.getMessage());
        }
    }

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
            System.out.println(e.getMessage());
        }
    }

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
            System.out.println(e.getMessage());
        }
    }

    public void markTask(String input) {
        String[] words = input.split(" ");
        try {
            int taskIndex = Integer.parseInt(words[1]);
            if (taskIndex < 0 || taskIndex > taskCount) {
                throw new InvalidTaskIndexException(taskIndex);
            }
            tasks.get(taskIndex - 1).setDone(true);
            storage.saveTasksToFile(tasks);
            System.out.println("Alright! I've marked task number " + taskIndex + ":");
            System.out.println("-> " + tasks.get(taskIndex - 1).toString());
        } catch (IndexOutOfBoundsException i) {
            System.out.println("Please enter a valid task number.");
        } catch (NumberFormatException n) {
            System.out.println("Oops! Please enter a valid numerical task number.");
        } catch (InvalidTaskIndexException e) {
            System.out.println(e.getMessage());
        }
    }

    public void unmarkTask(String input) {
        String[] words = input.split(" ");
        try {
            int taskIndex = Integer.parseInt(words[1]);
            if (taskIndex < 0 || taskIndex > taskCount) {
                throw new InvalidTaskIndexException(taskIndex);
            }
            tasks.get(taskIndex - 1).setDone(false);
            storage.saveTasksToFile(tasks);
            System.out.println("Alright! I've unmarked task number " + taskIndex + ":");
            System.out.println("-> " + tasks.get(taskIndex - 1).toString());
        } catch (IndexOutOfBoundsException i) {
            System.out.println("Please enter a valid task number.");
        } catch (NumberFormatException n) {
            System.out.println("Oops! Please enter a valid numerical task number.");
        } catch (InvalidTaskIndexException e) {
            System.out.println(e.getMessage());
        }
    }
}
