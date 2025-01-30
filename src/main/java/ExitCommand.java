/**
 * Deals with the exit command.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     *
     * @param tasklist The task list to operate on.
     * @param ui The UI to interact with user.
     * @param storage The storage to save or load tasks.
     * @throws KajiException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws KajiException {
        storage.save(tasklist);
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return true, as this command is an exit command.
     */
    @Override
    public boolean isExit() { return true; }
}
