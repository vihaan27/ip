import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final String directory;
    private final Ui ui;

    public Storage(String filePath, String directory, Ui ui) {
        this.filePath = filePath;
        this.directory = directory;
        this.ui = ui;
    }

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

    public ArrayList<Task> addSavedTask(String taskString, ArrayList<Task> tasks) {
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
