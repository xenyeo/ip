package kaji;

import java.util.ArrayList;

import kaji.task.Deadline;
import kaji.task.Event;
import kaji.task.Task;
import kaji.task.ToDo;

/**
 * Contains the task list with operations to manipulate tasks in the list.
 */
public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a new task to the task list. (For System)
     *
     * @param description the task to be added to the task list
     */
    public static void addTask(ArrayList<Task> taskList, String description) throws KajiException {
        String[] parts = description.split(" \\| ");
        assert !(parts.length == 2) : "Task description is in the wrong format";
        switch (parts[0]) {
            case "T" -> taskList.add(new ToDo(parts[0], Boolean.parseBoolean(parts[1]), parts[2]));
            case "D" -> taskList.add(new Deadline(parts[0], Boolean.parseBoolean(parts[1]), parts[2], parts[3]));
            case "E" -> taskList.add(new Event(parts[0], Boolean.parseBoolean(parts[1]), parts[2], parts[3], parts[4]));
            default -> throw new KajiException("Invalid task type: " + parts[0]);
        }
        System.out.println(taskList);
    }

    /**
     * Adds a new task to the task list. (For User)
     *
     * @param description the task to be added to the task list
     */
    public String addTask(String type, String description, Ui ui) throws KajiException {
        switch (type) {
            case "T" -> taskList.add(new ToDo(type, false, description));
            case "D" -> {
                String[] descriptionParts = description.split(" /by ");
                taskList.add(new Deadline(type, false, descriptionParts[0], descriptionParts[1]));
            }
            case "E" -> {
                String[] descriptionParts = description.split(" /from ");
                String[] range = descriptionParts[1].split(" /to ");
                taskList.add(new Event(type, false, descriptionParts[0], range[0], range[1]));
            }
            default -> throw new KajiException("Invalid task type: " + type);
        }
        return ui.showTaskAdded(this.taskList);
    }

    /**
     * Deletes a task from the task list based on the specified task ID.
     *
     * @param taskId the ID of the task to be deleted
     */
    public String deleteTask(int taskId, Ui ui) throws KajiException {
        if (taskId > taskList.size() || taskId == 0) {
            throw new KajiException("Invalid task id");
        } else {
            Task currentTask = taskList.get(taskId - 1);
            taskList.remove(currentTask);
            return ui.showTaskDeleted(taskList, currentTask);
        }
    }

    /**
     * Marks a task as completed based on the specified task ID.
     *
     * @param taskId the ID of the task to be marked as completed
     */
    public String markTask(int taskId, Ui ui) throws KajiException {
        if (taskId > taskList.size() || taskId == 0) {
            throw new KajiException("Invalid task id");
        } else {
            Task currentTask = taskList.get(taskId - 1);
            if (currentTask.isDone()) {
                throw new KajiException("Task is already marked");
            } else {
                currentTask.markTask();
                return ui.showMarkedTask(currentTask);
            }
        }
    }

    /**
     * Marks a task as uncompleted based on the specified task ID.
     *
     * @param taskId the ID of the task to be marked as uncompleted
     */
    public String unmarkTask(int taskId, Ui ui) throws KajiException {
        if (taskId > taskList.size() || taskId == 0) {
            throw new KajiException("Invalid task id");
        } else {
            Task currentTask = taskList.get(taskId - 1);
            if (!currentTask.isDone()) {
                throw new KajiException("Task is already unmarked");
            } else {
                currentTask.unmarkTask();
                return ui.showUnmarkedTask(currentTask);
            }
        }
    }

    /**
     * Finds and displays tasks that match the given pattern.
     *
     * @param pattern the pattern to search for in task descriptions
     * @param ui the user interface object for displaying matching tasks
     * @throws KajiException if an error occurs while processing the tasks
     */
    public String findTasks(String pattern, Ui ui) throws KajiException {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(pattern)) {
                foundTasks.add(task);
            }
        }
        if (!foundTasks.isEmpty()) {
            return ui.showMatchingTasks(foundTasks);
        } else {
            return ui.showNoMatchingTasks();
        }
    }
}
