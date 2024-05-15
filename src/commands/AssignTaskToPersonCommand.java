package commands;

import core.contracts.TaskRepository;
import models.contracts.Member;
import models.tasks.Contracts.Task;
import utilities.ParsingHelpers;
import utilities.ValidationHelper;

import java.util.List;

public class AssignTaskToPersonCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String TASK_ASSIGNED_SUCCESSFULLY = "Task with id %d assigned to %s successfully!";
    public static final String NO_SUCH_ID = "Task with id %s does not exist";
    public AssignTaskToPersonCommand(TaskRepository repository){
        super(repository);
    }
    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int taskId = ParsingHelpers.tryParseInteger(parameters.get(0), NO_SUCH_ID);
        String personName = parameters.get(1);
        return assignTaskToPerson(taskId, personName);
    }
    private String assignTaskToPerson(int taskId, String personName){
        Member member = getRepository().findMemberByName(personName);
        Task task = getRepository().findTaskById(taskId);
        member.addTask(task);
        return String.format(TASK_ASSIGNED_SUCCESSFULLY, taskId, personName);
    }
}

