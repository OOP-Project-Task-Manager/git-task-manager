package commands;

import core.contracts.TaskRepository;

import java.util.List;

public class ChangePriorityInStoryCommand extends BaseCommand {

    public ChangePriorityInStoryCommand(TaskRepository repository){
        super(repository);
    }
    @Override
    protected String executeCommand(List<String> parameters) {
        return null;
    }

    private String changePriorityInStory(){
        return null;
    }
}
