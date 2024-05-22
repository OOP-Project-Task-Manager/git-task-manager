package commands;

import commands.add.AddPersonToTeamCommand;
import commands.contracts.Command;
import commands.create.CreateNewBoardInTeamCommand;
import commands.create.CreateNewBugInBoardCommand;
import commands.create.CreateNewPersonCommand;
import commands.create.CreateNewTeamCommand;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class CreateNewBugInBoardCommandTest {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 7;
    private TaskRepository repository;
    private Command createBug;

    public static List<String> getList(int size) {
        return Arrays.asList(new String[size]);
    }

    @BeforeEach
    public void create() {
        repository = new TaskRepositoryImpl();
        createBug = new CreateNewBugInBoardCommand(repository);
    }

    
    @Test
    public void should_ThrowException_When_PriorityIsNotValid() {
        List<String> params = List.of("BugInIntelij", "ProjectWEntGoodButNo", "X", "Critical", "Nasko", "Tasks_OOP", "Test", "exit");

        Assertions.assertThrows(IllegalArgumentException.class, () -> createBug.execute(params));

    }

    @Test
    public void should_ThrowException_When_SeverityIsNotValid() {
        List<String> params = List.of("BugInIntelij", "ProjectWEntGoodButNo", "High", "X", "Nasko", "Tasks_OOP", "Test", "exit");

        Assertions.assertThrows(IllegalArgumentException.class, () -> createBug.execute(params));

    }

    @Test
    public void should_CreateBug_When_ArgumentsAreValid() {
        Command team = new CreateNewTeamCommand(repository);
        List<String> teamParam = List.of("Project_OOP");
        team.execute(teamParam);

        Command board = new CreateNewBoardInTeamCommand(repository);
        List<String> boardParam = List.of("Tasks_OOP", "Project_OOP");
        board.execute(boardParam);

        Command author = new CreateNewPersonCommand(repository);
        List<String> authorParam = List.of("Nasko");
        author.execute(authorParam);

        Command addPerson = new AddPersonToTeamCommand(repository);
        List<String> addParam = List.of("Nasko", "Project_OOP");
        addPerson.execute(addParam);

        List<String> params = List.of("BugInIntelij", "ProjectWEntGoodButNo", "High", "Critical", "Nasko", "Tasks_OOP", "Test", "exit");

        Assertions.assertDoesNotThrow(() -> createBug.execute(params));

    }


}
