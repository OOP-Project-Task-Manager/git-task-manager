package commands;

import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import models.BoardImpl;
import models.TeamImpl;
import models.contracts.Board;
import models.contracts.Team;
import models.tasks.Contracts.EventLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowTeamsActivityCommandTest {
    ShowTeamsActivityCommand showTeamsActivityCommand;
    TaskRepository repository;
    @BeforeEach
    public void before(){
        repository = new TaskRepositoryImpl();
        showTeamsActivityCommand = new ShowTeamsActivityCommand(repository);
    }

    @Test
    public void executeCommand_Should_ThrowException_When_InvalidArgumentsCount(){
        List<String> params = Arrays.asList(new String[3]);
        Assertions.assertThrows(IllegalArgumentException.class, () -> showTeamsActivityCommand.execute(params));
    }
    @Test
    public void executeCommand_Should_ThrowException_When_TeamDoesNotExist(){
        List<String> params = new ArrayList<>();
        params.add("SSSSS");
        Assertions.assertThrows(IllegalArgumentException.class, () -> showTeamsActivityCommand.execute(params));
    }
    @Test
    public void executeCommand_ShouldReturnBoardActivity_WhenBoardExists() {
        Team team = new TeamImpl("Test Team");
        repository.addTeam(team);

        team.addLog("Event 1");
        EventLog eventLog1 = team.getLogs().get(0);

        team.addLog("Event 2");
        EventLog eventLog2 = team.getLogs().get(1);
        EventLog eventLog3 = team.getLogs().get(2);

        List<String> params = new ArrayList<>();
        params.add("Test Team");

        String result = showTeamsActivityCommand.execute(params);

        StringBuilder expected = new StringBuilder();
        expected.append("Team Test Team activity history:\n\n");
        expected.append(eventLog1.toString()).append("\n");
        expected.append(eventLog2.toString()).append("\n");
        expected.append(eventLog3.toString()).append("\n");
        expected.append("No more logs as of ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm:ss a")));

        Assertions.assertTrue(result.equals(expected.toString()));
        Assertions.assertTrue(result.contains("No more logs as of"));
    }
}
