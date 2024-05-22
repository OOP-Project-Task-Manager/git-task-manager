package commands;

import commands.add.AddPersonToTeamCommand;
import commands.contracts.Command;
import commands.create.CreateNewPersonCommand;
import commands.create.CreateNewTeamCommand;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class AddPersonToTeamCommandTests {


    public static final int EXPECTED_ARGUMENTS_COUNT = 2;

    private TaskRepository repository;

    private Command addPersonToTeam;

    @BeforeEach
    public void before() {
        repository = new TaskRepositoryImpl();
        addPersonToTeam = new AddPersonToTeamCommand(repository);

    }

    public static List<String> getList(int size) {
        return Arrays.asList(new String[size]);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = getList(EXPECTED_ARGUMENTS_COUNT - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addPersonToTeam.execute(params));
    }


    @Test
    public void should_AddPerson_When_Parameters_Are_Valid() {


        Command team = new CreateNewTeamCommand(repository);
        List<String> teamParam = List.of("Project_OOP");
        team.execute(teamParam);

        Command author = new CreateNewPersonCommand(repository);
        List<String> authorParam = List.of("Nasko");
        author.execute(authorParam);

        // Arrange
        List<String> params = List.of(
                "Nasko",
                "Project_OOP");


        Assertions.assertDoesNotThrow(() -> addPersonToTeam.execute(params));


    }

}
