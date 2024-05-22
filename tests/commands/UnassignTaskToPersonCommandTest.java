package commands;

import commands.assign_unassign.UnassignTaskToPersonCommand;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import models.MemberImpl;
import models.contracts.Member;
import models.tasks.BugImpl;
import models.tasks.Contracts.Bug;
import models.tasks.Contracts.Feedback;
import models.tasks.FeedbackImpl;
import models.tasks.enums.Priority;
import models.tasks.enums.Severity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UnassignTaskToPersonCommandTest {
    private TaskRepository repository;
    private UnassignTaskToPersonCommand unassignTaskToPersonCommand;

    @BeforeEach
    public void before() {
        repository = new TaskRepositoryImpl();
        unassignTaskToPersonCommand = new UnassignTaskToPersonCommand(repository);
    }
    @Test
    public void executeCommand_ShouldThrowException_WhenInvalidNumberOfArguments() {
        // Arrange
        List<String> params = new ArrayList<>();

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            unassignTaskToPersonCommand.execute(params);
        });
    }

    @Test
    public void executeCommand_ShouldThrowException_WhenTaskNotFound() {
        // Arrange
        repository.addMember(new MemberImpl("Assignee"));
        List<String> params = List.of("1", "Assignee");

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            unassignTaskToPersonCommand.execute(params);
        });
    }

    @Test
    public void executeCommand_ShouldThrowException_WhenMemberNotFound() {
        // Arrange
        Bug bug = new BugImpl(1,
                "XXXXXXXASDADA12345",
                "YYYYYYYYYYYYY12345",
                Priority.HIGH,
                Severity.MAJOR,
                new MemberImpl("SSSSSSS"));
        repository.addTask(bug);
        List<String> params = List.of("1", "SSSSSSS");

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            unassignTaskToPersonCommand.execute(params);
        });
    }
    @Test
    public void executeCommand_ShouldThrowException_WhenTaskNotAssignable() {
        Member member = new MemberImpl("SSSSSSSSSS");
        Feedback feedback = new FeedbackImpl(1,
                "XXXXXXXXX1234",
                "YYYYYYYYYYY1234",
                10);
        repository.addTask(feedback);
        repository.addMember(member);
        member.addTask(feedback);
        List<String> params = List.of("1", "SSSSSSSSSS");
        Assertions.assertThrows(IllegalArgumentException.class, () -> unassignTaskToPersonCommand.execute(params));
    }
    @Test
    public void executeCommand_Should_UnassignTask_When_ValidArguments(){
        Member member = new MemberImpl("SSSSSSSSSS");
        Bug bug = new BugImpl(1,
                "XXXXXXXXX1234",
                "YYYYYYYYYYY1234",
                Priority.HIGH,
                Severity.MAJOR,
                member);
        repository.addTask(bug);
        repository.addMember(member);
        member.addTask(bug);
        List<String> params = List.of("1", "SSSSSSSSSS");
        String result = unassignTaskToPersonCommand.execute(params);
        String expected = "Task with id 1 unassigned from SSSSSSSSSS successfully!";
        Assertions.assertEquals(result, expected);
    }
}
