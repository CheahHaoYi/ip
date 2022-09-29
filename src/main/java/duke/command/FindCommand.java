package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class FindCommand extends Command {
    private final String keyword;
    public static final String COMMAND = "find";
    private static final String MESSAGE_FIND = "Here are the matching tasks in your list:";

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        tasks.findTasksContain(keyword);
        ui.addLine(MESSAGE_FIND);
        ui.addLine(tasks.getMessages());
        ui.printUi();

        tasks.clearBuffer();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}