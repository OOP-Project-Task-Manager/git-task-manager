package commands;

import core.contracts.TaskRepository;
import models.contracts.Board;
import models.contracts.Member;
import models.tasks.Contracts.Story;
import models.tasks.enums.Priority;
import models.tasks.enums.Severity;
import models.tasks.enums.Size;
import utilities.ParsingHelpers;
import utilities.ValidationHelper;

import java.util.List;

public class CreateNewStoryInBoardCommand extends BaseCommand {

    public static final String STORY_CREATED = "Story with name: {%s} and id: {%s} created and added to board {%s}";
    public static final String PRIORITY_ERR = "Priority {%s} does not exist";
    public static final String SIZE_ERR = "Size {%s} does not exist";
    public static final int EXPECTED_ARGUMENTS_COUNT = 6;


    public CreateNewStoryInBoardCommand(TaskRepository repository) {
        super(repository);
    }


    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT);
        String title = parameters.get(0);
        String description = parameters.get(1);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(2), Priority.class, PRIORITY_ERR.formatted(parameters.get(2)));
        Size size = ParsingHelpers.tryParseEnum(parameters.get(3), Size.class, SIZE_ERR.formatted(parameters.get(3)));
        Member assignee = getRepository().findMemberByName(parameters.get(4));
        String board = parameters.get(5);


        return createStoryInBoard(title, description, priority, size, assignee, board);
    }


    private String createStoryInBoard(String title, String description, Priority priority, Size size, Member assignee,
                                      String boardName) {
        Board board = getRepository().findBoardByName(boardName);
        Story story = getRepository().createStory(title, description, priority, size, assignee);
        AddToBoard(story, board);
        AddToMember(story, assignee);
        return String.format(STORY_CREATED, title, story.getId(), boardName);


    }

    private void AddToMember(Story story, Member assignee) {
        getRepository().addTaskToMember(story, assignee);
    }

    private void AddToBoard(Story story, Board board) {
        getRepository().addTaskToBoard(story, board);
    }
}
