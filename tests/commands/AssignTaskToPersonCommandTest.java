package commands;

import commands.add.AddPersonToTeamCommand;
import commands.assign_unassign.AssignTaskToPersonCommand;
import commands.contracts.Command;
import commands.create.*;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class AssignTaskToPersonCommandTest {

    public static final int EXPECTED_ARGUMENTS_COUNT = 3;

    private TaskRepository repository;
    private Command assignTaskToPerson;

    public static List<String> getList(int size) {
        return Arrays.asList(new String[size]);
    }


    @BeforeEach
    public void before() {
        repository = new TaskRepositoryImpl();
        assignTaskToPerson = new AssignTaskToPersonCommand(repository);

    }


    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = getList(EXPECTED_ARGUMENTS_COUNT - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> assignTaskToPerson.execute(params));
    }

    @Test
    public void should_ThrowException_When_TaskIsNotAssignable() {
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
        List<String> taskParam = List.of("BugInIntelij", "ProjectWEntGoodButNo", "High", "Critical", "Nasko", "Tasks_OOP","test","exit");
        task.execute(taskParam);


        Command feedback = new CreateNewFeedbackInBoardCommand(repository);
        List<String> feedbackParam = List.of("BugInIntelij", "AAAAAAAAAAA", "3", "Tasks_OOP");
        feedback.execute(feedbackParam);


        List<String> param = List.of("2", "Nasko");


        Assertions.assertThrows(IllegalArgumentException.class, () -> assignTaskToPerson.execute(param));
    }

    @Test
    public void should_AssignTask_When_ArgumentsAreValid() {

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
        List<String> taskParam = List.of("BugInIntelij", "ProjectWEntGoodButNo", "High", "Critical", "Nasko", "Tasks_OOP","test","exit");
        task.execute(taskParam);


        Command feedback = new CreateNewFeedbackInBoardCommand(repository);
        List<String> feedbackParam = List.of("BugInIntelij", "AAAAAAAAAAA", "3", "Tasks_OOP");
        feedback.execute(feedbackParam);

        List<String> param = List.of("1", "Nasko");

        Assertions.assertDoesNotThrow(() -> assignTaskToPerson.execute(param));

    }


}
