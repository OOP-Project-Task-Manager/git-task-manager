package commands;

import core.contracts.TaskRepository;
import models.tasks.Contracts.Story;
import models.tasks.enums.Status;
import utilities.ParsingHelpers;
import utilities.ValidationHelper;

import java.util.List;

public class ChangeStatusInStoryCommand extends BaseCommand {
    public static final int EXPECTED_ARGUMENTS_COUNT = 2;
    public static final String STATUS_NOT_EXIST = "Status {%s} does not exist";
    public static final String NOT_A_VALID_INTEGER = "Not a valid integer";

    public static final String STATUS_CHANGE = "Status of task %s changed to %s";
    public static final String STATUS_ERR = "Can't change status with same status";


    public ChangeStatusInStoryCommand(TaskRepository repository){
        super(repository);
    }
    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT);
        int id = ParsingHelpers.tryParseInteger(parameters.get(0), NOT_A_VALID_INTEGER);
        Status status = ParsingHelpers.tryParseEnum(parameters.get(1), Status.class ,STATUS_NOT_EXIST);
        Story story = getRepository().findStoryById(id);
        return changeStatusInStory(story, status);
    }

    private String changeStatusInStory(Story story, Status status){
        if (story.getStatus() == status){
            throw new IllegalArgumentException(STATUS_ERR);
        }
        story.setStatus(status);
        return String.format(STATUS_CHANGE, story.getTitle(), status);
    }
}
