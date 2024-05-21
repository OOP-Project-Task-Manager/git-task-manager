package models;


import models.contracts.Member;
import models.tasks.Contracts.Task;
import models.tasks.StoryImpl;
import models.tasks.enums.Priority;
import models.tasks.enums.Size;
import models.tasks.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class StoryImplTest {
    @Test
    public void storyImpl_Should_ImplementStoryInterface(){
        StoryImpl story = new StoryImpl(
                1,
                "XXXXXXXXXX",
                "CCCCCCCCCC",
                Priority.HIGH,
                Size.LARGE,
                new MemberImpl("SSSSSSSSSS"));
        Assertions.assertTrue(story instanceof StoryImpl);
    }

    @Test
    public void storyImpl_Should_ImplementTaskInterface(){
        StoryImpl story = new StoryImpl(
                1,
                "XXXXXXXXXX",
                "CCCCCCCCCC",
                Priority.HIGH,
                Size.LARGE,
                new MemberImpl("SSSSSSSSSS"));
        Assertions.assertTrue(story instanceof Task);
    }
    @Test
    public void constructor_Should_ThrowException_When_TitleNameLengthOutOfBounds() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new StoryImpl(
                        1,
                        "X",
                        "XXXXXXXXXXXX",
                        Priority.HIGH,
                        Size.LARGE,
                        new MemberImpl("SSSSSSS")));
    }
    @Test
    public void constructor_Should_ThrowException_When_DescriptionLengthOutOfBounds() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new StoryImpl(
                        1,
                        "XXXXXXXXXXXXX",
                        "X",
                        Priority.HIGH,
                        Size.LARGE,
                        new MemberImpl("SSSSSSS")));
    }
    @Test
    public void constructor_Should_CreateNewCar_When_ParametersAreCorrect() {
        // Arrange, Act
        StoryImpl story = new StoryImpl(
                1,
                "XXXXXXXXXX",
                "CCCCCCCCCC",
                Priority.HIGH,
                Size.LARGE,
                new MemberImpl("SSSSSSSSSS"));

        // Assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, story.getId()),
                () -> Assertions.assertEquals("XXXXXXXXXX", story.getTitle()),
                () -> Assertions.assertEquals("CCCCCCCCCC", story.getDescription()),
                () -> Assertions.assertEquals(Priority.HIGH, story.getPriority()),
                () -> Assertions.assertEquals(Size.LARGE, story.getSize()),
                () -> Assertions.assertEquals(new MemberImpl("SSSSSSSSSS"), story.getAssignee())
        );
    }
    @Test
    public void setPriority_Should_UpdatePriority_When_Valid() {
        // Arrange
        StoryImpl story = new StoryImpl(
                1,
                "XXXXXXXXXX",
                "CCCCCCCCCC",
                Priority.HIGH,
                Size.LARGE,
                new MemberImpl("SSSSSSSSSS"));

        // Act
        story.setPriority(Priority.LOW);

        // Assert
        Assertions.assertEquals(Priority.LOW, story.getPriority());
    }
    @Test
    public void setSize_Should_UpdateSize_When_Valid() {
        // Arrange
        StoryImpl story = new StoryImpl(
                1,
                "XXXXXXXXXX",
                "CCCCCCCCCC",
                Priority.HIGH,
                Size.LARGE,
                new MemberImpl("SSSSSSSSSS"));

        // Act
        story.setSize(Size.SMALL);

        // Assert
        Assertions.assertEquals(Size.SMALL, story.getSize());
    }
    @Test
    public void setStatus_Should_UpdateStatus_When_Valid() {
        // Arrange
        StoryImpl story = new StoryImpl(
                1,
                "XXXXXXXXXX",
                "CCCCCCCCCC",
                Priority.HIGH,
                Size.LARGE,
                new MemberImpl("SSSSSSSSSS"));

        // Act
        story.setStatus(Status.DONE);

        // Assert
        Assertions.assertEquals(Status.DONE, story.getStatus());
    }
    @Test
    public void setStatus_Should_ThrowException_When_NotValidInput(){
        StoryImpl story = new StoryImpl(
                1,
                "XXXXXXXXXX",
                "CCCCCCCCCC",
                Priority.HIGH,
                Size.LARGE,
                new MemberImpl("SSSSSSSSSS"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> story.setStatus(Status.ACTIVE));
    }
    @Test
    void setAssignee_Should_UpdateAssignee() {
        Member member = new MemberImpl("SSSSSSSSSS");
        StoryImpl story = new StoryImpl(
                1,
                "XXXXXXXXXX",
                "CCCCCCCCCC",
                Priority.HIGH,
                Size.LARGE,
                member);
        story.setAssignee(member);

        Assertions.assertEquals(member, story.getAssignee());
    }
    @Test
    void setAssignee_Should_LogChange_When_AssigneeIsUpdated() {
        Member member1 = new MemberImpl("SSSSSSSSSS");
        Member member2 = new MemberImpl("CCCCCCCCCC");

        StoryImpl story = new StoryImpl(
                1,
                "XXXXXXXXXX",
                "CCCCCCCCCC",
                Priority.HIGH,
                Size.LARGE,
                member1);

        story.setAssignee(member1);

        // Act
        story.setAssignee(member2);

        // Assert
        Assertions.assertEquals(member2, story.getAssignee());
        Assertions.assertTrue(story.getLogs().get(2).getDescription().contains("Assignee changed from SSSSSSSSSS to CCCCCCCCCC"));
    }
    @Test
    public void getAssignee_Should_ReturnAssignee() {
        Member member = new MemberImpl("SSSSSSSSSS");
        StoryImpl story = new StoryImpl(
                1,
                "XXXXXXXXXX",
                "CCCCCCCCCC",
                Priority.HIGH,
                Size.LARGE,
                member);
        Member result = story.getAssignee();

        Assertions.assertEquals(member, result);
    }
    @Test
    void getAssignee_Should_ThrowException_When_AssigneeIsNull() {
        StoryImpl story = new StoryImpl(1,
                "SSSSSSSSSS",
                "XXXXXXXXXX",
                Priority.HIGH,
                Size.LARGE,
                null);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            story.getAssignee();
        });

        Assertions.assertEquals("Not assigned to any member", exception.getMessage());
    }
    @Test
    public void log_Should_RecordPriorityChange() {
        // Arrange
        StoryImpl story = new StoryImpl(
                1,
                "XXXXXXXXXX",
                "CCCCCCCCCC",
                Priority.HIGH,
                Size.LARGE,
                new MemberImpl("SSSSSSSSSS"));

        // Act
        story.setPriority(Priority.MEDIUM);
        List<String> logDescriptions = story.getLogs().stream()
                .map(log -> log.getDescription())
                .collect(Collectors.toList());

        // Assert
        Assertions.assertTrue(logDescriptions.contains("Priority changed from High Priority to Medium Priority"));
    }
    @Test
    public void log_Should_RecordSizeChange() {
        // Arrange
        StoryImpl story = new StoryImpl(
                1,
                "XXXXXXXXXX",
                "CCCCCCCCCC",
                Priority.HIGH,
                Size.LARGE,
                new MemberImpl("SSSSSSSSSS"));

        // Act
        story.setSize(Size.SMALL);
        List<String> logDescription = story.getLogs().stream()
                .map(log -> log.getDescription())
                .collect(Collectors.toList());

        // Assert
        Assertions.assertTrue(logDescription.contains("Size changed from Large to Small"));
    }
    @Test
    public void log_Should_RecordStatusChange() {
        // Arrange
        StoryImpl story = new StoryImpl(
                1,
                "XXXXXXXXXX",
                "CCCCCCCCCC",
                Priority.HIGH,
                Size.LARGE,
                new MemberImpl("SSSSSSSSSS"));

        // Act
        story.setStatus(Status.IN_PROGRESS);
        List<String> logDescription = story.getLogs().stream()
                .map(log -> log.getDescription())
                .collect(Collectors.toList());

        // Assert
        Assertions.assertTrue(logDescription.contains("Status changed from Not_Done to In_Progress"));
    }

}
