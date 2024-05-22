package commands;

import commands.add.AddPersonToTeamCommand;
import commands.change.ChangePriorityInBugCommand;
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

public class ChangePriorityInBugCommandTest {

    public static final int EXPECTED_ARGUMENTS_COUNT = 2;
    private TaskRepository repository;
    private Command changePriorityInBug;

    public static List<String> getList(int size) {
        return Arrays.asList(new String[size]);
    }


    @BeforeEach
    public void before() {
        repository = new TaskRepositoryImpl();
        changePriorityInBug = new ChangePriorityInBugCommand(repository);

    }


    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = getList(EXPECTED_ARGUMENTS_COUNT - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changePriorityInBug.execute(params));
    }


    @Test
    public void should_ThrowException_When_IdIsNotValidInteger() {
        // Arrange
        List<String> params = List.of("2a", "High");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changePriorityInBug.execute(params));
    }

    @Test
    public void should_ThrowException_When_StatusIsNotValid() {
        // Arrange
        List<String> params = List.of("2", "WrongStatus");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changePriorityInBug.execute(params));
    }


    @Test
    public void should_ThrowException_When_PriorityProvidedIsTheSameAsInTheBug() {
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
        List<String> taskParam = List.of("BugInIntelij", "ProjectWEntGoodButNo", "High", "Critical", "Nasko", "Tasks_OOP","Test","exit");
        task.execute(taskParam);

        List<String> params = List.of("1", "High");
        Assertions.assertThrows(IllegalArgumentException.class, () -> changePriorityInBug.execute(params));


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
        List<String> taskParam = List.of("BugInIntelij", "ProjectWEntGoodButNo", "High", "Critical", "Nasko", "Tasks_OOP","Test","exit");
        task.execute(taskParam);

        List<String> params = List.of("1", "Low");
        Assertions.assertDoesNotThrow(() -> changePriorityInBug.execute(params));

    }











}
