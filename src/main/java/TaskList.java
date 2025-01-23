/**
 * Represents a simple task list that allows adding and viewing tasks.
 */
public class TaskList {
    private final Task[] list = new Task[100];
    private int id = 0;

    /**
     * Adds a new task to the task list.
     * @param description the task to be added to the task list
     */
    public void addTask(String description) {
        list[id] = new Task(description);
        id++;
    }

    /**
     * Prints all tasks currently stored in the task list.
     */
    public void printTasks() {
        System.out.println(UI.SEPARATOR);
        System.out.println(UI.INDENTATION + "Here are the current tasks in your list:");
        for (int i = 0; i < id; i++) {
            System.out.println(UI.INDENTATION + (i+1) + ". " + list[i].toString());
        }
        System.out.println(UI.SEPARATOR);
    }

    public void markTask(int taskId) {
        Task currentTask = list[taskId - 1];
        currentTask.markTask();
        System.out.println(UI.SEPARATOR);
        System.out.println(UI.INDENTATION + "Well Done! This task is now done:");
        System.out.println(UI.INDENTATION + currentTask.toString());
        System.out.println(UI.SEPARATOR);
    }

    public void unmarkTask(int taskId) {
        Task currentTask = list[taskId - 1];
        currentTask.unmarkTask();
        System.out.println(UI.SEPARATOR);
        System.out.println(UI.INDENTATION + "Task is now unmarked as not done yet:");
        System.out.println(UI.INDENTATION + currentTask.toString());
        System.out.println(UI.SEPARATOR);
    }
}
