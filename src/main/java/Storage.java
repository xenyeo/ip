import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles the storage functionalities for the KAJI virtual assistant.
 */
public class Storage {
    private static final String FILE_PATH = "./data/kaji.txt";

    public void loadTasks(TaskList taskList) {

    }

    /**
     * Saves the tasks stored in the given task list to a file.
     *
     * @param taskList the task list containing tasks to be saved
     */
    public void saveTasks(TaskList taskList) {
        for (Task task : taskList.list) {
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
                taskString = task.type + " | " + task.isDone + " | " + task.description + " | " + e.start + " | " + e.end;
            }
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
                writer.write(taskString);
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }
    }
}
