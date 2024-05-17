package commands;

import commands.BaseCommand;
import core.contracts.TaskRepository;
import models.contracts.Board;
import models.tasks.Contracts.EventLog;
import utilities.ValidationHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ShowBoardsActivityCommand extends BaseCommand {
    public static final int EXPECTED_ARGUMENTS_COUNT = 1;

    public ShowBoardsActivityCommand(TaskRepository repository){
        super(repository);
    }
    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT);
        Board board = getRepository().findBoardByName(parameters.get(0));
        return showBoardsActivity(board);
    }

    private String showBoardsActivity(Board board){
        List<EventLog> activity = board.getLogs();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Board %s activity history:\n".formatted(board.getName()));
        stringBuilder.append("\n");
        for (EventLog eventLog : activity) {
            stringBuilder.append(eventLog.toString()).append("\n");
        }
        stringBuilder.append("No more logs as of ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm:ss a")));

        return stringBuilder.toString();
    }
}
