package commands;

import commands.BaseCommand;
import core.contracts.TaskRepository;
import models.contracts.Team;
import models.tasks.Contracts.EventLog;
import utilities.ValidationHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ShowTeamsActivityCommand extends BaseCommand {

    public static final int EXPECTED_ARGUMENTS_COUNT = 1;

    public ShowTeamsActivityCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT);
        Team team = getRepository().findTeamByName(parameters.get(0));
        return showTeamsActivity(team);
    }

    private String showTeamsActivity(Team team) {
        List<EventLog> activity = team.getLogs();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Team %s activity history:\n".formatted(team.getName()));
        stringBuilder.append("\n");
        for (EventLog eventLog : activity) {
            stringBuilder.append(eventLog.toString()).append("\n");
        }
        stringBuilder.append("No more logs as of ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm:ss a")));

        return stringBuilder.toString();
    }
}
