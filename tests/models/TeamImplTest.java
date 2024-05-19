package models;

import models.tasks.Contracts.Task;
import models.tasks.StoryImpl;
import models.tasks.enums.Priority;
import models.tasks.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class TeamImplTest {
    @Test
    public void teamImpl_Should_ImplementTeamInterface() {

        TeamImpl team = new TeamImpl("SSSSSSSS");

        Assertions.assertTrue(team instanceof TeamImpl);
    }
    @Test
    public void constructor_Should_ThrowException_When_TeamNameLengthOutOfBounds() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new TeamImpl("SSS"));
    }
    @Test
    public void constructor_Should_CreateNewTeam_When_ParametersAreCorrect() {
        TeamImpl team = new TeamImpl("SSSSSSSS");

        Assertions.assertEquals("SSSSSSSS", team.getName());
    }
    @Test
    public void setName_Should_UpdateName_When_Valid() {
        TeamImpl team = new TeamImpl("SSSSSSSS");

        team.setName("NewName");

        Assertions.assertEquals("NewName", team.getName());
    }
    @Test
    public void setName_Should_ThrowException_When_NameLengthOutOfBounds() {
        TeamImpl team = new TeamImpl("SSSSSSSS");

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                team.setName("SS"));
    }
    @Test
    public void addMember_Should_AddMemberToTeam() {
        MemberImpl member = new MemberImpl("SSSSSSSS");
        TeamImpl team = new TeamImpl("XXXXXXXXXXXX");
        team.addMember(member);

        Assertions.assertTrue(team.getMembers().contains(member));
    }
    @Test
    public void removeMember_Should_RemoveMemberToTeam() {
        MemberImpl member = new MemberImpl("SSSSSSSS");
        TeamImpl team = new TeamImpl("XXXXXXXXXXXX");
        team.addMember(member);
        team.removeMember(member);

        Assertions.assertFalse(team.getMembers().contains(member));
    }
    @Test
    public void addBoard_Should_AddBoardToTeam() {
        BoardImpl board = new BoardImpl("CCCCCCCC");
        TeamImpl team = new TeamImpl("XXXXXXXXXXXX");
        team.addBoard(board);

        Assertions.assertTrue(team.getBoards().contains(board));
    }
    @Test
    public void removeBoard_Should_RemoveBoardToTeam() {
        BoardImpl board = new BoardImpl("CCCCCCCC");
        TeamImpl team = new TeamImpl("XXXXXXXXXXXX");
        team.addBoard(board);
        team.removeBoard(board);

        Assertions.assertFalse(team.getBoards().contains(board));
    }
    @Test
    public void addMember_Should_LogMemberAddition() {
        MemberImpl member = new MemberImpl("SSSSSSSS");
        TeamImpl team = new TeamImpl("XXXXXXXXXXXX");
        team.addMember(member);
        List<String> logDescriptions = team.getLogs().stream()
                .map(log -> log.getDescription())
                .collect(Collectors.toList());

        // Assert
        Assertions.assertTrue(logDescriptions.contains("Member SSSSSSSS added to team XXXXXXXXXXXX"));
    }
    @Test
    public void removeMember_Should_LogMemberAddition() {
        MemberImpl member = new MemberImpl("SSSSSSSS");
        TeamImpl team = new TeamImpl("XXXXXXXXXXXX");
        team.addMember(member);
        team.removeMember(member);
        List<String> logDescriptions = team.getLogs().stream()
                .map(log -> log.getDescription())
                .collect(Collectors.toList());

        // Assert
        Assertions.assertTrue(logDescriptions.contains("Member SSSSSSSS removed from team XXXXXXXXXXXX"));
    }
    @Test
    public void addBoard_Should_LogBoardAddition() {
        BoardImpl board = new BoardImpl("SSSSSSSS");
        TeamImpl team = new TeamImpl("XXXXXXXXXXXX");
        team.addBoard(board);
        List<String> logDescriptions = team.getLogs().stream()
                .map(log -> log.getDescription())
                .collect(Collectors.toList());

        // Assert
        Assertions.assertTrue(logDescriptions.contains("Board SSSSSSSS added to team XXXXXXXXXXXX"));
    }
    @Test
    public void removeBoard_Should_LogMemberAddition() {
        BoardImpl board = new BoardImpl("SSSSSSSS");
        TeamImpl team = new TeamImpl("XXXXXXXXXXXX");
        team.addBoard(board);
        team.removeBoard(board);
        List<String> logDescriptions = team.getLogs().stream()
                .map(log -> log.getDescription())
                .collect(Collectors.toList());

        // Assert
        Assertions.assertTrue(logDescriptions.contains("Board SSSSSSSS removed from team XXXXXXXXXXXX"));
    }
}
