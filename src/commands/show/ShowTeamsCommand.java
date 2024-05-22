package commands.show;

import commands.BaseCommand;
import core.contracts.TaskRepository;
import models.contracts.Team;

import java.util.List;

public class ShowTeamsCommand extends BaseCommand {


    public ShowTeamsCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        return showTeams();
    }

    private String showTeams() {
        List<Team> teams = getRepository().getTeams();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Teams:\n");

        for (Team team : teams) {
            stringBuilder.append("Team ").append(team.getName()).append("\n");
        }

        return stringBuilder.toString();


    }

}
