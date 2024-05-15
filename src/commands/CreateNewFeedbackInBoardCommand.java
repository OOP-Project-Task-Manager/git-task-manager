package commands;

import core.contracts.TaskRepository;
import models.contracts.Board;
import models.contracts.Team;
import utilities.ParsingHelpers;
import utilities.ValidationHelper;

import java.util.List;

public class CreateNewFeedbackInBoardCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;

    private final static String BOARD_CREATED = "Board {%s} created successfully and added to team {%s}!";
    public CreateNewFeedbackInBoardCommand(TaskRepository repository){
        super(repository);
    }
    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        //(int id, String title, String description, int rating) {
        String title = parameters.get(0);
        String description = parameters.get(1);
        int rating = ParsingHelpers.tryParseInteger(parameters.get(2),"rating");

//        return createBoardInTeam(boardName, teamName);
        return null;
    }
//    private String createFeedbackInBoard(String feedbackName, String boardName){
//        Team team = getRepository().findTeamByName(teamName);
//        Board board = getRepository().find
//        Board board = getRepository().createBoard(boardName);
//        team.addBoard(board);
//        return String.format(BOARD_CREATED, board.getName(),team.getName());
//    }


}
