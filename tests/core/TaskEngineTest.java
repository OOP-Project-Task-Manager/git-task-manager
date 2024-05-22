package core;

import core.contracts.TaskEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class TaskEngineTest {
    private TaskEngineImpl taskEngine;
    @BeforeEach
    public void before(){
        taskEngine = new TaskEngineImpl();
    }

    @Test
    public void start_Should_Stop_When_Empty(){
        String testInput = "\nExit\n";
        ByteArrayInputStream input = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(input);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        taskEngine.start();
        String[] outputLines = output.toString().split(System.lineSeparator());
        Assertions.assertTrue(outputLines[0].contains("Command cannot be empty."));
    }
    @Test
    public void start_Should_Stop_When_TerminationCommand(){
        String terminationCommand = "Exit\n";
        ByteArrayInputStream input = new ByteArrayInputStream(terminationCommand.getBytes());
        System.setIn(input);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        taskEngine.start();
        String[] outputLines = output.toString().split(System.lineSeparator());
        Assertions.assertTrue(outputLines[0].isEmpty() || !outputLines[0].contains("Command cannot be empty."));
    }

    @Test
    public void start_Should_Work_When_ValidCommand(){
        String testInput = "CREATENEWTEAM team1\nExit\n";
        ByteArrayInputStream input = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(input);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        taskEngine.start();
        String outputString = output.toString();
        Assertions.assertTrue(outputString.contains("Team team1 created successfully!\r\n" +
                "####################\r\n"));
    }
    @Test
    public void testStartWithInvalidArguments() {
        String simulatedInput = "CREATENEWTEAM\nExit\n";
        ByteArrayInputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(input);

        // Capture the output
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Start the task engine
        taskEngine.start();

        // Verify that the exception message from ValidationHelper is printed
        String outputString = output.toString();
        Assertions.assertTrue(outputString.contains("Invalid number of arguments. Expected: 1, Received: 0"));
    }

}
