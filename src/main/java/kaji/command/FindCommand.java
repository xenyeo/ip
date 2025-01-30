package kaji.command;

import kaji.KajiException;
import kaji.Storage;
import kaji.TaskList;
import kaji.Ui;

/**
 * Deals with the find command.
 */
public class FindCommand extends Command {
    String pattern;

    public FindCommand(String pattern) {
        this.pattern = pattern;
    }

    /**
     * Executes the find command.
     *
     * @param tasklist The task list to operate on.
     * @param ui The UI to interact with user.
     * @param storage The storage to save or load tasks.
     * @throws KajiException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws KajiException {
        tasklist.findTasks(pattern, ui);
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
}
