/**
 * Represents a simple task list that allows adding and viewing tasks.
 */
public class TaskList {
    private final String[] list = new String[100];
    private int id = 0;
    UI ui = new UI();

    /**
     * Adds a new task to the task list.
     * @param task the task to be added to the task list
     */
    public void addTask(String task) {
        list[id] = task;
        id++;
    }

    /**
     * Prints all tasks currently stored in the task list.
     */
    public void printTasks() {
        ui.printSeparator();
        for (int i = 1; i <= id; i++) {
            ui.printIndentation();
            System.out.print(i + ". " + list[i]);
            System.out.println();
        }
        ui.printSeparator();
    }
}
