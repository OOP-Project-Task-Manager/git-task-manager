package models;


import models.contracts.Member;
import models.tasks.Contracts.Task;
import models.tasks.EventLogImpl;
import models.tasks.StoryImpl;
import models.tasks.enums.Priority;
import models.tasks.enums.Size;
import models.tasks.enums.StatusStory;
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
    public void constructor_Should_ThrowException_When_DESCRIPTIONLengthOutOfBounds() {
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
        story.setStatus(StatusStory.DONE);

        // Assert
        Assertions.assertEquals(StatusStory.DONE, story.getStatus());
    }
    @Test
    public void getAssignee_Should_ReturnAssignee() {
        // Arrange
        Member member = new MemberImpl("SSSSSSSSSS");
        StoryImpl story = new StoryImpl(
                1,
                "XXXXXXXXXX",
                "CCCCCCCCCC",
                Priority.HIGH,
                Size.LARGE,
                member);

        // Act
        Member result = story.getAssignee();

        // Assert
        Assertions.assertEquals(member, result);
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

        // Assert
        Assertions.assertTrue(story.getLogs().contains("Priority changed from High Priority to Medium Priority"));
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

        // Assert
        Assertions.assertTrue(story.getLogs().contains("Size changed from LARGE to SMALL"));
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
        story.setStatus(StatusStory.IN_PROGRESS);

        // Assert
        Assertions.assertTrue(story.getLogs().contains("Status changed from NOT_DONE to IN_PROGRESS"));
    }

}
