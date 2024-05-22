package commands;

import commands.contracts.Command;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import models.MemberImpl;
import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Team;
import models.tasks.Contracts.Feedback;
import models.tasks.FeedbackImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class AddCommentToTaskCommandTest {


    public static final int EXPECTED_ARGUMENTS_COUNT = 3;
    public static final String VALID_MESSAGE = "This is a valid message";
    public static final String INVALID_ID = "1S";
    public static final String VALID_ID = "1";
    private TaskRepository repository;
    private Member member = new MemberImpl("Nasko");

    private Command addCommentToTask;


    public static List<String> getList(int size) {
        return Arrays.asList(new String[size]);
    }

    @BeforeEach
    public void before() {
        repository = new TaskRepositoryImpl();
        addCommentToTask = new AddCommentToTaskCommand(repository);

    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = getList(EXPECTED_ARGUMENTS_COUNT - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addCommentToTask.execute(params));
    }


    @Test
    public void should_ThrowException_When_IdIsNotNumber() {
        // Arrange
        List<String> params = List.of(
                member.getName(),
                VALID_MESSAGE,
                INVALID_ID);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addCommentToTask.execute(params));
    }

    @Test
    public void should_AddComment_When_Parameters_Are_Valid() {


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


        // Arrange
        List<String> params = List.of(
                member.getName(),
                VALID_MESSAGE,
                VALID_ID);


        Assertions.assertDoesNotThrow(() -> addCommentToTask.execute(params));


    }


}
