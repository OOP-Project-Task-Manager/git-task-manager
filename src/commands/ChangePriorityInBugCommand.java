package commands;

import core.contracts.TaskRepository;
import models.tasks.Contracts.Bug;
import models.tasks.Contracts.Story;
import models.tasks.enums.Priority;
import utilities.ParsingHelpers;
import utilities.ValidationHelper;

import java.util.List;

public class ChangePriorityInBugCommand extends BaseCommand {

    protected ChangePriorityInBugCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        return null;
    }


}
