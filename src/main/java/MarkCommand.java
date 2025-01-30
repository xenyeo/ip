/**
 * Deals with the mark command.
 */
public class MarkCommand extends Command {
    private int taskId;

    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the mark command.
     *
     * @param tasklist The task list to operate on.
     * @param ui The UI to interact with user.
     * @param storage The storage to save or load tasks.
     * @throws KajiException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws KajiException {
        tasklist.markTask(taskId, ui);
    }

    /**
     * Indicates that this command is not an exit command.
     *
     * @return false, as this command is not an exit command.
     */
    @Override
    public boolean isExit() { return false; }
}
