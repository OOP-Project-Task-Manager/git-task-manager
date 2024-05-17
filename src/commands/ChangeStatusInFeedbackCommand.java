package commands;

import core.contracts.TaskRepository;
import models.tasks.Contracts.Feedback;
import models.tasks.enums.StatusFeedback;
import utilities.ParsingHelpers;
import utilities.ValidationHelper;

import java.util.List;

public class ChangeStatusInFeedbackCommand extends BaseCommand {

    public static final int EXPECTED_ARGUMENTS_COUNT = 2;
    public static final String STATUS_ERR = "Status {%s} does not exist";
    public static final String NOT_A_VALID_INTEGER = "Not a valid integer";

    public static final String STATUS_CHANGE = "Status of task %s changed to %s";

    public ChangeStatusInFeedbackCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT);
        int id = ParsingHelpers.tryParseInteger(parameters.get(0), NOT_A_VALID_INTEGER);
        Feedback feedback = getRepository().findFeedbackById(id);
        StatusFeedback status = ParsingHelpers.tryParseEnum(parameters.get(1), StatusFeedback.class, STATUS_ERR);

        return changeStatus(feedback, status);
    }

    private String changeStatus(Feedback feedback, StatusFeedback status) {
        feedback.setStatus(status);

        return String.format(STATUS_CHANGE, feedback.getId(), status);

    }


}
