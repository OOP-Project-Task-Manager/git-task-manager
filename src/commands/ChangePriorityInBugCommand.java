package commands;

import core.contracts.TaskRepository;
import models.tasks.Contracts.Bug;
import models.tasks.Contracts.Story;
import models.tasks.enums.Priority;
import utilities.ParsingHelpers;
import utilities.ValidationHelper;

import java.util.List;

public class ChangePriorityInBugCommand extends BaseCommand {

    public static final int EXPECTED_ARGUMENTS_COUNT = 2;
    public static final String NOT_A_VALID_INTEGER = "Not a valid integer";
    public static final String PRIORITY_ERR = "Priority {%s} does not exist";
    public static final String PRIORITY_CHANGE = "Priority of task %s changed to %s";

    protected ChangePriorityInBugCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT);
        int id = ParsingHelpers.tryParseInteger(parameters.get(0), NOT_A_VALID_INTEGER);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(1), Priority.class, PRIORITY_ERR.formatted(parameters.get(1)));
        Story story = getRepository().findStoryById(id);


        return changePriority(story, priority);
    }

    private String changePriority(Story story, Priority priority) {
        story.setPriority(priority);
        return (PRIORITY_CHANGE.formatted(story.getTitle(), priority));

    }


}
