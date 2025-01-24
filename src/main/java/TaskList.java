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
    public void addTask(String description, String type) {
        System.out.println(UI.SEPARATOR);
        System.out.println(UI.INDENTATION + "This task has been added:");
        switch (type) {
            case "T" -> list[id] = new ToDo(description, type);
            case "D" -> {
                String[] descriptionParts = description.split(" /by ");
                list[id] = new Deadline(descriptionParts[0], type, descriptionParts[1]);
            }
            case "E" -> {
                String[] descriptionParts = description.split(" /from ");
                String[] range = descriptionParts[1].split(" /to ");
                list[id] = new Event(descriptionParts[0], type, range[0], range[1]);
            }
        }
        System.out.println(UI.INDENTATION + " " + list[id].toString());
        id++;
        String taskWord = (id == 1) ? "task" : "tasks";
        System.out.println(UI.INDENTATION + "There are now " + id + " " + taskWord + " in your list.");
        System.out.println(UI.SEPARATOR);
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

    /**
     * Marks a task as completed based on the specified task ID.
     *
     * @param taskId the ID of the task to be marked as completed
     */
    public void markTask(int taskId) {
        Task currentTask = list[taskId - 1];
        currentTask.markTask();
        System.out.println(UI.SEPARATOR);
        System.out.println(UI.INDENTATION + "Well Done! This task is now done:");
        System.out.println(UI.INDENTATION + currentTask.toString());
        System.out.println(UI.SEPARATOR);
    }

    /**
     * Marks a task as uncompleted based on the specified task ID.
     *
     * @param taskId the ID of the task to be marked as uncompleted
     */
    public void unmarkTask(int taskId) {
        Task currentTask = list[taskId - 1];
        currentTask.unmarkTask();
        System.out.println(UI.SEPARATOR);
        System.out.println(UI.INDENTATION + "Task is now unmarked as not done yet:");
        System.out.println(UI.INDENTATION + currentTask.toString());
        System.out.println(UI.SEPARATOR);
    }
}
