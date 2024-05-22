package commands;

import commands.contracts.Command;
import commands.create.CreateNewBoardInTeamCommand;
import commands.create.CreateNewTeamCommand;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class CreateNewBoardInTeamCommandTest {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private TaskRepository repository;
    private Command createNewBoardInTeam;

    public static List<String> getList(int size) {
        return Arrays.asList(new String[size]);
    }

    @BeforeEach
    public void before() {
        repository = new TaskRepositoryImpl();
        createNewBoardInTeam = new CreateNewBoardInTeamCommand(repository);
    }



    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createNewBoardInTeam.execute(params));
    }


    @Test
    public void should_CreateBoard_When_ArgumentsAreValid(){

        Command team = new CreateNewTeamCommand(repository);
        List<String> teamParam = List.of("Project_OOP");
        team.execute(teamParam);

        List<String> boardParam = List.of("Tasks_OOP", "Project_OOP");

        Assertions.assertDoesNotThrow(()->createNewBoardInTeam.execute(boardParam));


    }




}
