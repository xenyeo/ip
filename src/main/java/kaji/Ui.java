package kaji;

import kaji.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private static final String INDENTATION = "    ";
    private static final String SEPARATOR = INDENTATION + "_______________________________________________________";
    // Logo design adapted from https://patorjk.com/software/taag
    private static final String LOGO = INDENTATION
            + "    )               (\n"
            + "      ( /(   (           )\\ )\n"
            + "      )\\())  )\\      (  (()/(\n"
            + "     ((_)\\((((_)(    )\\  /(_))\n"
            + "     _ ((_))\\ _ )\\  ((_)(_))\n"
            + "    | |/ / (_) \\(_)_ | ||_ _|\n"
            + "    | ' (   / _ \\ | || | | |\n"
            + "    |_|\\_\\ /_/ \\_\\ \\__/ |___|";
    private static final String GREET = INDENTATION + "Hello! I'm KAJI, your personal virtual assistant.\n"
            + INDENTATION + "How can i help you today?";
    private static final String EXIT = INDENTATION + "Thank you for chatting with me!\n"
            + INDENTATION + "See you again soon!";

    /**
     * Displays the welcome page.
     */
    public void showWelcome() {
        System.out.println(LOGO);
        System.out.println(SEPARATOR);
        System.out.println(GREET);
        System.out.println(SEPARATOR);
    }

    /**
     * Displays a line as separator.
     */
    public void showLine() {
        System.out.println(SEPARATOR);
    }

    /**
     * Displays the exit page.
     */
    public void showExit() {
        System.out.println(SEPARATOR);
        System.out.println(EXIT);
        System.out.println(SEPARATOR);

    }

    /**
     * Displays error for storage load fail.
     */
    public void showLoadingError() {
        System.out.println(SEPARATOR);
        System.out.println("File could not be loaded...");
        System.out.println(SEPARATOR);
    }

    /**
     * Displays the error message caught by exceptions
     *
     * @param message the error message to be displayed
     */
    public void showError(String message) {
        System.out.println(SEPARATOR);
        System.out.println(Ui.INDENTATION + message);
        System.out.println(SEPARATOR);
    }

    /**
     * Displays task successfully added to task list.
     *
     * @param taskList the task list containing task added.
     */
    public void showTaskAdded(ArrayList<Task> taskList) {
        showLine();
        System.out.println(Ui.INDENTATION + "This task has been added:");
        System.out.println(Ui.INDENTATION + " " + taskList.get(taskList.size() - 1).toString());
        String taskWord = (taskList.size() == 1) ? "task" : "tasks";
        System.out.println(Ui.INDENTATION + "There are now " + taskList.size() + " " + taskWord + " in your list.");
        showLine();
    }

    /**
     * Displays task successfully deleted from task list.
     *
     * @param taskList the task list without the deleted task.
     * @param taskDeleted the task that is deleted.
     */
    public void showTaskDeleted(ArrayList<Task> taskList, Task taskDeleted) {
        String taskWord = (taskList.size() == 1) ? "task" : "tasks";
        showLine();
        System.out.println(Ui.INDENTATION + "This task has been removed:\n"
                + Ui.INDENTATION + "  " + taskDeleted.toString() + "\n"
                + Ui.INDENTATION + "There are " + taskList.size() + " " + taskWord + " left in the list.");
        showLine();
    }

    /**
     * Displays task successfully marked in the task list.
     *
     * @param taskList the task list containing the marked task.
     * @param markedTask the task that is marked.
     */
    public void showMarkedTask(ArrayList<Task> taskList, Task markedTask) {
        showLine();
        System.out.println(Ui.INDENTATION + "Well Done! This task is now done:");
        System.out.println(Ui.INDENTATION + markedTask.toString());
        showLine();
    }

    /**
     * Displays task successfully unmarked in the task list.
     *
     * @param taskList the task list containing the unmarked task.
     * @param unmarkedTask the task that is unmarked.
     */
    public void showUnmarkedTask(ArrayList<Task> taskList, Task unmarkedTask) {
        showLine();
        System.out.println(Ui.INDENTATION + "Well Done! This task is now done:");
        System.out.println(Ui.INDENTATION + unmarkedTask.toString());
        showLine();
    }

    /**
     * Displays all the tasks in the current task list.
     *
     * @param taskList the current task list
     */
    public void showTaskList(TaskList taskList) {
        showLine();
        System.out.println(Ui.INDENTATION + "Here are the current tasks in your list:");
        for (int i = 0; i < taskList.taskList.size(); i++) {
            System.out.println(Ui.INDENTATION + (i+1) + ". " + taskList.taskList.get(i).toString());
        }
        System.out.println(Ui.SEPARATOR);
    }

    /**
     * Reads the command entered by user.
     *
     * @return user command in the form of a String.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
