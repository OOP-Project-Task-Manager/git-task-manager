package commands;

import commands.change.ChangeStatusInFeedbackCommand;
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

public class ChangeStatusInFeedbackCommandTest {
    public static final int EXPECTED_ARGUMENTS_COUNT = 2;
    private TaskRepository repository;
    private Command changeStatusInFeedback;

    public static List<String> getList(int size) {
        return Arrays.asList(new String[size]);
    }


    @BeforeEach
    public void before() {
        repository = new TaskRepositoryImpl();
        changeStatusInFeedback = new ChangeStatusInFeedbackCommand(repository);

    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = getList(EXPECTED_ARGUMENTS_COUNT - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeStatusInFeedback.execute(params));
    }


    @Test
    public void should_ThrowException_When_IdIsNotValidInteger() {
        // Arrange
        List<String> params = List.of("2a", "Active");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeStatusInFeedback.execute(params));
    }


    @Test
    public void should_ThrowException_When_StatusIsNotValid() {
        // Arrange
        List<String> params = List.of("2", "AAAAAAA");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeStatusInFeedback.execute(params));
    }

    @Test
    public void sshould_ThrowException_When_StatusProvidedIsTheSameAsInTheFeedback() {
        Command team = new CreateNewTeamCommand(repository);
        List<String> teamParam = List.of("Project_OOP");
        team.execute(teamParam);

        Command board = new CreateNewBoardInTeamCommand(repository);
        List<String> boardParam = List.of("Tasks_OOP", "Project_OOP");
        board.execute(boardParam);


        Command task = new CreateNewFeedbackInBoardCommand(repository);
        List<String> taskParam = List.of("Feedback_AAAA", "ProjectWEntGoodButNo", "3", "Tasks_OOP");
        task.execute(taskParam);

        List<String> params = List.of("1", "New");
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeStatusInFeedback.execute(params));
    }


    @Test
    public void should_ChangeStatus_When_ArgumentsAreValid() {
        Command team = new CreateNewTeamCommand(repository);
        List<String> teamParam = List.of("Project_OOP");
        team.execute(teamParam);

        Command board = new CreateNewBoardInTeamCommand(repository);
        List<String> boardParam = List.of("Tasks_OOP", "Project_OOP");
        board.execute(boardParam);


        Command task = new CreateNewFeedbackInBoardCommand(repository);
        List<String> taskParam = List.of("Feedback_AAAA", "ProjectWEntGoodButNo", "3", "Tasks_OOP");
        task.execute(taskParam);

        List<String> params = List.of("1", "Done");
        Assertions.assertDoesNotThrow(() -> changeStatusInFeedback.execute(params));

    }


}
