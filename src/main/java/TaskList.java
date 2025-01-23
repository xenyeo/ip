/**
 * Represents a simple task list that allows adding and viewing tasks.
 */
public class TaskList {
    private final Task[] list = new Task[100];
    private int id = 0;
    UI ui = new UI();

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
        ui.printSeparator();
        for (int i = 0; i < id; i++) {
            ui.printIndentation();
            System.out.print((i+1) + ". " + list[i]);
            System.out.println();
        }
        ui.printSeparator();
    }
}
