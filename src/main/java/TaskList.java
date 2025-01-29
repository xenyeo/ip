import Exceptions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a simple task list that allows adding and viewing tasks.
 */
public class TaskList {
    protected final ArrayList<Task> list = new ArrayList<>();

    /**
     * Adds a new task to the task list. (For User)
     *
     * @param description the task to be added to the task list
     */
    public void addTask(String description, String type) {
        System.out.println(UI.SEPARATOR);
        System.out.println(UI.INDENTATION + "This task has been added:");
        switch (type) {
            case "T" -> list.add(new ToDo(type, false, description));
            case "D" -> {
                String[] descriptionParts = description.split(" /by ");
                list.add(new Deadline(type, false, descriptionParts[0], descriptionParts[1]));
            }
            case "E" -> {
                String[] descriptionParts = description.split(" /from ");
                String[] range = descriptionParts[1].split(" /to ");
                list.add(new Event(type, false, descriptionParts[0], range[0], range[1]));
            }
        }
        System.out.println(UI.INDENTATION + " " + list.get(list.size() - 1).toString());
        String taskWord = (list.size() == 1) ? "task" : "tasks";
        System.out.println(UI.INDENTATION + "There are now " + list.size() + " " + taskWord + " in your list.");
        System.out.println(UI.SEPARATOR);
    }

    /**
     * Adds a new task to the task list. (For System)
     *
     * @param description the task to be added to the task list
     */
    public void addTask(String description) {
        String[] parts = description.split(" \\| ");
        switch (parts[0]) {
            case "T" -> list.add(new ToDo(parts[0], Boolean.getBoolean(parts[1]), parts[2]));
            case "D" -> list.add(new Deadline(parts[0], Boolean.getBoolean(parts[1]), parts[2], parts[3]));
            case "E" -> list.add(new Event(parts[0], Boolean.getBoolean(parts[1]), parts[2], parts[3], parts[4]));
        }
    }

    /**
     * Prints all tasks currently stored in the task list.
     */
    public void printTasks() {
        System.out.println(UI.SEPARATOR);
        System.out.println(UI.INDENTATION + "Here are the current tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(UI.INDENTATION + (i+1) + ". " + list.get(i).toString());
        }
        System.out.println(UI.SEPARATOR);
    }

    /**
     * Marks a task as completed based on the specified task ID.
     *
     * @param taskId the ID of the task to be marked as completed
     */
    public void markTask(int taskId) {
        try {
            if (taskId > list.size() || taskId == 0) {
                throw new TaskIDOutOfBoundException("taskId out of bound");
            } else {
                Task currentTask = list.get(taskId - 1);
                if (currentTask.isDone) {
                    throw new AlreadyMarkedException("Task is already marked");
                } else {
                    currentTask.markTask();
                    System.out.println(UI.SEPARATOR);
                    System.out.println(UI.INDENTATION + "Well Done! This task is now done:");
                    System.out.println(UI.INDENTATION + currentTask.toString());
                    System.out.println(UI.SEPARATOR);
                }
            }
        } catch (TaskIDOutOfBoundException e) {
            String range = list.isEmpty() ? "nil" : "1-" + list.size();
            System.out.println(UI.SEPARATOR);
            System.out.println(UI.INDENTATION + "Task number is out of range.\n"
                    + UI.INDENTATION + "Current range: " + range + " (Given: " + taskId + ")\n"
                    + UI.INDENTATION + "Type list for more information.");
            System.out.println(UI.SEPARATOR);
        } catch (AlreadyMarkedException e) {
            System.out.println(UI.SEPARATOR);
            System.out.println(UI.INDENTATION + "Task is already marked");
            System.out.println(UI.SEPARATOR);
        }
    }

    /**
     * Marks a task as uncompleted based on the specified task ID.
     *
     * @param taskId the ID of the task to be marked as uncompleted
     */
    public void unmarkTask(int taskId) {
        try {
            if (taskId > list.size() || taskId == 0) {
                throw new TaskIDOutOfBoundException("taskId out of bound");
            } else {
                Task currentTask = list.get(taskId - 1);
                if (!currentTask.isDone) {
                    throw new AlreadyUnmarkedException("Task is already unmarked");
                } else {
                    currentTask.unmarkTask();
                    System.out.println(UI.SEPARATOR);
                    System.out.println(UI.INDENTATION + "Well Done! This task is now done:");
                    System.out.println(UI.INDENTATION + currentTask.toString());
                    System.out.println(UI.SEPARATOR);
                }
            }
        } catch (TaskIDOutOfBoundException e) {
            String range = list.isEmpty() ? "nil" : "1-" + list.size();
            System.out.println(UI.SEPARATOR);
            System.out.println(UI.INDENTATION + "Task number is out of range.\n"
                    + UI.INDENTATION + "Current range: " + range + " (Given: " + taskId + ")\n"
                    + UI.INDENTATION + "Type list for more information.");
            System.out.println(UI.SEPARATOR);
        } catch (AlreadyUnmarkedException e) {
            System.out.println(UI.SEPARATOR);
            System.out.println(UI.INDENTATION + "Task is already unmarked");
            System.out.println(UI.SEPARATOR);        }
    }

    /**
     * Deletes a task from the task list based on the specified task ID.
     *
     * @param taskId the ID of the task to be deleted
     */
    public void deleteTask(int taskId) {
        try {
            if (taskId > list.size() || taskId == 0) {
                throw new TaskIDOutOfBoundException("taskId out of bound");
            } else {
                Task currentTask = list.get(taskId - 1);
                list.remove(currentTask);
                String taskWord = (list.size() == 1) ? "task" : "tasks";
                System.out.println(UI.SEPARATOR);
                System.out.println(UI.INDENTATION + "This task has been removed:\n"
                        + UI.INDENTATION + "  " + currentTask.toString() + "\n"
                        + UI.INDENTATION + "There are " + list.size() + " " + taskWord + " left in the list.");
                System.out.println(UI.SEPARATOR);
            }
        } catch (TaskIDOutOfBoundException e) {
            String range = list.isEmpty() ? "nil" : "1-" + list.size();
            System.out.println(UI.SEPARATOR);
            System.out.println(UI.INDENTATION + "Task number is out of range.\n"
                    + UI.INDENTATION + "Current range: " + range + " (Given: " + taskId + ")\n"
                    + UI.INDENTATION + "Type list for more information.");
            System.out.println(UI.SEPARATOR);
        }
    }

    /**
     * Converts a date and time string from one format to another.
     * The input date and time string is expected to be in the format "d/M/yyyy HHmm".
     * The output date and time string is formatted as "MMM dd yyyy HHmm".
     *
     * @param dateTime the date and time string to be converted, expected in the format "d/M/yyyy HHmm"
     * @return the converted date and time string in the format "MMM dd yyyy HHmm"
     */
    public static String convertDateTime(String dateTime) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        LocalDateTime formattedDateTime = LocalDateTime.parse(dateTime, inputFormatter);
        return formattedDateTime.format(outputFormatter);
    }
}