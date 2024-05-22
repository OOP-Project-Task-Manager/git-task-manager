package commands.create;

import commands.BaseCommand;
import core.contracts.TaskRepository;
import models.contracts.Board;
import models.contracts.Member;
import models.tasks.Contracts.Bug;
import models.tasks.Contracts.Feedback;
import models.tasks.Contracts.Story;
import models.tasks.enums.Priority;
import models.tasks.enums.Severity;
import utilities.ParsingHelpers;
import utilities.ValidationHelper;

import java.util.ArrayList;
import java.util.List;


public class CreateNewBugInBoardCommand extends BaseCommand {

    public static final String BUG_CREATED = "Bug with name: {%s} and id:{%s} created in board: {%s}";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 7;
    public static final String PRIORITY_ERR = "Priority {%s} does not exist";
    public static final String SEVERITY_ERR = "Severity {%s} does not exist";

    public CreateNewBugInBoardCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
//        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String title = parameters.get(0);
        String description = parameters.get(1);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(2), Priority.class, PRIORITY_ERR.formatted(parameters.get(2)));
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(3), Severity.class, SEVERITY_ERR.formatted(parameters.get(3)));
        Member assignee = getRepository().findMemberByName(parameters.get(4));
        String board = parameters.get(5);
        int i = 6;
        List<String> stepsToReproduce = new ArrayList<>();
        while (!parameters.get(i).equalsIgnoreCase("exit")) {
            stepsToReproduce.add(parameters.get(i++));

        }


        return createBugInBoard(title, description, priority, severity, assignee, board, stepsToReproduce);
    }


    private String createBugInBoard(String title, String description, Priority priority, Severity severity, Member assignee, String boardName, List<String> stepsToReproduce) {
        Board board = getRepository().findBoardByName(boardName);
        Bug bug = getRepository().createBug(title, description, priority, severity, assignee);
        for (String step : stepsToReproduce) {
            bug.addStepToReproduce(step);
        }
        bug.setBoard(board);
        AddToBoard(bug, board);
        AddToMember(bug, assignee);
        return String.format(BUG_CREATED, title, bug.getId(), boardName);


    }

    private void AddToBoard(Bug bug, Board board) {
        getRepository().addTaskToBoard(bug, board);
    }

    private void AddToMember(Bug bug, Member assignee) {
        getRepository().addTaskToMember(bug, assignee);
    }


}
