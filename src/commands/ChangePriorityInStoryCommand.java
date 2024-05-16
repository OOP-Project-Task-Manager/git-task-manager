package commands;

import core.contracts.TaskRepository;
import models.tasks.Contracts.Story;
import models.tasks.Contracts.Task;
import models.tasks.enums.Priority;

import java.util.List;

public class ChangePriorityInStoryCommand extends BaseCommand {

    public ChangePriorityInStoryCommand(TaskRepository repository){
        super(repository);
    }
    @Override
    protected String executeCommand(List<String> parameters) {
        return null;
    }

    private String changePriorityInStory(int id, Priority priority){
        Task task = getRepository().findTaskById(id);




        return null;
    }
}
