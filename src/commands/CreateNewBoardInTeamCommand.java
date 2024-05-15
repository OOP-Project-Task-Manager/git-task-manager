package commands;

import core.contracts.TaskRepository;
import models.BoardImpl;
import models.contracts.Board;
import models.contracts.Team;
import utilities.ValidationHelper;

import java.util.List;

public class CreateNewBoardInTeamCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final static String BOARD_CREATED = "Board %s created successfully!";
    CreateNewBoardInTeamCommand(TaskRepository repository){
        super(repository);
    }
    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);
        return createBoardInTeam(name);
    }
    private String createBoardInTeam(String name){
        Team team = getRepository().findTeamByName(name);
        Board board = getRepository().createBoard(name);
        team.addBoard(board);
        return String.format(BOARD_CREATED, board.getName());
    }

    ///not sure should ask tomorrow!!!!!!!!!!!!!!!!
}
