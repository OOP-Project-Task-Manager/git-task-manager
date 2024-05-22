package commands;

import commands.show.ShowTeamMembersCommand;
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

public class ShowTeamMembersCommandTest {
    ShowTeamMembersCommand showTeamMembersCommand;
    TaskRepository repository;
    @BeforeEach
    public void before(){
        repository = new TaskRepositoryImpl();
        showTeamMembersCommand = new ShowTeamMembersCommand(repository);
    }
    @Test
    public void executeCommand_Should_ThrowException_When_TeamDoesNotExist(){
        List<String> params = new ArrayList<>();
        params.add("SSSSSSSSS");
        Assertions.assertThrows(IllegalArgumentException.class, () -> showTeamMembersCommand.execute(params));
    }
    @Test
    public void executeCommand_Should_ThrowException_When_InvalidArgumentsCount(){
        List<String> params = new ArrayList<>();
        params.add("SSSSSSSSS");
        params.add("SSSSSSSSS");
        params.add("SSSSSSSSS");
        params.add("SSSSSSSSS");
        Assertions.assertThrows(IllegalArgumentException.class, () -> showTeamMembersCommand.execute(params));
    }
    @Test
    public void executeCommand_Should_ReturnMembers_When_TeamExist(){
        Team team = new TeamImpl("SSSSSSSS");
        repository.addTeam(team);
        Member member1 = new MemberImpl("XXXXXXXXX");
        Member member2 = new MemberImpl("YYYYYYYYY");
        team.addMember(member1);
        team.addMember(member2);
        List<String> params = new ArrayList<>();
        params.add("SSSSSSSS");
        String result = showTeamMembersCommand.executeCommand(params);
        String expected = "Team SSSSSSSS members:\nMember name: XXXXXXXXX\r\nMember name: YYYYYYYYY\r\n";
        Assertions.assertEquals(result, expected);
    }
}
