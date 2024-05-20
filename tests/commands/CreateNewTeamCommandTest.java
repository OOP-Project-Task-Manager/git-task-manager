package commands;

import commands.contracts.Command;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import models.MemberImpl;
import models.TeamImpl;
import models.contracts.Member;
import models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateNewTeamCommandTest {
    private Command createNewTeamCommand;
    private TaskRepository repository;
    @BeforeEach
    public void before(){
        repository = new TaskRepositoryImpl();
        createNewTeamCommand = new CreateNewTeamCommand(repository);
    }
    @Test
    public void execute_Should_CreateAndAddInRepositoryNewTeam_When_ValidParameters() {
        List<String> params = new ArrayList<>();
        Team team = new TeamImpl("SSSSSSSSS");
        params.add(team.getName());
        createNewTeamCommand.execute(params);
        Assertions.assertEquals(1, repository.getTeams().size());
    }
    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        List<String> params = new ArrayList<>();
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createNewTeamCommand.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_DuplicateCategoryName() {
        List<String> params = new ArrayList<>();
        Team team = new TeamImpl("SSSSSSSSS");
        params.add(team.getName());
        repository.createTeam(team.getName());
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createNewTeamCommand.execute(params));
    }
}
