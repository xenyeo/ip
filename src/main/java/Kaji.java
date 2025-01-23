import java.util.Scanner;

public class Kaji {
    private static final String INDENTATION = "    ";
    private static final String SEPARATOR = INDENTATION + "____________________________________________________________";
    // Design adapted from https://patorjk.com/software/taag
    private static final String LOGO = INDENTATION
            + "    )               (\n"
            + "      ( /(   (           )\\ )\n"
            + "      )\\())  )\\      (  (()/(\n"
            + "     ((_)\\((((_)(    )\\  /(_))\n"
            + "     _ ((_))\\ _ )\\  ((_)(_))\n"
            + "    | |/ / (_) \\(_)_ | ||_ _|\n"
            + "    | ' (   / _ \\ | || | | |\n"
            + "    |_|\\_\\ /_/ \\_\\ \\__/ |___|";
    private static final String GREET = INDENTATION + "Hello! I'm KAJI, your personal virtual assistant.\n"
            + INDENTATION + "How can i help you today?";
    private static final String EXIT = INDENTATION + "Thank you for chatting with me!\n"
            + INDENTATION + "See you again soon!";

    public static void main(String[] args) {
        System.out.println(LOGO);
        System.out.println(SEPARATOR);
        System.out.println(GREET);
        System.out.println(SEPARATOR);

        Scanner sc = new Scanner(System.in);
        String command = "";
        while (!command.equals("bye")) {
            command = sc.nextLine();
            System.out.println(SEPARATOR);
            if (command.equals("bye")) {
                System.out.println(EXIT);
            } else {
                System.out.println(INDENTATION + command);
            }
            System.out.println(SEPARATOR);
        }
        sc.close();
    }
}
