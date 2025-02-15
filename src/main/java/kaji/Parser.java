package kaji;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import kaji.command.AddCommand;
import kaji.command.Command;
import kaji.command.DeleteCommand;
import kaji.command.ExitCommand;
import kaji.command.FindCommand;
import kaji.command.InvalidCommand;
import kaji.command.ListCommand;
import kaji.command.MarkCommand;
import kaji.command.TagCommand;
import kaji.command.UnmarkCommand;
import kaji.command.UntagCommand;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    /**
     * Parses the full command string and returns the corresponding command object.
     *
     * @param fullCommand The full command string entered by the user.
     * @return The Command object corresponding to the parsed command.
     * @throws KajiException If the command type is unknown or if there is an error parsing the command.
     */
    public static Command parse(String fullCommand) throws KajiException {
        String[] commandParts = fullCommand.split(" ", 2);
        String commandType = commandParts[0];

        return switch (commandType) {
            case "todo" -> parseTodoCommand(commandParts);
            case "deadline" -> parseDeadlineCommand(commandParts);
            case "event" -> parseEventCommand(commandParts);
            case "mark" -> parseMarkCommand(commandParts);
            case "unmark" -> parseUnmarkCommand(commandParts);
            case "list" -> new ListCommand();
            case "delete" -> parseDeleteCommand(commandParts);
            case "bye" -> new ExitCommand();
            case "find" -> parseFindCommand(commandParts);
            case "tag" -> parseTagCommand(commandParts);
            case "untag" -> parseUntagCommand(commandParts);
            default -> new InvalidCommand();
        };
    }

    /**
     * Parses the todo command and returns the corresponding AddCommand object.
     *
     * @param commandParts The parts of the command string.
     * @return The AddCommand object for the todo command.
     * @throws KajiException If the todo command is invalid.
     */
    private static Command parseTodoCommand(String[] commandParts) throws KajiException {
        if (commandParts.length == 1) {
            throw new KajiException("Invalid todo command");
        }
        return new AddCommand("T", commandParts[1]);
    }

    /**
     * Parses the deadline command and returns the corresponding AddCommand object.
     *
     * @param commandParts The parts of the command string.
     * @return The AddCommand object for the deadline command.
     * @throws KajiException If the deadline command is invalid.
     */
    private static Command parseDeadlineCommand(String[] commandParts) throws KajiException {
        String validPattern = ".+\\s+/by\\s+.+";
        if (commandParts.length == 1 || !commandParts[1].matches(validPattern)) {
            throw new KajiException("Invalid deadline command");
        }
        try {
            String[] parts = commandParts[1].split(" /by ");
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm]");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            String formattedDateTime = LocalDateTime.parse(parts[1], inputFormatter).format(outputFormatter);
            return new AddCommand("D", parts[0] + " /by " + formattedDateTime);
        } catch (DateTimeParseException e) {
            throw new KajiException("Invalid date & time format");
        }
    }

    /**
     * Parses the event command and returns the corresponding AddCommand object.
     *
     * @param commandParts The parts of the command string.
     * @return The AddCommand object for the event command.
     * @throws KajiException If the event command is invalid.
     */
    private static Command parseEventCommand(String[] commandParts) throws KajiException {
        String validPattern = ".+\\s+/from\\s+.+\\s+/to.+";
        if (commandParts.length == 1 || !commandParts[1].matches(validPattern)) {
            throw new KajiException("Invalid event command");
        }
        return new AddCommand("E", commandParts[1]);
    }

    /**
     * Parses the mark command and returns the corresponding MarkCommand object.
     *
     * @param commandParts The parts of the command string.
     * @return The MarkCommand object for the mark command.
     * @throws KajiException If the mark command is invalid.
     */
    private static Command parseMarkCommand(String[] commandParts) throws KajiException {
        if (commandParts.length == 1) {
            throw new KajiException("Invalid mark command\nUsage: mark <task>");
        } else if (!commandParts[1].matches("\\d+")) {
            throw new KajiException("Invalid task list number");
        }
        return new MarkCommand(Integer.parseInt(commandParts[1]));
    }

    /**
     * Parses the unmark command and returns the corresponding UnmarkCommand object.
     *
     * @param commandParts The parts of the command string.
     * @return The UnmarkCommand object for the unmark command.
     * @throws KajiException If the unmark command is invalid.
     */
    private static Command parseUnmarkCommand(String[] commandParts) throws KajiException {
        if (commandParts.length == 1) {
            throw new KajiException("Invalid unmark command\nUsage: unmark <task>");
        } else if (!commandParts[1].matches("\\d+")) {
            throw new KajiException("Invalid task list number");
        }
        return new UnmarkCommand(Integer.parseInt(commandParts[1]));
    }

    /**
     * Parses the delete command and returns the corresponding DeleteCommand object.
     *
     * @param commandParts The parts of the command string.
     * @return The DeleteCommand object for the delete command.
     * @throws KajiException If the delete command is invalid.
     */
    private static Command parseDeleteCommand(String[] commandParts) throws KajiException {
        if (commandParts.length == 1) {
            throw new KajiException("Invalid delete command");
        } else if (!commandParts[1].matches("\\d+")) {
            throw new KajiException("Invalid task list number");
        }
        return new DeleteCommand(Integer.parseInt(commandParts[1]));
    }

    /**
     * Parses the find command and return the corresponding FindCommand object.
     *
     * @param commandParts The parts of the command string.
     * @return FindCommand object for the find command.
     * @throws KajiException If the find command is invalid.
     */
    private static Command parseFindCommand(String[] commandParts) throws KajiException {
        if (commandParts.length == 1) {
            throw new KajiException("Invalid find command");
        }
        return new FindCommand(commandParts[1]);
    }


    /**
     * Parses the tag command and return the corresponding TagCommand object.
     *
     * @param commandParts The parts of the command string.
     * @return TagCommand object for the tag command.
     * @throws KajiException If the tag command is invalid.
     */
    private static Command parseTagCommand(String[] commandParts) throws KajiException {
        if (commandParts.length == 1) {
            throw new KajiException("Invalid tag command");
        }

        String[] parts = commandParts[1].split(" ");

        if (!parts[0].matches("\\d+")) {
            throw new KajiException("Invalid task list number");
        } else if (parts.length == 1) {
            throw new KajiException("Invalid tag command");
        }

        return new TagCommand(Integer.parseInt(parts[0]), parts[1]);
    }

    /**
     * Parses the untag command and return the corresponding UntagCommand object.
     *
     * @param commandParts The parts of the command string.
     * @return UntagCommand object for the untag command.
     * @throws KajiException If the untag command is invalid.
     */
    private static Command parseUntagCommand(String[] commandParts) throws KajiException {
        if (commandParts.length == 1) {
            throw new KajiException("Invalid untag command");
        }

        String[] parts = commandParts[1].split(" ");

        if (!parts[0].matches("\\d+")) {
            throw new KajiException("Invalid task list number");
        } else if (parts.length == 1) {
            throw new KajiException("Invalid tag command");
        }

        return new UntagCommand(Integer.parseInt(parts[0]), parts[1]);
    }
}
