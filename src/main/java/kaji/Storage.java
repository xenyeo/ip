import java.io.*;
import java.util.ArrayList;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks saved in a specific file.
     *
     * @return task list loaded from file.
     * @throws KajiException If an error occurs when loading file
     */
    public ArrayList<Task> load() throws KajiException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            createStorageFile(file);
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    TaskList.addTask(taskList, line);
                }
            } catch (IOException e) {
                throw new KajiException("Error reading file: " + e.getMessage());
            }
        }
        return taskList;
    }

    /**
     * Creates new storage file if it does not exist.
     *
     * @param file The file to be created.
     * @throws KajiException If an error occurs when creating file.
     */
    public void createStorageFile(File file) throws KajiException {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new KajiException("Error creating file: " + e.getMessage());
        }
    }

    /**
     * Saves the tasks to a file.
     *
     * @param taskList the task list containing tasks to be saved
     */
    public void save(TaskList taskList) throws KajiException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList.taskList) {
            String taskString = "";
            if (task.type.equals("T")) {
                taskString = task.type + " | " + task.isDone + " | " + task.description;
            }
            if (task.type.equals("D")) {
                Deadline t = (Deadline) task;
                taskString = task.type + " | " + task.isDone + " | " + task.description + " | " + t.by;
            }
            if (task.type.equals("E")) {
                Event e = (Event) task;
                taskString = task.type + " | " + task.isDone + " | " + task.description + " | " + e.start + " | "
                        + e.end;
            }
            writer.write(taskString);
            writer.newLine();
            }
        } catch (IOException e) {
            throw new KajiException("Error writing to file: " + e.getMessage());
        }
    }
}
