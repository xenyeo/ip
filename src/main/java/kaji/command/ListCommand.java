package kaji.command;

import kaji.KajiException;
import kaji.Storage;
import kaji.TaskList;
import kaji.Ui;

/**
 * Deals with the list command.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command.
     *
     * @param tasklist The task list to operate on.
     * @param ui The UI to interact with user.
     * @param storage The storage to save or load tasks.
     * @throws KajiException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws KajiException {
        ui.showTaskList(tasklist);
    }

    /**
     * Indicates that this command is not an exit command.
     *
     * @return false, as this command is not an exit command.
     */
    @Override
    public boolean isExit() { return false; }
}
