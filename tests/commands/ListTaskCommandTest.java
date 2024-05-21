package commands;

import commands.listing.ListTasksCommand;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import models.MemberImpl;
import models.tasks.BugImpl;
import models.tasks.Contracts.Bug;
import models.tasks.Contracts.Story;
import models.tasks.Contracts.Task;
import models.tasks.StoryImpl;
import models.tasks.TaskImpl;
import models.tasks.enums.Priority;
import models.tasks.enums.Severity;
import models.tasks.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListTaskCommandTest {
    private TaskRepository repository;
    private ListTasksCommand listTasksCommand;

    @BeforeEach
    public void setUp() {
        repository = new TaskRepositoryImpl();
        listTasksCommand = new ListTasksCommand(repository);
        Bug bug1 = new BugImpl(1,
                "AAAAAAAAAAAAAAAAA",
                "XXXXXXXXXX",
                Priority.HIGH,
                Severity.MAJOR,
                new MemberImpl("Stefan1234"));
        Bug bug2 = new BugImpl(2,
                "BBBBBBBBBBBBBBBBB",
                "YYYYYYYYYYY",
                Priority.HIGH,
                Severity.MAJOR,
                new MemberImpl("Ivancho1234"));
        Story story1 = new StoryImpl(3,
                "CCCCCCCCCCCCCCCCC",
                "QQQQQQQQQQQQ",
                Priority.HIGH,
                Size.LARGE,
                new MemberImpl("Martin1234"));
        Story story2 = new StoryImpl(4,
                "DDDDDDDDDDDDDD",
                "ZZZZZZZZZZZ",
                Priority.HIGH,
                Size.LARGE,
                new MemberImpl("Boyan1234"));

        repository.addTask(bug1);
        repository.addTask(bug2);
        repository.addTask(story1);
        repository.addTask(story2);
    }
    @Test
    public void executeCommand_Should_ReturnNormalList_When_NoParameters() {
        List<String> params = new ArrayList<>();
        String result = listTasksCommand.executeCommand(params);
        String expected = "Task ID: 1, Title: AAAAAAAAAAAAAAAAA, Description: XXXXXXXXXX\n" +
                "Task ID: 2, Title: BBBBBBBBBBBBBBBBB, Description: YYYYYYYYYYY\n" +
                "Task ID: 3, Title: CCCCCCCCCCCCCCCCC, Description: QQQQQQQQQQQQ\n" +
                "Task ID: 4, Title: DDDDDDDDDDDDDD, Description: ZZZZZZZZZZZ\n";
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void executeCommand_Should_SortTasks() {
        List<String> params = new ArrayList<>();
        params.add("sort");
        String result = listTasksCommand.executeCommand(params);
        String expected = "Task ID: 1, Title: AAAAAAAAAAAAAAAAA, Description: XXXXXXXXXX\n" +
                "Task ID: 2, Title: BBBBBBBBBBBBBBBBB, Description: YYYYYYYYYYY\n" +
                "Task ID: 3, Title: CCCCCCCCCCCCCCCCC, Description: QQQQQQQQQQQQ\n" +
                "Task ID: 4, Title: DDDDDDDDDDDDDD, Description: ZZZZZZZZZZZ\n";
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void executeCommand_Should_FilterTasksByTitle() {
        List<String> params = new ArrayList<>();
        params.add("AAAAAAAAAAAAAAAAA");
        String result = listTasksCommand.executeCommand(params);
        String expected = "Task ID: 1, Title: AAAAAAAAAAAAAAAAA, Description: XXXXXXXXXX\n";
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void executeCommand_Sould_ReturnEmptyList_When_InvalidFilter() {
        List<String> params = new ArrayList<>();
        params.add("NonExistentTask");
        String result = listTasksCommand.executeCommand(params);
        String expected = "";
        Assertions.assertEquals(expected, result);
    }
}
