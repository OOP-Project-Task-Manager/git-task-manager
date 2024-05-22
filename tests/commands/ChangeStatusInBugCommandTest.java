package commands;

import commands.contracts.Command;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ChangeStatusInBugCommandTest {

    public static final int EXPECTED_ARGUMENTS_COUNT = 2;
    private TaskRepository repository;
    private Command changeStatusinBug;

    public static List<String> getList(int size) {
        return Arrays.asList(new String[size]);
    }


    @BeforeEach
    public void before() {
        repository = new TaskRepositoryImpl();
        changeStatusinBug = new ChangeStatusInBugCommand(repository);

    }


    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = getList(EXPECTED_ARGUMENTS_COUNT - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeStatusinBug.execute(params));
    }


    @Test
    public void should_ThrowException_When_IdIsNotValidInteger() {
        // Arrange
        List<String> params = List.of("2a", "Active");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeStatusinBug.execute(params));
    }

    @Test
    public void should_ThrowException_When_StatusIsNotValid() {
        // Arrange
        List<String> params = List.of("2", "AAAAAAA");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeStatusinBug.execute(params));
    }

    @Test
    public void should_ThrowException_When_StatusProvidedIsTheSameAsInTheBug() {
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

        List<String> params = List.of("1", "Active");
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeStatusinBug.execute(params));
    }

    @Test
    public void should_ChangeStatus_When_ArgumentsAreValid() {
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

        List<String> params = List.of("1", "Done");
        Assertions.assertDoesNotThrow(() -> changeStatusinBug.execute(params));

    }


}
