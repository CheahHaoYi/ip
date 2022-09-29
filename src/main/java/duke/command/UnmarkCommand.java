package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskManager;
import duke.util.Ui;

public class UnmarkCommand extends Command {
    private final int taskIndex;
    public static final String COMMAND = "unmark";
    private static final String MESSAGE_UNMARK = "OK, I've marked this task as not done yet:";


    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskManager tasks, Ui ui, Storage storage) {

        try {
            tasks.setTask(taskIndex, false);
            ui.addLine(MESSAGE_UNMARK);
            ui.addLine(tasks.getMessages());
            ui.printUi();
        } catch (DukeException e) {
            ui.displayMessage(e.getErrorMessage());
        }

        tasks.clearBuffer();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
