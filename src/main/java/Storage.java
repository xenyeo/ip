import java.io.*;

/**
 * Handles the storage functionalities for the KAJI virtual assistant.
 */
public class Storage {
    private static final String FILE_PATH = "./data/kaji.txt";

    /**
     * Loads the tasks to the given task list from a file.
     *
     * @param taskList the task list for tasks to be loaded
     */
    public void loadTasks(TaskList taskList) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
                String line;
                while ((line = reader.readLine()) != null) {
                    taskList.addTask(line);
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
    }

    /**
     * Saves the tasks stored in the given task list to a file.
     *
     * @param taskList the task list containing tasks to be saved
     */
    public void saveTasks(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
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
            writer.write(taskString);
            writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
