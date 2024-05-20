package commands;

import core.contracts.TaskRepository;
import models.BoardImpl;
import models.contracts.Board;
import models.contracts.Team;
import utilities.ValidationHelper;

import java.util.List;

public class CreateNewBoardInTeamCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private final static String BOARD_CREATED = "Board {%s} created successfully and added to team {%s}!";

    public CreateNewBoardInTeamCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String boardName = parameters.get(0);
        String teamName = parameters.get(1);
        return createBoardInTeam(boardName, teamName);
    }

    private String createBoardInTeam(String boardName, String teamName) {
        Team team = getRepository().findTeamByName(teamName);
        Board board = getRepository().createBoard(boardName);
        board.setTeam(team);
        team.addBoard(board);
        return String.format(BOARD_CREATED, board.getName(), team.getName());
    }


}
