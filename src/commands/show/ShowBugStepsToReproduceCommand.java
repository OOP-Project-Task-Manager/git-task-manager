package commands.show;

import commands.BaseCommand;
import core.contracts.TaskRepository;
import models.tasks.Contracts.Bug;
import utilities.ParsingHelpers;
import utilities.ValidationHelper;

import java.util.List;

public class ShowBugStepsToReproduceCommand extends BaseCommand {
    public static final String NOT_A_VALID_INTEGER = "Not a valid integer";
    public static final int EXPECTED_ARUGMENTS_COUNT = 1;

    public ShowBugStepsToReproduceCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_ARUGMENTS_COUNT);
        int bugId = ParsingHelpers.tryParseInteger(parameters.get(0), NOT_A_VALID_INTEGER);
        return showStepsToReproduce(bugId);
    }


    private String showStepsToReproduce(int bugId) {
        Bug bug = getRepository().findBugById(bugId);
        StringBuilder stringBuilder = new StringBuilder();
        for (String step : bug.getStepsToReproduce()) {
            stringBuilder.append(step).append(" ");
        }

        return """
                Steps to reproduce of bug with id:%s
                                
                %s
                                
                End
                """.formatted(bugId, stringBuilder.toString());
    }
}
