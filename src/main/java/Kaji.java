import Exceptions.*;

import java.util.Scanner;

public class Kaji {
    public static void main(String[] args) {
        UI ui = new UI();
        TaskList taskList = new TaskList();
        Storage storage = new Storage();

        ui.start();
        storage.loadTasks(taskList);

        Scanner sc = new Scanner(System.in);
        String command = "";
        while (!command.equals("bye")) {
            try {
                command = sc.nextLine();
                String[] commandParts = command.split(" ", 2);
                String action = commandParts[0];

                // TODO: Add "help"
                switch (action) {
                    case "bye" -> ui.end();
                    case "list" -> taskList.printTasks();
                    case "mark" -> {
                        if (commandParts.length == 1) {
                            throw new InvalidMarkCommandException("Invalid mark command");
                        } else if (!commandParts[1].matches("\\d+")) {
                            throw new InvalidTaskListNumberException("Invalid task list number");
                        } else {
                            taskList.markTask(Integer.parseInt(commandParts[1]));
                        }
                    }
                    case "unmark" -> {
                        if (commandParts.length == 1) {
                            throw new InvalidUnmarkCommandException("Invalid unmark command");
                        } else if (!commandParts[1].matches("\\d+")) {
                            throw new InvalidTaskListNumberException("Invalid task list number");
                        } else {
                            taskList.unmarkTask(Integer.parseInt(commandParts[1]));
                        }
                    }
                    case "todo" -> {
                        if (commandParts.length == 1) {
                            throw new InvalidTodoCommandException("Invalid todo command");
                        } else {
                            taskList.addTask(commandParts[1], "T");
                        }
                    }
                    case "deadline" -> {
                        String validPattern = ".+\\s+/by\\s+.+";
                        if (commandParts.length == 1 || !commandParts[1].matches(validPattern)){
                            throw new InvalidDeadlineCommandException("Invalid deadline command");
                        } else {
                            taskList.addTask(commandParts[1], "D");
                        }
                    }
                    case "event" -> {
                        String validPattern = ".+\\s+/from\\s+.+\\s+/to.+";
                        if (commandParts.length == 1 || !commandParts[1].matches(validPattern)) {
                            throw new InvalidEventCommandException("Invalid event command");
                        } else {
                            taskList.addTask(commandParts[1], "E");
                        }
                    }
                    case "delete" -> {
                        if (commandParts.length == 1) {
                            throw new InvalidDeleteCommandException("Invalid mark command");
                        } else if (!commandParts[1].matches("\\d+")) {
                            throw new InvalidTaskListNumberException("Invalid task list number");
                        } else {
                            taskList.deleteTask(Integer.parseInt(commandParts[1]));
                        }
                    }
                    default -> throw new InvalidCommandException("Invalid Command: " + command);
                }
            } catch (InvalidCommandException e) {
                System.out.println(UI.SEPARATOR);
                System.out.println(UI.INDENTATION + "Invalid Command: Type help for command list");
                System.out.println(UI.SEPARATOR);
            } catch (InvalidTodoCommandException e) {
                System.out.println(UI.SEPARATOR);
                System.out.println(UI.INDENTATION + "Usage: todo <task>");
                System.out.println(UI.SEPARATOR);
            } catch (InvalidDeadlineCommandException e) {
                System.out.println(UI.SEPARATOR);
                System.out.println(UI.INDENTATION + "Usage: deadline <task> /by <date/time>");
                System.out.println(UI.SEPARATOR);
            } catch (InvalidEventCommandException e) {
                System.out.println(UI.SEPARATOR);
                System.out.println(UI.INDENTATION + "Usage: event <task> /from <start> /to <end>");
                System.out.println(UI.SEPARATOR);
            } catch (InvalidMarkCommandException e) {
                System.out.println(UI.SEPARATOR);
                System.out.println(UI.INDENTATION + "Usage: mark <task number>\n"
                        + UI.INDENTATION + "Type list for task numbers");
                System.out.println(UI.SEPARATOR);
            } catch (InvalidUnmarkCommandException e) {
                System.out.println(UI.SEPARATOR);
                System.out.println(UI.INDENTATION + "Usage: unmark <task number>\n"
                        + UI.INDENTATION + "Type list for task numbers");
                System.out.println(UI.SEPARATOR);
            } catch (InvalidTaskListNumberException e) {
                System.out.println(UI.SEPARATOR);
                System.out.println(UI.INDENTATION + "Task number needs to be an integer");
                System.out.println(UI.SEPARATOR);
            } catch (InvalidDeleteCommandException e) {
                System.out.println(UI.SEPARATOR);
                System.out.println(UI.INDENTATION + "Usage: delete <task number>\n"
                        + UI.INDENTATION + "Type list for task numbers");                System.out.println(UI.SEPARATOR);
            }
        }

        storage.saveTasks(taskList);

        sc.close();
    }
}
