package commands.show;

import commands.BaseCommand;
import core.contracts.TaskRepository;
import models.contracts.Member;
import models.contracts.Team;
import models.tasks.Contracts.EventLog;
import utilities.ValidationHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ShowPersonActivityCommand extends BaseCommand {

    public static final int EXPECTED_ARGUMENTS_COUNT = 1;

    public ShowPersonActivityCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT);
        Member member = getRepository().findMemberByName(parameters.get(0));
        return showPersonActivity(member);
    }

    private String showPersonActivity(Member member) {
        List<EventLog> activity = member.getLogs();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Member %s activity history:\n".formatted(member.getName()));
        stringBuilder.append("\n");
        for (EventLog eventLog : activity) {
            stringBuilder.append(eventLog.toString()).append("\n");
        }
        stringBuilder.append("No more logs as of ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm:ss a")));

        return stringBuilder.toString();
    }
}
