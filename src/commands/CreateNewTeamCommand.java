package commands;

import core.contracts.TaskRepository;
import models.contracts.Team;
import utilities.ValidationHelper;

import java.util.ArrayList;
import java.util.List;

public class CreateNewTeamCommand extends BaseCommand {

    List<String> test = new ArrayList<>();


    private final static String TEAM_CREATED = "Team %s created successfully!";
    public final static String TEAM_LOGGED_IN_ALREADY = "Team %s is logged in! Please log out first!";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public CreateNewTeamCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        //throwIfTeamLoggedIn();
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);
        return createTeam(name);
    }

    private String createTeam(String name) {
        Team team = getRepository().createTeam(name);
        getRepository().addTeam(team);
        /*getRepository().login(team);*///без
        return String.format(TEAM_CREATED, name);
    }

    /*@Override
    protected boolean requiresLogin() {
        return false;
    }*/

    /*private void throwIfTeamLoggedIn(){//без
        if (getRepository().hasLoggedInTeam()){
            throw new IllegalArgumentException(String.format(TEAM_LOGGED_IN_ALREADY,
                    getRepository().getLoggedInTeam().getName()));
        }
    }*/
}
