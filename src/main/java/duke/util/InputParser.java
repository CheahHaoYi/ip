package duke.util;

import duke.exception.UnknownCommandException;
import duke.exception.EmptyArgumentException;

import java.util.Scanner;

public class InputParser implements Utilities{

    private static String userCommand;
    private static String inputBuffer;
    private static Scanner scanner;

    public InputParser() {
    }

    public static void init() {
        inputBuffer = "";
        userCommand = "";
        scanner = new Scanner(System.in);
    }

    public static void close() {
        inputBuffer = "";
        userCommand = "";
        scanner.close();
    }

    private static void clear() {
        inputBuffer = "";
        userCommand = "";
    }

    private static boolean isValidCommand() {
        switch (userCommand) {
            case ("list"):
            case ("mark"):
            case ("unmark"):
            case ("todo"):
            case ("deadline"):
            case ("event"): //Fallthrough
            case ("delete"):
                return true;
            default:
                return false;
        }
    }

    private static boolean isCorrectInput(String[] parsed ) {
        if (userCommand.equals("list")){
            return true;
        }
        return (parsed.length > 1);
    }

    private static String[] parseParameter(String inputString, String optionFlag){
        int optionLen = 4;
        int optionIndex = inputString.indexOf(optionFlag);

        String descriptionMain = inputString.substring(0, optionIndex);
        String descriptionOption = inputString.substring(optionIndex + optionLen);

        return new String[]{ descriptionMain , descriptionOption };
    }

    public static void parseUserInput(String userInput) throws UnknownCommandException, EmptyArgumentException {
        final int NUM_CMD_SPLIT = 2;
        //assume first word input by user is the command
        String[] inputSplitBySpace = userInput.split(" ", NUM_CMD_SPLIT);

        userCommand = inputSplitBySpace[0];

        if (!isValidCommand()) {
            throw new UnknownCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if (!isCorrectInput(inputSplitBySpace) ) {
            throw new EmptyArgumentException("☹ OOPS!!! The description cannot be empty.");
        }

        //if the particular command requires arguments
        if ( inputSplitBySpace.length > 1) {
            inputBuffer = inputSplitBySpace[1];
        }

    }

    public static void readInput() {
        userCommand = scanner.nextLine();
    }

    public static String getCommand() {
        return userCommand;
    }

    public static String[] getTaskParameters() {
        String[] parameters;

        switch (userCommand) {
            case ("todo"):
            case ("mark"):
            case ("delete"): //Fallthrough
            case ("unmark"):
                parameters = new String[]{ inputBuffer };
                break;
            case ("deadline"):
                parameters = parseParameter(inputBuffer, "/by");
                break;
            case ("event"):
                parameters = parseParameter(inputBuffer, "/at");
                break;
            default:
                parameters = null;
        }

        clear();
        return parameters;
    }

}
