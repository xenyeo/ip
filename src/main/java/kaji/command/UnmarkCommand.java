package kaji.command;

import kaji.KajiException;
import kaji.Storage;
import kaji.TaskList;
import kaji.Ui;

/**
 * Deals with the unmark command.
 */
public class UnmarkCommand extends Command {
    private int taskId;

    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the unmark command.
     *
     * @param tasklist The task list to operate on.
     * @param ui The UI to interact with user.
     * @param storage The storage to save or load tasks.
     * @throws KajiException If an error occurs during command execution.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws KajiException {
        return tasklist.unmarkTask(taskId, ui);
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
     * Returns the task id of task that is unmarked.
     *
     * @return The task id of task that is unmarked.
     */
    public int getTaskId() {
        return taskId;
    }
}
