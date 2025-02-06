package kaji.command;

import kaji.KajiException;
import kaji.Storage;
import kaji.TaskList;
import kaji.Ui;

/**
 * Deals with the add command.
 */
public class AddCommand extends Command {
    private String type;
    private String description;

    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * Executes the add command.
     *
     * @param tasklist The task list to operate on.
     * @param ui The UI to interact with user.
     * @param storage The storage to save or load tasks.
     * @throws KajiException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws KajiException {
        tasklist.addTask(type, description, ui);
    }

    /**
     * Indicates that this command is not an exit command.
     *
     * @return false, as this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns a string representation of the command.
     *
     * @return A string representation of the command.
     */
    public String getDescription() {
        return description;
    }

}
