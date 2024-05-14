package commands;

import core.contracts.TaskRepository;
import models.BoardImpl;
import models.contracts.Board;
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
        String name = parameters.getFirst();
        return createBoardInTeam(name);
    }
    private String createBoardInTeam(String name){
        Board board = getRepository().createBoard(name);
        return String.format(BOARD_CREATED, board.getName());
    }
}
