package models;

import models.tasks.Contracts.Task;
import models.tasks.StoryImpl;
import models.tasks.enums.Priority;
import models.tasks.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class MemberImplTest {
    @Test
    public void memberImpl_Should_ImplementMemberInterface() {

        MemberImpl member = new MemberImpl("SSSSSSSS");

        Assertions.assertTrue(member instanceof MemberImpl);
    }

    @Test
    public void constructor_Should_ThrowException_When_MemberNameLengthOutOfBounds() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new MemberImpl("SSS"));
    }

    @Test
    public void constructor_Should_CreateNewMember_When_ParametersAreCorrect() {
        MemberImpl member = new MemberImpl("SSSSSSSS");

        Assertions.assertEquals("SSSSSSSS", member.getName());
    }

    @Test
    public void setName_Should_UpdateName_When_Valid() {
        MemberImpl member = new MemberImpl("SSSSSSSS");

        member.setName("NewName");

        Assertions.assertEquals("NewName", member.getName());
    }

    @Test
    public void setName_Should_ThrowException_When_NameLengthOutOfBounds() {
        MemberImpl member = new MemberImpl("SSSSSSSS");

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                member.setName("SS"));
    }

    @Test
    public void addTask_Should_AddTaskToMember() {
        MemberImpl member = new MemberImpl("SSSSSSSS");
        Task task = new StoryImpl(
                1,
                "TaskTitle1",
                "TaskDescription",
                Priority.HIGH,
                Size.LARGE,
                member
        );
        member.addTask(task);

        Assertions.assertTrue(member.getTasks().contains(task));
    }

    @Test
    public void addTask_Should_LogTaskAddition() {
        MemberImpl member = new MemberImpl("SSSSSSSS");
        Task task = new StoryImpl(
                1,
                "TaskTitle1",
                "TaskDescription",
                Priority.HIGH,
                Size.LARGE,
                member
        );
        member.addTask(task);
        List<String> logDescriptions = member.getLogs().stream()
                .map(log -> log.getDescription())
                .collect(Collectors.toList());

        // Assert
        Assertions.assertTrue(logDescriptions.contains("Task with name TaskTitle1 and id 1 added to member SSSSSSSS"));
    }
    @Test
    public void removeTask_Should_RemoveTaskFromMember() {
        // Arrange
        MemberImpl member = new MemberImpl("SSSSSSSS");
        Task task = new StoryImpl(
                1,
                "TaskTitle1",
                "TaskDescription",
                Priority.HIGH,
                Size.LARGE,
                member
        );
        member.addTask(task);

        // Act
        member.removeTask(task);

        // Assert
        Assertions.assertFalse(member.getTasks().contains(task));
    }
    @Test
    public void removeTask_Should_LogTaskRemoval() {
        // Arrange
        MemberImpl member = new MemberImpl("SSSSSSSS");
        Task task = new StoryImpl(
                1,
                "TaskTitle1",
                "TaskDescription",
                Priority.HIGH,
                Size.LARGE,
                member
        );
        member.addTask(task);

        // Act
        member.removeTask(task);

        // Extract log descriptions
        List<String> logDescriptions = member.getLogs().stream()
                .map(log -> log.getDescription())
                .collect(Collectors.toList());

        // Assert
        Assertions.assertTrue(logDescriptions.contains("Task TaskTitle1 removed from member SSSSSSSS"));
    }
    @Test
    public void removeTask_Should_ThrowException_When_TaskDoesNotExist() {
        // Arrange
        MemberImpl member = new MemberImpl("SSSSSSSS");
        Task task = new StoryImpl(
                1,
                "TaskTitle1",
                "TaskDescription",
                Priority.HIGH,
                Size.LARGE,
                member
        );

        // Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                member.removeTask(task));
    }
}
