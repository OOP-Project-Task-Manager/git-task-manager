package commands.Archived;

import commands.BaseCommand;
import core.contracts.TaskRepository;
import models.contracts.Assignable;
import models.contracts.Member;
import models.tasks.Contracts.Task;
import utilities.ParsingHelpers;
import utilities.ValidationHelper;

import java.util.List;

public class UnassignTaskToPersonCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String NO_SUCH_ID = "Task with id %s does not exist";

    public static final String TASK_UNASSIGNED_SUCCESSFULLY = "Task with id %d unassigned from %s successfully!";

    public UnassignTaskToPersonCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int taskId = ParsingHelpers.tryParseInteger(parameters.get(0), NO_SUCH_ID);
        String personName = parameters.get(1);
        return unassignTaskToPerson(taskId, personName);
    }

    private String unassignTaskToPerson(int taskId, String personName) {
        Member member = getRepository().findMemberByName(personName);
        Assignable task = null;
        try {
            task = (Assignable) getRepository().findTaskById(taskId);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Task does not have assignable condition");
        }
        task.setAssignee(null);
        member.removeTask(task);
        return String.format(TASK_UNASSIGNED_SUCCESSFULLY, taskId, personName);
    }
}
