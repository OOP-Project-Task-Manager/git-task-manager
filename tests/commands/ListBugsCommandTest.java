package commands;

import commands.listing.ListTasksCommand;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import models.MemberImpl;
import models.tasks.BugImpl;
import models.tasks.Contracts.Bug;
import models.tasks.enums.Priority;
import models.tasks.enums.Severity;
import org.junit.jupiter.api.BeforeEach;

public class ListBugsCommandTest {


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

        repository.addBug(bug1);
        repository.addTask(bug2);
    }





}
