import Exceptions.FileLoadException;
import Exceptions.InvalidDateException;
import Exceptions.InvalidFileFormatException;

import java.io.*;
import java.util.ArrayList;

/**
 * Handles the storage functionalities for the KAJI virtual assistant.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileLoadException {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new FileLoadException("Error creating file: " + e.getMessage());
            }
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                ArrayList<Task> taskList = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
                    taskList.add(line);
                }
                return taskList;
            } catch (IOException e) {
                throw new FileLoadException("Error reading file: " + e.getMessage());
            }
        }
    }

//    /**
//     * Loads the tasks to the given task list from a file.
//     *
//     * @param taskList the task list for tasks to be loaded
//     */
//    public void loadTasks(TaskList taskList) {
//        File file = new File(filePath);
//        if (!file.exists()) {
//            try {
//                file.getParentFile().mkdirs();
//                file.createNewFile();
//            } catch (IOException e) {
//                System.out.println("Error creating file: " + e.getMessage());
//            }
//        } else {
//            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    try {
//                        if (isValidTaskFormat(line)) {
//                            taskList.addTask(line);
//                        } else {
//                            throw new InvalidFileFormatException("Invalid file format");
//                        }
//                    } catch (InvalidFileFormatException e) {
//                        System.out.println(Ui.INDENTATION + "Data file, 'tasks.txt', is corrupted.");
//                    } catch (InvalidDateException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            } catch (IOException e) {
//                System.out.println("Error reading file: " + e.getMessage());
//            }
//        }
//    }

    /**
     * Saves the tasks stored in the given task list to a file.
     *
     * @param taskList the task list containing tasks to be saved
     */
    public void saveTasks(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
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

    /**
     * Validates whether the given task line is in a correct and valid format.
     * A valid task format includes specific types (T, D, E), a valid completion status,
     * and the appropriate number of segments based on the task type.
     *
     * @param taskLine the string representation of a task to be validated
     * @return true if the task line matches the expected format, false otherwise
     */
    private boolean isValidTaskFormat(String taskLine) {
        String[] parts = taskLine.split(" \\| ");
        if (parts.length < 3) {
            return false;
        }
        String type = parts[0];
        if (!(type.equals("T") || type.equals("D") || type.equals("E"))) {
            return false;
        }
        if (!(parts[1].equals("true") || parts[1].equals("false"))) {
            return false;
        }
        if (type.equals("T") && parts.length != 3) {
            return false;
        }
        if (type.equals("D") && parts.length != 4) {
            return false;
        }
        if (type.equals("E") && parts.length != 5) {
            return false;
        }
        return true;
    }
}
