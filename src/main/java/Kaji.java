import Exceptions.*;

import java.util.Scanner;

public class Kaji {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Kaji(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileLoadException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {

    }

    public static void main(String[] args) {
        new Kaji("data/tasks.txt").run();
    }
//    public static void main(String[] args) {
//        Ui ui = new Ui();
//        TaskList taskList = new TaskList();
//        Storage storage = new Storage();
//
//        ui.start();
//        storage.loadTasks(taskList);
//
//        Scanner sc = new Scanner(System.in);
//        String command = "";
//        while (!command.equals("bye")) {
//            try {
//                command = sc.nextLine();
//                String[] commandParts = command.split(" ", 2);
//                String action = commandParts[0];
//
//                // TODO: Add "help"
//                switch (action) {
//                case "bye" -> ui.end();
//                case "list" -> taskList.printTasks();
//                case "mark" -> {
//                    if (commandParts.length == 1) {
//                        throw new InvalidMarkCommandException("Invalid mark command");
//                    } else if (!commandParts[1].matches("\\d+")) {
//                        throw new InvalidTaskListNumberException("Invalid task list number");
//                    } else {
//                        taskList.markTask(Integer.parseInt(commandParts[1]));
//                    }
//                }
//                case "unmark" -> {
//                    if (commandParts.length == 1) {
//                        throw new InvalidUnmarkCommandException("Invalid unmark command");
//                    } else if (!commandParts[1].matches("\\d+")) {
//                        throw new InvalidTaskListNumberException("Invalid task list number");
//                    } else {
//                        taskList.unmarkTask(Integer.parseInt(commandParts[1]));
//                    }
//                }
//                case "todo" -> {
//                    if (commandParts.length == 1) {
//                        throw new InvalidTodoCommandException("Invalid todo command");
//                    } else {
//                        taskList.addTask(commandParts[1], "T");
//                    }
//                }
//                case "deadline" -> {
//                    String validPattern = ".+\\s+/by\\s+.+";
//                    if (commandParts.length == 1 || !commandParts[1].matches(validPattern)){
//                        throw new InvalidDeadlineCommandException("Invalid deadline command");
//                    } else {
//                        taskList.addTask(commandParts[1], "D");
//                    }
//                }
//                case "event" -> {
//                    String validPattern = ".+\\s+/from\\s+.+\\s+/to.+";
//                    if (commandParts.length == 1 || !commandParts[1].matches(validPattern)) {
//                        throw new InvalidEventCommandException("Invalid event command");
//                    } else {
//                        taskList.addTask(commandParts[1], "E");
//                    }
//                }
//                case "delete" -> {
//                    if (commandParts.length == 1) {
//                        throw new InvalidDeleteCommandException("Invalid mark command");
//                    } else if (!commandParts[1].matches("\\d+")) {
//                        throw new InvalidTaskListNumberException("Invalid task list number");
//                    } else {
//                        taskList.deleteTask(Integer.parseInt(commandParts[1]));
//                    }
//                }
//                default -> throw new InvalidCommandException("Invalid Command: " + command);
//            }
//            } catch (InvalidCommandException e) {
//                System.out.println(Ui.SEPARATOR);
//                System.out.println(Ui.INDENTATION + "Invalid Command: Type help for command list");
//                System.out.println(Ui.SEPARATOR);
//            } catch (InvalidTodoCommandException e) {
//                System.out.println(Ui.SEPARATOR);
//                System.out.println(Ui.INDENTATION + "Usage: todo <task>");
//                System.out.println(Ui.SEPARATOR);
//            } catch (InvalidDeadlineCommandException e) {
//                System.out.println(Ui.SEPARATOR);
//                System.out.println(Ui.INDENTATION + "Usage: deadline <task> /by dd/mm/yyyy HHmm");
//                System.out.println(Ui.SEPARATOR);
//            } catch (InvalidEventCommandException e) {
//                System.out.println(Ui.SEPARATOR);
//                System.out.println(Ui.INDENTATION + "Usage: event <task> /from dd/mm/yyyy HHmm /to dd/mm/yyyy HHmm");
//                System.out.println(Ui.SEPARATOR);
//            } catch (InvalidMarkCommandException e) {
//                System.out.println(Ui.SEPARATOR);
//                System.out.println(Ui.INDENTATION + "Usage: mark <task number>\n"
//                        + Ui.INDENTATION + "Type list for task numbers");
//                System.out.println(Ui.SEPARATOR);
//            } catch (InvalidUnmarkCommandException e) {
//                System.out.println(Ui.SEPARATOR);
//                System.out.println(Ui.INDENTATION + "Usage: unmark <task number>\n"
//                        + Ui.INDENTATION + "Type list for task numbers");
//                System.out.println(Ui.SEPARATOR);
//            } catch (InvalidTaskListNumberException e) {
//                System.out.println(Ui.SEPARATOR);
//                System.out.println(Ui.INDENTATION + "Task number needs to be an integer");
//                System.out.println(Ui.SEPARATOR);
//            } catch (InvalidDeleteCommandException e) {
//                System.out.println(Ui.SEPARATOR);
//                System.out.println(Ui.INDENTATION + "Usage: delete <task number>\n"
//                        + Ui.INDENTATION + "Type list for task numbers");
//                System.out.println(Ui.SEPARATOR);
//            }
//        }
//
//        storage.saveTasks(taskList);
//
//        sc.close();
//    }
}
