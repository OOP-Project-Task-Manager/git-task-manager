package commands;

import commands.contracts.Command;
import commands.listing.ListBugsCommand;
import commands.listing.ListTasksCommand;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import models.MemberImpl;
import models.tasks.BugImpl;
import models.tasks.Contracts.Bug;
import models.tasks.Contracts.EventLog;
import models.tasks.Contracts.Story;
import models.tasks.enums.Priority;
import models.tasks.enums.Severity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        Bug bug3 = new BugImpl(3,
                "CCCCCCCCCCCCCCCCC",
                "QQQQQQQQQQQ",
                Priority.HIGH,
                Severity.MAJOR,
                new MemberImpl("Dimitar1234"));


        repository.addBug(bug1);
        repository.addBug(bug2);
        repository.addBug(bug3);
    }

    private String formatBugs(List<Bug> bugs) {
        StringBuilder result = new StringBuilder();
        for (Bug bug : bugs) {
            result.append("Bug ID: ").append(bug.getId())
                    .append(", Title: ").append(bug.getTitle())
                    .append(", Status: ").append(bug.getStatus())
                    .append(", Assignee: ").append(bug.getAssignee() != null ? bug.getAssignee().getName() : "Unassigned");
            for (EventLog eventLog : bug.getLogs()) {
                result.append(eventLog);
            }
            result.append("\n");
        }
        return result.toString();
    }


    private String generateExpectedResultForAllBugs() {
        return formatBugs(List.of(
                repository.findBugById(1),
                repository.findBugById(2),
                repository.findBugById(3)

        ));

    }

    private String generateExpectedResultForAllBugsSortedByTitle() {
        List<Bug> bugs = new ArrayList<>(List.of(repository.findBugById(1),
                repository.findBugById(2),
                repository.findBugById(3)));
        bugs.sort(Comparator.comparing(Bug::getTitle));
        return formatBugs(bugs);

    }

    private String generateExpectedResultForAllBugsSortedByPriority() {
        List<Bug> bugs = new ArrayList<>(List.of(repository.findBugById(1),
                repository.findBugById(2),
                repository.findBugById(3)));
        bugs.sort(Comparator.comparing(Bug::getPriority));
        return formatBugs(bugs);

    }

    private String generateExpectedResultForFilteredByStatusAndSortedByTitle() {
        List<Bug> bugs = new ArrayList<>(List.of(repository.findBugById(1),
                repository.findBugById(2),
                repository.findBugById(3)));

        bugs = bugs.stream().filter(bug -> bug.getStatus().toString().equalsIgnoreCase("Active")).collect(Collectors.toList());
        bugs.sort(Comparator.comparing(Bug::getTitle));

        return formatBugs(bugs);
    }


    @Test
    public void executeCommand_Should_FilterByStatus() {
        List<String> params = new ArrayList<>();
        params.add("Active");
        String result = listBugCommand.execute(params);

        String expected = generateExpectedResultForAllBugs();
        Assertions.assertEquals(result, expected);
    }

    @Test
    public void executeCommand_Should_SortByTitle() {
        List<String> params = new ArrayList<>();
        params.add("title");
        String result = listBugCommand.execute(params);
        String expected = generateExpectedResultForAllBugsSortedByTitle();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void executeCommand_Should_SortByPriority() {

        List<String> params = new ArrayList<>();
        params.add("priority");
        String result = listBugCommand.execute(params);
        String expected = generateExpectedResultForAllBugsSortedByPriority();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void executeCommand_Should_FilterByStatusAndSortByTitle() {
        List<String> params = new ArrayList<>();
        params.add("Active");
        params.add("title");
        String result = listBugCommand.execute(params);
        String expected = generateExpectedResultForFilteredByStatusAndSortedByTitle();
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
