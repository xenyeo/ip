public class Kaji {
    public static void main(String[] args) {
        // Design adapted from https://patorjk.com/software/taag
        String logo = "    )               (\n"
                + "  ( /(   (           )\\ )\n"
                + "  )\\())  )\\      (  (()/(\n"
                + " ((_)\\((((_)(    )\\  /(_))\n"
                + " _ ((_))\\ _ )\\  ((_)(_))\n"
                + "| |/ / (_) \\(_)_ | ||_ _|\n"
                + "| ' (   / _ \\ | || | | |\n"
                + "|_|\\_\\ /_/ \\_\\ \\__/ |___|";
        String separator = "____________________________________________________________";
        String greet = "Hello! I'm KAJI, your personal virtual assistant.\n"
                + "How can i help you today?";
        String exit = "Thank you for chatting with me!\n"
                + "See you again soon!";
        System.out.println(logo);
        System.out.println(separator);
        System.out.println(greet);
        System.out.println(separator);
        System.out.println(exit);
        System.out.println(separator);
    }
}
