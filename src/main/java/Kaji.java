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
            if (command.equals("bye")) {
                ui.end();
            } else if (command.equals("list")) {
                taskList.printTasks();
            } else if (command.split(" ")[0].equals("mark")) {
                taskList.markTask(Integer.parseInt(command.split(" ")[1]));
            } else if (command.split(" ")[0].equals("unmark")) {
                taskList.unmarkTask(Integer.parseInt(command.split(" ")[1]));
            } else {
                taskList.addTask(command);
                ui.echo(command);
            }
        }
        sc.close();
    }
}
