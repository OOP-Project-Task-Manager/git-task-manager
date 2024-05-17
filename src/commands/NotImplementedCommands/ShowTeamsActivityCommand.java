package commands.NotImplementedCommands;

import commands.BaseCommand;
import core.contracts.TaskRepository;
import models.contracts.Team;
import utilities.ValidationHelper;

import java.util.List;

public class ShowTeamsActivityCommand extends BaseCommand {

    public static final int EXPECTED_ARGUMENTS_COUNT = 1;
    public static final String INVALID_TEAM_ERR = "No such team";
    ShowTeamsActivityCommand(TaskRepository repository){
        super(repository);
    }
    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT);
        Team team = getRepository().findTeamByName(parameters.get(0));
        return showTeamsActivity(team);
    }

    private String showTeamsActivity(Team team){
        return team.getLogs().toString();//toString() override not sure if it should be overridden?
    }
}
