package commands;

import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import models.BoardImpl;
import models.MemberImpl;
import models.contracts.Board;
import models.contracts.Member;
import models.tasks.Contracts.EventLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowPersonActivityCommandTest {
    ShowPersonActivityCommand showPersonActivityCommand;
    TaskRepository repository;
    @BeforeEach
    public void before(){
        repository = new TaskRepositoryImpl();
        showPersonActivityCommand = new ShowPersonActivityCommand(repository);
    }
    @Test
    public void executeCommand_Should_ThrowException_When_InvalidArgumentsCount(){
        List<String> params = Arrays.asList(new String[3]);
        Assertions.assertThrows(IllegalArgumentException.class, () -> showPersonActivityCommand.execute(params));
    }
    @Test
    public void executeCommand_Should_ThrowException_When_PersonDoesNotExist(){
        List<String> params = new ArrayList<>();
        params.add("SSSSS");
        Assertions.assertThrows(IllegalArgumentException.class, () -> showPersonActivityCommand.execute(params));
    }
    @Test
    public void executeCommand_ShouldReturnPersonActivity_WhenPersonExists() {
        Member member = new MemberImpl("SSSSSSSSSSS");
        repository.addMember(member);

        member.addLog("Event1");
        EventLog eventLog1 = member.getLogs().get(0);

        member.addLog("Event 2");
        EventLog eventLog2 = member.getLogs().get(1);
        EventLog eventLog3 = member.getLogs().get(2);

        List<String> params = new ArrayList<>();
        params.add("SSSSSSSSSSS");

        String result = showPersonActivityCommand.execute(params);

        StringBuilder expected = new StringBuilder();
        expected.append("Member SSSSSSSSSSS activity history:\n\n");
        expected.append(eventLog1.toString()).append("\n");
        expected.append(eventLog2.toString()).append("\n");
        expected.append(eventLog3.toString()).append("\n");
        expected.append("No more logs as of ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm:ss a")));

        Assertions.assertTrue(result.equals(expected.toString()));
        Assertions.assertTrue(result.contains("No more logs as of"));
    }
}
