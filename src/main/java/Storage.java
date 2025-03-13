import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles writing and reading saved tasks from text file.
 */
public class Storage {
    private final String filePath;
    private final String directory;
    private final Ui ui;

    public Storage(String filePath, String directory, Ui ui) {
        this.filePath = filePath;
        this.directory = directory;
        this.ui = ui;
    }

    /**
     * Loads saved tasks from text file.
     * Reconstructs and returns the list of saved task objects.
     * @return list of Task objects loaded from file.
     */
    public ArrayList<Task> loadSavedTasks() {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<Task>();

        if (!file.exists()) {
            ui.printMessage("No saved tasks found.");
            return tasks;
        }

        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                tasks = addSavedTask(s.nextLine(), tasks);
            }
        } catch (FileNotFoundException f) {
            ui.printMessage("No saved tasks found.");
        }

        return tasks;
    }

    /**
     * Interprets saved String format of tasks and accordingly instantiates tasks and adds it to given list.
     * @param taskString string format of task to interpret and load.
     * @param tasks list of tasks that new task must be added to.
     * @return task list with new task added.
     */
    private ArrayList<Task> addSavedTask(String taskString, ArrayList<Task> tasks) {
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
            break;
        default:
            taskToAdd = null;
            ui.printMessage("Invalid task in saved file");
            return tasks;
        }
        if (details[1].equals("1")) {
            taskToAdd.setDone(true);
        }
        tasks.add(taskToAdd);
        return tasks;
    }

    /**
     * Writes all tasks in the given list to text file in the specified String format.
     * @param tasks list of tasks to be written to file.
     */
    public void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            ensureFileExists();
            FileWriter fw = new FileWriter(filePath);
            for (Task task: tasks){
                fw.write(task.toSaveFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            ui.printMessage("Failed to save tasks to file");
        }
    }

    /**
     * Ensures required directory or file exist and creates them if not.
     * @throws IOException
     */
    private void ensureFileExists() throws IOException {
        File dir = new File(directory);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                ui.printMessage("Failed to create directory");
                return;
            }
        }

        File file = new File(filePath);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                ui.printMessage("Failed to create file");
                return;
            }
        }
    }
}
