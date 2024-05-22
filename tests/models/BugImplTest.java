package models;

import models.contracts.Member;
import models.tasks.BugImpl;
import models.tasks.Contracts.Bug;
import models.tasks.Contracts.EventLog;
import models.tasks.Contracts.Task;
import models.tasks.StoryImpl;
import models.tasks.enums.Priority;
import models.tasks.enums.Severity;
import models.tasks.enums.Size;
import models.tasks.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class BugImplTest {

    public static final Status[] BUG_POSSIGLBE_STATUS = {Status.ACTIVE, Status.DONE};
    public static final String VALID_TITLE = "XXXXXXXXXX";
    public static final String VALID_DESCRIPTION = "CCCCCCCCCC";


    BugImpl bug = new BugImpl(1, VALID_TITLE, VALID_DESCRIPTION, Priority.HIGH, Severity.MINOR, new MemberImpl("Johny"));


    @Test
    public void bugImpl_Should_ImplementBugInterface() {

        Assertions.assertInstanceOf(Bug.class, bug);
    }

    @Test
    public void BugImpl_Should_ImplementTaskInterface() {

        Assertions.assertInstanceOf(Task.class, bug);
    }

    @Test
    public void constructor_Should_ThrowException_When_TitleNameLengthOutOfBounds() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new BugImpl(
                        1,
                        "X",
                        "XXXXXXXXXXXX",
                        Priority.HIGH,
                        Severity.MINOR,
                        new MemberImpl("SSSSSSS")));
    }

    @Test
    public void constructor_Should_ThrowException_When_DescriptionLengthOutOfBounds() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new BugImpl(
                        1,
                        "XXXXXXXXXXXXX",
                        "X",
                        Priority.HIGH,
                        Severity.MINOR,
                        new MemberImpl("SSSSSSS")));
    }

    ////////////////////
    @Test
    public void setPriority_Should_UpdatePriority_When_Valid() {
        // Arrange
        BugImpl statusBug = new BugImpl(1, VALID_TITLE, VALID_DESCRIPTION, Priority.HIGH, Severity.MINOR, new MemberImpl("Johny"));

        // Act
        statusBug.setPriority(Priority.LOW);

        // Assert
        Assertions.assertEquals(Priority.LOW, statusBug.getPriority());
    }

    @Test
    public void setSeverity_Should_UpdateSeverity_When_Valid() {
        // Arrange
        BugImpl severityBug = new BugImpl(1, VALID_TITLE, VALID_DESCRIPTION, Priority.HIGH, Severity.MINOR, new MemberImpl("Johny"));
        // Act
        severityBug.setSeverity(Severity.MAJOR);

        // Assert
        Assertions.assertEquals(Severity.MAJOR, severityBug.getSeverity());
    }

    @Test
    public void setStatus_Should_UpdateStatus_When_Valid() {
        // Arrange
        BugImpl statusBug = new BugImpl(1, VALID_TITLE, VALID_DESCRIPTION, Priority.HIGH, Severity.MINOR, new MemberImpl("Johny"));

        // Act
        statusBug.setStatus(Status.DONE);

        // Assert
        Assertions.assertEquals(Status.DONE, statusBug.getStatus());
    }

    @Test
    public void log_Should_RecordPriorityChange() {
        // Arrange
        BugImpl logBug = new BugImpl(1, VALID_TITLE, VALID_DESCRIPTION, Priority.HIGH, Severity.MINOR, new MemberImpl("Johny"));

        // Act
        logBug.setPriority(Priority.MEDIUM);
        List<String> logDescriptions = logBug.getLogs().stream()
                .map(EventLog::getDescription)
                .toList();

        // Assert
        Assertions.assertTrue(logDescriptions.contains("Priority of task with id:1 changed from High Priority to Medium Priority"));
    }

    @Test
    public void log_Should_RecordSeverityChange() {
        // Arrange
        BugImpl logBug = new BugImpl(1, VALID_TITLE, VALID_DESCRIPTION, Priority.HIGH, Severity.MINOR, new MemberImpl("Johny"));

        // Act
        logBug.setSeverity(Severity.MAJOR);
        List<String> logDescription = logBug.getLogs().stream()
                .map(EventLog::getDescription)
                .toList();

        // Assert
        Assertions.assertTrue(logDescription.contains("Severity of task with id:1 changed from Minor to Major"));
    }

    @Test
    public void log_Should_RecordStatusChange() {
        // Arrange
        BugImpl logBug = new BugImpl(1, VALID_TITLE, VALID_DESCRIPTION, Priority.HIGH, Severity.MINOR, new MemberImpl("Johny"));

        // Act
        logBug.setStatus(Status.DONE);
        List<String> logDescription = logBug.getLogs().stream()
                .map(EventLog::getDescription)
                .toList();

        // Assert
        Assertions.assertTrue(logDescription.contains("Status of task with id:1 changed from Active to Done"));
    }


}
