package commands;

import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import models.BoardImpl;
import models.contracts.Board;
import models.tasks.Contracts.EventLog;
import models.tasks.EventLogImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowBoardsActivityCommandTest {
    ShowBoardsActivityCommand showBoardsActivityCommand;
    TaskRepository repository;
    @BeforeEach
    public void before(){
        repository = new TaskRepositoryImpl();
        showBoardsActivityCommand = new ShowBoardsActivityCommand(repository);
    }

    @Test
    public void executeCommand_Should_ThrowException_When_InvalidArgumentsCount(){
        List<String> params = Arrays.asList(new String[3]);
        Assertions.assertThrows(IllegalArgumentException.class, () -> showBoardsActivityCommand.execute(params));
    }
    @Test
    public void executeCommand_Should_ThrowException_When_BoardDoesNotExist(){
        List<String> params = new ArrayList<>();
        params.add("SSSSS");
        Assertions.assertThrows(IllegalArgumentException.class, () -> showBoardsActivityCommand.execute(params));
    }
    @Test
    public void executeCommand_ShouldReturnBoardActivity_WhenBoardExists() {
        Board board = new BoardImpl("Test Board");
        repository.addBoard(board);

        board.addLog("Event 1");
        EventLog eventLog1 = board.getLogs().get(0);

        board.addLog("Event 2");
        EventLog eventLog2 = board.getLogs().get(1);
        EventLog eventLog3 = board.getLogs().get(2);

        List<String> params = new ArrayList<>();
        params.add("Test Board");

        String result = showBoardsActivityCommand.execute(params);

        StringBuilder expected = new StringBuilder();
        expected.append("Board Test Board activity history:\n\n");
        expected.append(eventLog1.toString()).append("\n");
        expected.append(eventLog2.toString()).append("\n");
        expected.append(eventLog3.toString()).append("\n");
        expected.append("No more logs as of ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm:ss a")));

        Assertions.assertTrue(result.equals(expected.toString()));
        Assertions.assertTrue(result.contains("No more logs as of"));
    }
}
