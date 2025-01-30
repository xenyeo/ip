/**
 * Deals with making sense of the user command.
 */
public class Parser {
    /**
     * Parses the full command string and returns the corresponding Command object.
     *
     * @param fullCommand The full command string entered by the user.
     * @return The Command object corresponding to the parsed command.
     * @throws KajiException If the command type is unknown or if there is an error parsing the command.
     */
    public static Command parse(String fullCommand) throws KajiException {
        String[] commandParts = fullCommand.split(" ", 2);
        String commandType = commandParts[0];

        switch (commandType) {
        case "todo": return new AddCommand("T", commandParts[1]);
        case "deadline": return new AddCommand("D", commandParts[1]);
        case "event": return new AddCommand("E", commandParts[1]);
        case "mark": return new MarkCommand(Integer.parseInt(commandParts[1]));
        case "unmark": return new UnmarkCommand(Integer.parseInt(commandParts[1]));
        case "list": return new ListCommand();
        case "delete": return new DeleteCommand(Integer.parseInt(commandParts[1]));
        case "bye": return new ExitCommand();
        default: throw new KajiException("Unknown command: " + commandType);
        }
    }
}
