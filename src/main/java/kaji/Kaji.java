package kaji;

import kaji.command.Command;

/**
 * Initializes chatbot
 */
public class Kaji {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Kaji(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (KajiException e) {
            ui.showLoadingError(); // If file cannot be created or loaded fully
            tasks = new TaskList();
        }
    }

    /**
     * Starts the chatbot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (KajiException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showExit();
    }

    public static void main(String[] args) {
        new Kaji("data/tasks.txt").run();
    }
}
