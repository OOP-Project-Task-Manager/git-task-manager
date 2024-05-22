package commands;

import commands.show.ShowTeamsCommand;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import models.TeamImpl;
import models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowTeamsCommandTest {
    ShowTeamsCommand showTeamsCommand;
    TaskRepository repository;
    @BeforeEach
    public void before(){
        repository = new TaskRepositoryImpl();
        showTeamsCommand = new ShowTeamsCommand(repository);
    }
    @Test
    public void executeCommand_Should_ReturnTeams(){
        Team team1 = new TeamImpl("BABABABABBA");
        Team team2 = new TeamImpl("CACACACACAC");
        repository.addTeam(team1);
        repository.addTeam(team2);
        List<String> params = new ArrayList<>();
        String result = showTeamsCommand.execute(params);
        String output = "Teams:\nTeam BABABABABBA\nTeam CACACACACAC\n";
        Assertions.assertEquals(output, result);
    }
    @Test
    public void executeCommand_Should_ReturnEmptyList_WhenNoPeople(){
        List<String> params = new ArrayList<>();
        String result = showTeamsCommand.execute(params);
        String output = "Teams:\n";
        Assertions.assertEquals(output, result);
    }
}
