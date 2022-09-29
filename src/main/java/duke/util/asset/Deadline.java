package duke.util.asset;

public class Deadline extends Task {

    protected String byWhen;
    public static final String OPTIONFLAG = "by";
    public static final String COMMAND = "deadline";

    public Deadline(String description, String byWhen) {
        super(description);
        this.byWhen = byWhen;
        this.addMessage = "OH NO BEEP BEEP, a new Deadline: " + description;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byWhen + ")";
    }

    @Override
    public String serialize() {
        return COMMAND + " " + getTask() + "/" + OPTIONFLAG + " " + byWhen;
    }

    @Override
    public String getAddMessage() {
        return addMessage;
    }
}