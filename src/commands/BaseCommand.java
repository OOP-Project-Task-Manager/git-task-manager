package commands;

import commands.contracts.Command;
import core.contracts.TaskRepository;

import java.util.List;

public abstract class BaseCommand implements Command {
    private final static String TEAM_NOT_LOGGED = "Not logged in!";
    private final TaskRepository repository;
    protected BaseCommand(TaskRepository repository) {
        this.repository = repository;
    }

    protected TaskRepository getRepository() {
        return repository;
    }

    @Override
    public String execute(List<String> parameters) {
        /*if (requiresLogin() && !repository.hasLoggedInTeam()) {
            throw new IllegalArgumentException(TEAM_NOT_LOGGED);
        }*/
        return executeCommand(parameters);
    }

    //protected abstract boolean requiresLogin();

    protected abstract String executeCommand(List<String> parameters);
}
