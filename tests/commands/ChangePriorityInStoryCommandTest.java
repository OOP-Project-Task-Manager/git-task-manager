package commands;

import commands.contracts.Command;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ChangePriorityInStoryCommandTest {

    public static final int EXPECTED_ARGUMENTS_COUNT = 2;
    private TaskRepository repository;
    private Command changePriorityInStory;

    public static List<String> getList(int size) {
        return Arrays.asList(new String[size]);
    }


    @BeforeEach
    public void before() {
        repository = new TaskRepositoryImpl();
        changePriorityInStory = new ChangePriorityInStoryCommand(repository);

    }


    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = getList(EXPECTED_ARGUMENTS_COUNT - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changePriorityInStory.execute(params));
    }


    @Test
    public void should_ThrowException_When_IdIsNotValidInteger() {
        // Arrange
        List<String> params = List.of("2a", "High");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changePriorityInStory.execute(params));
    }

    @Test
    public void should_ThrowException_When_StatusIsNotValid() {
        // Arrange
        List<String> params = List.of("2", "WrongStatus");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changePriorityInStory.execute(params));
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

        Command task = new CreateNewStoryInBoardCommand(repository);
        List<String> taskParam = List.of("StoryInIntelij", "ProjectWEntGoodButNo", "High", "Large", "Nasko", "Tasks_OOP");
        task.execute(taskParam);

        List<String> params = List.of("1", "High");
        Assertions.assertThrows(IllegalArgumentException.class, () -> changePriorityInStory.execute(params));


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

        Command task = new CreateNewStoryInBoardCommand(repository);
        List<String> taskParam = List.of("StoryInIntelij", "ProjectWEntGoodButNo", "High", "Large", "Nasko", "Tasks_OOP");
        task.execute(taskParam);

        List<String> params = List.of("1", "Low");
        Assertions.assertDoesNotThrow(() -> changePriorityInStory.execute(params));

    }


}
