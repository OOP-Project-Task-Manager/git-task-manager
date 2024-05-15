package commands;

import core.contracts.TaskRepository;
import models.contracts.Board;
import models.contracts.Team;
import models.tasks.Contracts.Feedback;
import utilities.ParsingHelpers;
import utilities.ValidationHelper;

import java.util.List;

public class CreateNewFeedbackInBoardCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;

    private final static String FEEDBACK_CREATED = "Feedback {%s} created successfully and added to board {%s}!";
    public static final String NOT_A_VALID_INTEGER = "Not a valid integer";

    public CreateNewFeedbackInBoardCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        //(int id, String title, String description, int rating) {
        String title = parameters.get(0);
        String description = parameters.get(1);
        int rating = ParsingHelpers.tryParseInteger(parameters.get(2), NOT_A_VALID_INTEGER);
        String boardName = parameters.get(3);


        return createFeedbackInBoard(title, description, rating, boardName);

    }

    private String createFeedbackInBoard(String title, String description, int rating, String boardName) {
        Board board = getRepository().findBoardByName(boardName);
        Feedback feedback = getRepository().createFeedback(title, description, rating);
        return String.format(FEEDBACK_CREATED, title, boardName);


    }


}
