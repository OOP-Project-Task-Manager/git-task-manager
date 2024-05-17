package commands;

import commands.BaseCommand;
import core.contracts.TaskRepository;
import models.contracts.Board;
import utilities.ValidationHelper;

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
        return board.getLogs().toString();//toString() override not sure if it should be overridden?
    }
}
