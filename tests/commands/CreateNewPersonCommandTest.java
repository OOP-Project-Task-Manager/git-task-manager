package commands;

import commands.contracts.Command;
import commands.create.CreateNewPersonCommand;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import models.MemberImpl;
import models.contracts.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateNewPersonCommandTest {
    private Command createNewPersonCommand;
    private TaskRepository repository;
    @BeforeEach
    public void before(){
        repository = new TaskRepositoryImpl();
        createNewPersonCommand = new CreateNewPersonCommand(repository);
    }
    @Test
    public void execute_Should_CreateAndAddInRepositoryNewPerson_When_ValidParameters() {
        List<String> params = new ArrayList<>();
        Member member = new MemberImpl("SSSSSSSSS");
        params.add(member.getName());
        createNewPersonCommand.execute(params);
        Assertions.assertEquals(1, repository.getMembers().size());
    }
    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        List<String> params = new ArrayList<>();
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createNewPersonCommand.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_DuplicateCategoryName() {
        List<String> params = new ArrayList<>();
        Member member = new MemberImpl("Pppppppp");
        params.add(member.getName());
        repository.createNewPerson(member.getName());
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createNewPersonCommand.execute(params));
    }

}
