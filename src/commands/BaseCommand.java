package commands;

import commands.contracts.Command;
import core.contracts.TaskRepository;

import java.util.List;

public abstract class BaseCommand implements Command {

    private final TaskRepository repository;

    protected BaseCommand(TaskRepository repository) {
        this.repository = repository;
    }

    protected TaskRepository getRepository() {
        return repository;
    }

    @Override
    public String execute(List<String> parameters) {

        return executeCommand(parameters);
    }


    protected abstract String executeCommand(List<String> parameters);
}
