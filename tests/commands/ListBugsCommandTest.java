package commands;

import commands.contracts.Command;
import commands.listing.ListBugsCommand;
import commands.listing.ListTasksCommand;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import models.MemberImpl;
import models.tasks.BugImpl;
import models.tasks.Contracts.Bug;
import models.tasks.enums.Priority;
import models.tasks.enums.Severity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListBugsCommandTest {


    private TaskRepository repository;
    private ListBugsCommand listBugCommand;

    @BeforeEach
    public void setUp() {
        repository = new TaskRepositoryImpl();
        listBugCommand = new ListBugsCommand(repository);
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

        repository.addBug(bug1);
        repository.addBug(bug2);
    }


    @Test
    public void executeCommand_Should_FilterByStatus() {
        List<String> params = new ArrayList<>();
        params.add("Active");
        String result = listBugCommand.execute(params);

        String expected = "Bug ID: 1, Title: AAAAAAAAAAAAAAAAA, Status: Active, Assignee: Stefan1234\n" +
                "Bug ID: 2, Title: BBBBBBBBBBBBBBBBB, Status: Active, Assignee: Ivancho1234\n";
        Assertions.assertEquals(result, expected);
    }

    @Test
    public void executeCommand_Should_SortByTitle() {
        List<String> params = new ArrayList<>();
        params.add("title");
        String result = listBugCommand.execute(params);
        String expected = "Bug ID: 1, Title: AAAAAAAAAAAAAAAAA, Status: Active, Assignee: Stefan1234\n" +
                "Bug ID: 2, Title: BBBBBBBBBBBBBBBBB, Status: Active, Assignee: Ivancho1234\n";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void executeCommand_Should_SortByPriority() {

        List<String> params = new ArrayList<>();
        params.add("priority");
        String result = listBugCommand.execute(params);
        String expected = "Bug ID: 1, Title: AAAAAAAAAAAAAAAAA, Status: Active, Assignee: Stefan1234\n" +
                "Bug ID: 2, Title: BBBBBBBBBBBBBBBBB, Status: Active, Assignee: Ivancho1234\n";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void executeCommand_Should_FilterByStatusAndSortByTitle() {
        List<String> params = new ArrayList<>();
        params.add("Active");
        params.add("title");
        String result = listBugCommand.execute(params);
        String expected = "Bug ID: 1, Title: AAAAAAAAAAAAAAAAA, Status: Active, Assignee: Stefan1234\n" +
                "Bug ID: 2, Title: BBBBBBBBBBBBBBBBB, Status: Active, Assignee: Ivancho1234\n";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void executeCommand_Should_ReturnEmptyList_When_InvalidStatus() {
        List<String> params = new ArrayList<>();
        params.add("INVALID_STATUS");
        String result = listBugCommand.execute(params);
        String expected = "";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void executeCommand_Should_ReturnEmptyList_When_InvalidAssignee() {
        List<String> params = new ArrayList<>();
        params.add("NonExistentAssignee");
        String result = listBugCommand.execute(params);
        String expected = "";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void executeCommand_Should_ReturnEmptyList_When_InvalidSortCriteria() {
        List<String> params = new ArrayList<>();
        params.add("INVALID_SORT_CRITERIA");
        String result = listBugCommand.execute(params);
        String expected = "";
        Assertions.assertEquals(expected, result);
    }


}
