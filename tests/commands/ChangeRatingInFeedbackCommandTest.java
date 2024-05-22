package commands;

import commands.change.ChangeRatingInFeedbackCommand;
import commands.contracts.Command;
import commands.create.CreateNewBoardInTeamCommand;
import commands.create.CreateNewFeedbackInBoardCommand;
import commands.create.CreateNewTeamCommand;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ChangeRatingInFeedbackCommandTest {


    public static final int EXPECTED_ARGUMENTS_COUNT = 2;
    private TaskRepository repository;
    private Command changeRatingInFeedback;

    public static List<String> getList(int size) {
        return Arrays.asList(new String[size]);
    }


    @BeforeEach
    public void before() {
        repository = new TaskRepositoryImpl();
        changeRatingInFeedback = new ChangeRatingInFeedbackCommand(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = getList(EXPECTED_ARGUMENTS_COUNT - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeRatingInFeedback.execute(params));
    }

    @Test
    public void should_ThrowException_When_IdIsNotValidInteger() {
        // Arrange
        List<String> params = List.of("2a", "2");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeRatingInFeedback.execute(params));
    }


    @Test
    public void should_ThrowException_When_IsNotValidInteger() {
        // Arrange
        List<String> params = List.of("2", "2a");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeRatingInFeedback.execute(params));
    }


    @Test
    public void should_ThrowException_When_RatingProvidedIsTheSameAsInTheFeedback() {
        Command team = new CreateNewTeamCommand(repository);
        List<String> teamParam = List.of("Project_OOP");
        team.execute(teamParam);

        Command board = new CreateNewBoardInTeamCommand(repository);
        List<String> boardParam = List.of("Tasks_OOP", "Project_OOP");
        board.execute(boardParam);


        Command task = new CreateNewFeedbackInBoardCommand(repository);
        List<String> taskParam = List.of("Feedback_inProject", "I hate writing tests", "3", "Tasks_OOP");
        task.execute(taskParam);

        List<String> params = List.of("1", "3");
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeRatingInFeedback.execute(params));
    }

    @Test
    public void should_ChangeRating_When_ArgumentsAreValid() {
        Command team = new CreateNewTeamCommand(repository);
        List<String> teamParam = List.of("Project_OOP");
        team.execute(teamParam);

        Command board = new CreateNewBoardInTeamCommand(repository);
        List<String> boardParam = List.of("Tasks_OOP", "Project_OOP");
        board.execute(boardParam);


        Command task = new CreateNewFeedbackInBoardCommand(repository);
        List<String> taskParam = List.of("Feedback_inProject", "I hate writing tests", "3", "Tasks_OOP");
        task.execute(taskParam);

        List<String> params = List.of("1", "2");
        Assertions.assertDoesNotThrow(() -> changeRatingInFeedback.execute(params));

    }


}
