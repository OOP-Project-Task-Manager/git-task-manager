package models;

import models.tasks.Contracts.Task;
import models.tasks.StoryImpl;
import models.tasks.enums.Priority;
import models.tasks.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class BoardImplTest {
    @Test
    public void boardImpl_Should_ImplementBoardInterface() {

        BoardImpl board = new BoardImpl("SSSSSSSS");

        Assertions.assertTrue(board instanceof BoardImpl);
    }
    @Test
    public void constructor_Should_ThrowException_When_BoardNameLengthOutOfBounds() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new BoardImpl("SSS"));
    }

    @Test
    public void constructor_Should_CreateNewBoard_When_ParametersAreCorrect() {
        BoardImpl board = new BoardImpl("SSSSSSSS");

        Assertions.assertEquals("SSSSSSSS", board.getName());
    }
    @Test
    public void setName_Should_UpdateName_When_Valid() {
        BoardImpl board = new BoardImpl("SSSSSSSS");

        board.setName("NewName");

        Assertions.assertEquals("NewName", board.getName());
    }

    @Test
    public void setName_Should_ThrowException_When_NameLengthOutOfBounds() {
        BoardImpl board = new BoardImpl("SSSSSSSS");

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                board.setName("SS"));
    }
    @Test
    public void addTask_Should_AddTaskToBoard() {
        BoardImpl board = new BoardImpl("SSSSSSSS");
        MemberImpl member = new MemberImpl("SSSSSSSS");
        Task task = new StoryImpl(
                1,
                "TaskTitle1",
                "TaskDescription",
                Priority.HIGH,
                Size.LARGE,
                member
        );
        board.addTask(task);

        Assertions.assertTrue(board.getTasks().contains(task));
    }
    @Test
    public void addTask_Should_LogTaskAddition() {
        BoardImpl board = new BoardImpl("SSSSSSSS");
        MemberImpl member = new MemberImpl("SSSSSSSS");
        Task task = new StoryImpl(
                1,
                "TaskTitle1",
                "TaskDescription",
                Priority.HIGH,
                Size.LARGE,
                member
        );
        board.addTask(task);
        List<String> logDescriptions = board.getLogs().stream()
                .map(log -> log.getDescription())
                .collect(Collectors.toList());

        // Assert
        Assertions.assertTrue(logDescriptions.contains("Task TaskTitle1 added to board SSSSSSSS"));
    }
    @Test
    public void removeTask_Should_RemoveTaskFromMember() {
        // Arrange
        MemberImpl member = new MemberImpl("SSSSSSSS");
        BoardImpl board = new BoardImpl("SSSSSSSS");
        Task task = new StoryImpl(
                1,
                "TaskTitle1",
                "TaskDescription",
                Priority.HIGH,
                Size.LARGE,
                member
        );
        board.addTask(task);

        // Act
        board.removeTask(task);

        // Assert
        Assertions.assertFalse(board.getTasks().contains(task));
    }
    @Test
    public void removeTask_Should_LogTaskRemoval() {
        // Arrange
        MemberImpl member = new MemberImpl("SSSSSSSS");
        BoardImpl board = new BoardImpl("SSSSSSSS");
        Task task = new StoryImpl(
                1,
                "TaskTitle1",
                "TaskDescription",
                Priority.HIGH,
                Size.LARGE,
                member
        );
        board.addTask(task);

        // Act
        board.removeTask(task);

        // Extract log descriptions
        List<String> logDescriptions = board.getLogs().stream()
                .map(log -> log.getDescription())
                .collect(Collectors.toList());

        // Assert
        Assertions.assertTrue(logDescriptions.contains("Task TaskTitle1 removed from board SSSSSSSS"));
    }
    @Test
    public void removeTask_Should_ThrowException_When_TaskDoesNotExist() {
        // Arrange
        MemberImpl member = new MemberImpl("SSSSSSSS");
        BoardImpl board = new BoardImpl("SSSSSSSS");
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
                board.removeTask(task));
    }
}
