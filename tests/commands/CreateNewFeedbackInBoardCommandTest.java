package commands;

import commands.contracts.Command;
import commands.create.CreateNewFeedbackInBoardCommand;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import models.BoardImpl;
import models.contracts.Board;
import models.tasks.Contracts.Feedback;
import models.tasks.FeedbackImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateNewFeedbackInBoardCommandTest {
    private Command createNewFeedbackCommand;
    private TaskRepository repository;
    @BeforeEach
    public void before(){
        repository = new TaskRepositoryImpl();
        createNewFeedbackCommand = new CreateNewFeedbackInBoardCommand(repository);
    }
    @Test
    public void execute_Should_AddNewFeedbackToRepository_When_ValidParameters() {
        List<String> params = new ArrayList<>();
        Feedback feedback = new FeedbackImpl(1, "Feedback321123", "FeedbackForWork", 8);
        Board board = new BoardImpl("board1234");
        repository.addBoard(board);
        board.addTask(feedback);
        params.add(feedback.getTitle());
        params.add(feedback.getDescription());
        params.add(String.valueOf(feedback.getRating()));
        params.add(board.getName());
        createNewFeedbackCommand.execute(params);
        Assertions.assertEquals(1, repository.getFeedbacks().size());
    }
    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        List<String> params = new ArrayList<>();
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createNewFeedbackCommand.execute(params));
    }
}
