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
                case "bye" -> ui.end();
                case "list" -> taskList.printTasks();
                case "mark" -> taskList.markTask(Integer.parseInt(commandParts[1]));
                case "unmark" -> taskList.unmarkTask(Integer.parseInt(commandParts[1]));
                case "todo" -> taskList.addTask(commandParts[1], "T");
                case "deadline" -> taskList.addTask(commandParts[1], "D");
                case "event" -> taskList.addTask(commandParts[1], "E");
            }

        }
        sc.close();
    }
}
