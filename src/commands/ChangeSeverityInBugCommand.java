package commands;

import core.contracts.TaskRepository;
import models.tasks.Contracts.Bug;
import models.tasks.enums.Severity;
import utilities.ParsingHelpers;
import utilities.ValidationHelper;

import java.util.List;

public class ChangeSeverityInBugCommand extends BaseCommand {

    public static final int EXPECTED_ARGUMENTS_COUNT = 2;
    public static final String NOT_A_VALID_INTEGER = "Not a valid integer";
    public static final String SEVERITY_CHANGE = "Severity of task %s changed to %s";
    public static final String SEVERITY_ERR = "Not a valid severity option";

    protected ChangeSeverityInBugCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT);
        int id = ParsingHelpers.tryParseInteger(parameters.get(0), NOT_A_VALID_INTEGER);
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(1), Severity.class, SEVERITY_ERR);
        Bug bug = getRepository().findBugById(id);

        return changeSevereity(bug, severity);

    }


    private String changeSevereity(Bug bug, Severity severity) {
        bug.setSeverity(severity);
        return String.format(SEVERITY_CHANGE, bug.getTitle(), severity);

    }
}
