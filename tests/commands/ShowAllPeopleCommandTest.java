package commands;

import commands.show.ShowAllPeopleCommand;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import models.MemberImpl;
import models.contracts.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowAllPeopleCommandTest {
    ShowAllPeopleCommand showAllPeopleCommand;
    TaskRepository repository;
    @BeforeEach
    public void before(){
        repository = new TaskRepositoryImpl();
        showAllPeopleCommand = new ShowAllPeopleCommand(repository);
    }
    @Test
    public void executeCommand_Should_ReturnAllPeople(){
        Member member1 = new MemberImpl("Veselin");
        Member member2 = new MemberImpl("Lyuben");
        repository.addMember(member1);
        repository.addMember(member2);
        List<String> params = new ArrayList<>();
        String result = showAllPeopleCommand.execute(params);
        String output = "People:\nPerson Veselin\r\nPerson Lyuben\r\n";
        Assertions.assertEquals(output, result);
    }
    @Test
    public void executeCommand_Should_ReturnEmptyList_WhenNoPeople(){
        List<String> params = new ArrayList<>();
        String result = showAllPeopleCommand.execute(params);
        String output = "People:\n";
        Assertions.assertEquals(output, result);
    }
}
