package commands;

import commands.show.ShowTeamBoardsCommand;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import models.BoardImpl;
import models.TeamImpl;
import models.contracts.Board;
import models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowTeamBoardsCommandTest {
    ShowTeamBoardsCommand showTeamBoardsCommand;
    TaskRepository repository;
    @BeforeEach
    public void before(){
        repository = new TaskRepositoryImpl();
        showTeamBoardsCommand = new ShowTeamBoardsCommand(repository);
    }
    @Test
    public void executeCommand_Should_ThrowException_When_TeamDoesNotExist(){
        List<String> params = new ArrayList<>();
        params.add("SSSSSSSSS");
        Assertions.assertThrows(IllegalArgumentException.class, () -> showTeamBoardsCommand.execute(params));
    }
    @Test
    public void executeCommand_Should_ThrowException_When_InvalidArgumentsCount(){
        List<String> params = new ArrayList<>();
        params.add("SSSSSSSSS");
        params.add("SSSSSSSSS");
        params.add("SSSSSSSSS");
        params.add("SSSSSSSSS");
        Assertions.assertThrows(IllegalArgumentException.class, () -> showTeamBoardsCommand.execute(params));
    }
    @Test
    public void executeCommand_Should_ReturnBoards_When_TeamExist(){
        Team team = new TeamImpl("SSSSSSSS");
        repository.addTeam(team);
        Board board1 = new BoardImpl("XXXXXXXXX");
        Board board2 = new BoardImpl("YYYYYYYYY");
        team.addBoard(board1);
        team.addBoard(board2);
        List<String> params = new ArrayList<>();
        params.add("SSSSSSSS");
        String result = showTeamBoardsCommand.executeCommand(params);
        String expected = "Team SSSSSSSS boards:\nBoard name: XXXXXXXXX\nBoard name: YYYYYYYYY\n";
        Assertions.assertEquals(result, expected);
    }
}
