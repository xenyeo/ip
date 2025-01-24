/**
 * Represents the user interface functionalities for the KAJI virtual assistant.
 */
public class UI {
    protected static final String INDENTATION = "    ";
    protected static final String SEPARATOR = INDENTATION + "____________________________________________________________";
    // Logo design adapted from https://patorjk.com/software/taag
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

    /**
     * Prints the application's logo to the console.
     */
    private void printLogo() {
        System.out.println(LOGO);
    }

    /**
     * Prints the greeting message to the console.
     */
    private void printGreeting() {
        System.out.println(GREET);
    }

    /**
     * Prints the exit message to the console.
     */
    private void printExit() {
        System.out.println(EXIT);
    }

    /**
     * Initializes the user interface for the KAJI virtual assistant.
     */
    public void start() {
        printLogo();
        System.out.println(SEPARATOR);
        printGreeting();
        System.out.println(SEPARATOR);
    }

    /**
     * Terminates the user interface for the KAJI virtual assistant.
     */
    public void end() {
        System.out.println(SEPARATOR);
        printExit();
        System.out.println(SEPARATOR);
    }
}
