package commands;

import commands.listing.ListTasksWithAssigneeCommand;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import models.MemberImpl;
import models.contracts.Assignable;
import models.tasks.BugImpl;
import models.tasks.Contracts.Bug;
import models.tasks.Contracts.Story;
import models.tasks.StoryImpl;
import models.tasks.enums.Priority;
import models.tasks.enums.Severity;
import models.tasks.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ListTasksWithAssigneeCommandTest {
    private TaskRepository repository;
    private ListTasksWithAssigneeCommand listTasksWithAssigneeCommand;

    @BeforeEach
    public void setUp() {
        // Create a simple TaskRepository with mock data
        repository = new TaskRepositoryImpl();

        // Initialize the command with the repository
        listTasksWithAssigneeCommand = new ListTasksWithAssigneeCommand(repository);

        // Mock data
        Bug task1 = new BugImpl(2,
                "TaskAAAAAAA",
                "DescriptionA",
                Priority.HIGH,
                Severity.MAJOR,
                new MemberImpl("BobbyG"));
        Story task2 = new StoryImpl(1,
                "TaskBBBBBBB",
                "DescriptionB",
                Priority.HIGH, Size.LARGE,
                new MemberImpl("Aliceandra"));
        Bug task3 = new BugImpl(3,
                "TaskCCCCCCCC",
                "DescriptionC",
                Priority.HIGH,
                Severity.MAJOR,
                new MemberImpl("Johnathan"));
        Story task4 = new StoryImpl(4,
                "TaskDDDDDDDD",
                "DescriptionD",
                Priority.HIGH,
                Size.LARGE,
                new MemberImpl("Charlie"));

        repository.addBug(task1);
        repository.addStory(task2);
        repository.addBug(task3);
        repository.addStory(task4);
    }
    @Test
    public void executeCommand_NoParameters() {
        List<String> params = Arrays.asList("no", "no", "no");
        String result = listTasksWithAssigneeCommand.executeCommand(params);
        String expected = "Task ID: 2, Title: TaskAAAAAAA, Description: DescriptionA Status: Active Assignee: BobbyG\n" +
                "Task ID: 3, Title: TaskCCCCCCCC, Description: DescriptionC Status: Active Assignee: Johnathan\n" +
                "Task ID: 1, Title: TaskBBBBBBB, Description: DescriptionB Status: Not Done Assignee: Aliceandra\n" +
                "Task ID: 4, Title: TaskDDDDDDDD, Description: DescriptionD Status: Not Done Assignee: Charlie\n";
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void executeCommand_Should_SortTasks() {
        List<String> params = Arrays.asList("no", "no", "sort");
        String result = listTasksWithAssigneeCommand.executeCommand(params);
        String expected = "Task ID: 2, Title: TaskAAAAAAA, Description: DescriptionA Status: Active Assignee: BobbyG\n" +
                "Task ID: 1, Title: TaskBBBBBBB, Description: DescriptionB Status: Not Done Assignee: Aliceandra\n" +
                "Task ID: 3, Title: TaskCCCCCCCC, Description: DescriptionC Status: Active Assignee: Johnathan\n" +
                "Task ID: 4, Title: TaskDDDDDDDD, Description: DescriptionD Status: Not Done Assignee: Charlie\n";
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void executeCommand_Should_FilterByAssignee() {
        List<String> params = Arrays.asList("Aliceandra", "no", "no");
        String result = listTasksWithAssigneeCommand.executeCommand(params);
        String expected = "Task ID: 1, Title: TaskBBBBBBB, Description: DescriptionB Status: Not Done Assignee: Aliceandra\n";
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void executeCommand_Should_FilterByStatus() {
        List<String> params = Arrays.asList("no", "ACTIVE", "no");
        String result = listTasksWithAssigneeCommand.executeCommand(params);
        String expected = "Task ID: 2, Title: TaskAAAAAAA, Description: DescriptionA Status: Active Assignee: BobbyG\n" +
                "Task ID: 3, Title: TaskCCCCCCCC, Description: DescriptionC Status: Active Assignee: Johnathan\n";
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void executeCommand_Should_FilterByAssigneeAndStatus() {
        List<String> params = Arrays.asList("Aliceandra", "ACTIVE", "no");
        String result = listTasksWithAssigneeCommand.executeCommand(params);
        String expected = "Task ID: 1, Title: Task A, Description: Description A Status: ACTIVE Assignee: Alice\n" +
                "Task ID: 3, Title: Task C, Description: Description C Status: ACTIVE Assignee: Alice\n";
        Assertions.assertEquals(expected, result);
    }
}
