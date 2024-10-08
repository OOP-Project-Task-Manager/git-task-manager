package commands.change;

import commands.BaseCommand;
import core.contracts.TaskRepository;
import models.tasks.Contracts.Story;
import models.tasks.enums.Size;
import utilities.ParsingHelpers;
import utilities.ValidationHelper;

import java.util.List;
import java.util.Stack;

public class ChangeSizeInStoryCommand extends BaseCommand {

    public static final int EXPECTED_ARGUMENTS_COUNT = 2;
    public static final String SIZE_NOT_EXIST = "Size {%s} does not exist";
    public static final String NOT_A_VALID_INTEGER = "Not a valid integer";

    public static final String SIZE_ERR = "Can't change size with same size";

    public static final String SIZE_CHANGE = "Size of task %s changed to %s";


    public ChangeSizeInStoryCommand(TaskRepository repository){
        super(repository);
    }
    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT);
        int id = ParsingHelpers.tryParseInteger(parameters.get(0), NOT_A_VALID_INTEGER);
        Size size = ParsingHelpers.tryParseEnum(parameters.get(1), Size.class, SIZE_NOT_EXIST);
        Story story = getRepository().findStoryById(id);
        return changeSizeInStory(story, size);
    }

    private String changeSizeInStory(Story story, Size size){
        if (story.getSize() == size){
            throw new IllegalArgumentException(SIZE_ERR);
        }
        story.setSize(size);
        return String.format(SIZE_CHANGE, story.getTitle(), size);
    }
}
