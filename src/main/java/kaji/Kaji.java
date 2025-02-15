package kaji;

import kaji.command.Command;

/**
 * Initializes chatbot
 */
public class Kaji {

    private static final String FILE_PATH = "./data/kaji.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Kaji() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        loadFile();
        ui.showWelcome();
    }

    // For GUI (In Use)
    /**
     * Gets the response for the given input.
     *
     * @param input the user input
     * @return the response as a string
     * @throws KajiException if an error occurs while processing the input
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (KajiException e) {
            return e.getMessage();
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @throws KajiException if an error occurs while loading the tasks
     */
    private void loadFile() {
        try {
            tasks = new TaskList(storage.load());
        } catch (KajiException e) {
            ui.showLoadingError(); // If file cannot be created or loaded fully
            tasks = new TaskList();
        }
    }

    // CLI interface (Not In Use)
    public static void main(String[] args) {
    }
}
