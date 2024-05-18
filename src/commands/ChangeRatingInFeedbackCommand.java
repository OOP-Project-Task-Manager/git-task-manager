package commands;

import core.contracts.TaskRepository;
import models.tasks.Contracts.Feedback;
import utilities.ParsingHelpers;
import utilities.ValidationHelper;

import java.util.List;

public class ChangeRatingInFeedbackCommand extends BaseCommand {

    public static final int EXPECTED_ARGUMENTS_COUNT = 2;
    public static final String NOT_A_VALID_INTEGER = "Not a valid integer";
    public static final String RATING_CHANGE = "Rating of task %s changed to %s";
    public static final String RATING_ERR = "Can't change rating with same rating";


    public ChangeRatingInFeedbackCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT);
        int id = ParsingHelpers.tryParseInteger(parameters.get(0), NOT_A_VALID_INTEGER);
        Feedback feedback = getRepository().findFeedbackById(id);
        int rating = ParsingHelpers.tryParseInteger(parameters.get(1), NOT_A_VALID_INTEGER);

        return changeRating(feedback, rating);
    }

    private String changeRating(Feedback feedback, int rating) {
        if (feedback.getRating() == rating){
            throw new IllegalArgumentException(RATING_ERR);
        }
        feedback.setRating(rating);
        return String.format(RATING_CHANGE, feedback.getTitle(), rating);
    }


}
