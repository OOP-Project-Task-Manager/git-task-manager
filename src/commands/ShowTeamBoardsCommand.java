package commands;

import core.contracts.TaskRepository;
import models.contracts.Board;
import models.contracts.Team;
import utilities.ValidationHelper;

import java.util.List;

public class ShowTeamBoardsCommand extends BaseCommand {

    public static final int EXPECTED_ARGUMENTS_COUNT = 1;

    public ShowTeamBoardsCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT);
        Team team = getRepository().findTeamByName(parameters.get(0));

        return showTeamBoard(team);
    }

    private String showTeamBoard(Team team) {
        List<Board> boards = team.getBoards();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Team %s boards:\n".formatted(team.getName()));
        for (Board board : boards) {
            stringBuilder.append(board.getName()).append("\n");
        }

        return stringBuilder.toString();


    }
}
