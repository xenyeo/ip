package kaji.command;

import kaji.KajiException;
import kaji.Storage;
import kaji.TaskList;
import kaji.Ui;

/**
 * Provides a template for executing commands.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasklist The task list to operate on.
     * @param ui The UI to interact with user.
     * @param storage The storage to save or load tasks.
     * @throws KajiException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasklist, Ui ui, Storage storage) throws KajiException;

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
