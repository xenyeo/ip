import java.util.Scanner;

public class Kaji {
    public static void main(String[] args) {
        UI ui = new UI();
        TaskList taskList = new TaskList();
        ui.start();

        Scanner sc = new Scanner(System.in);
        String command = "";
        while (!command.equals("bye")) {
            command = sc.nextLine();
            String[] commandParts = command.split(" ", 2);
            String action = commandParts[0];

            switch (action) {
                case "bye":
                    ui.end();
                    break;
                case "list":
                    taskList.printTasks();
                    break;
                case "mark":
                    taskList.markTask(Integer.parseInt(commandParts[1]));
                    break;
                case "unmark":
                    taskList.unmarkTask(Integer.parseInt(commandParts[1]));
                    break;
                case "todo":
                    taskList.addTask(commandParts[1], "T");
                    break;
                case "deadline":
                    taskList.addTask(commandParts[1], "D");
                    break;
                case "event":
                    taskList.addTask(commandParts[1], "E");
                    break;
                default:
                    ui.echo(command);
                    break;
            }
        }
        sc.close();
    }
}
