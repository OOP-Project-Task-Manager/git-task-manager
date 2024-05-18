package commands;

import core.contracts.TaskRepository;
import models.tasks.Contracts.Bug;
import models.tasks.enums.StatusBug;
import utilities.ParsingHelpers;
import utilities.ValidationHelper;

import java.util.List;

public class ChangeStatusInBugCommand extends BaseCommand {
    public static final int EXPECTED_ARGUMENTS_COUNT = 2;
    public static final String STATUS_NOT_EXIST = "Status {%s} does not exist";
    public static final String NOT_A_VALID_INTEGER = "Not a valid integer";

    public static final String STATUS_ERR = "Can't change status with same status";

    public static final String STATUS_CHANGE = "Status of task %s changed to %s";

    public ChangeStatusInBugCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT);
        int id = ParsingHelpers.tryParseInteger(parameters.get(0), NOT_A_VALID_INTEGER);
        StatusBug status = ParsingHelpers.tryParseEnum(parameters.get(1), StatusBug.class, STATUS_NOT_EXIST);
        Bug bug = getRepository().findBugById(id);

        return changeStatus(bug, status);
    }

    private String changeStatus(Bug bug, StatusBug status) {
        if (bug.getStatus() == status){
            throw new IllegalArgumentException(STATUS_ERR);
        }
        bug.setStatus(status);
        return String.format(STATUS_CHANGE, bug.getTitle(), status);
    }
}
