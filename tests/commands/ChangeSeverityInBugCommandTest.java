package commands;

import commands.add.AddPersonToTeamCommand;
import commands.change.ChangeSeverityInBugCommand;
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

public class ChangeSeverityInBugCommandTest {

    public static final int EXPECTED_ARGUMENTS_COUNT = 2;
    private TaskRepository repository;
    private Command changeSeverityInBug;

    public static List<String> getList(int size) {
        return Arrays.asList(new String[size]);
    }


    @BeforeEach
    public void before() {
        repository = new TaskRepositoryImpl();
        changeSeverityInBug = new ChangeSeverityInBugCommand(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = getList(EXPECTED_ARGUMENTS_COUNT - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeSeverityInBug.execute(params));
    }


    @Test
    public void should_ThrowException_When_IdIsNotValidInteger() {
        // Arrange
        List<String> params = List.of("2a", "Critical");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeSeverityInBug.execute(params));
    }


    @Test
    public void should_ThrowException_When_SeverityIsNotValid() {
        // Arrange
        List<String> params = List.of("2", "NotValid");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeSeverityInBug.execute(params));
    }

    @Test
    public void should_ThrowException_When_SeverityIsTheSameInTheBug() {
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

        Command task = new CreateNewBugInBoardCommand(repository);
        List<String> taskParam = List.of("BugInIntelij", "ProjectWEntGoodButNo", "High", "Critical", "Nasko", "Tasks_OOP");
        task.execute(taskParam);

        List<String> params = List.of("1", "Critical");
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeSeverityInBug.execute(params));

    }


    @Test
    public void should_ChangeSeverity_When_ArgumentsAreValid() {
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

        Command task = new CreateNewBugInBoardCommand(repository);
        List<String> taskParam = List.of("BugInIntelij", "ProjectWEntGoodButNo", "High", "Critical", "Nasko", "Tasks_OOP");
        task.execute(taskParam);

        List<String> params = List.of("1", "Major");
        Assertions.assertDoesNotThrow(() -> changeSeverityInBug.execute(params));

    }


}
