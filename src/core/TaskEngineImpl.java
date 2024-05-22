package core;

import commands.contracts.Command;
import core.contracts.CommandFactory;
import core.contracts.TaskEngine;
import core.contracts.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskEngineImpl implements TaskEngine {
    private static final String TERMINATION_COMMAND = "Exit";
    private static final String EMPTY_COMMAND_ERROR = "Command cannot be empty.";
    private static final String MAIN_SPLIT_SYMBOL = " ";
    private static final String REPORT_SEPARATOR = "####################";


    private final CommandFactory commandFactory;
    private final TaskRepository taskRepository;


    public TaskEngineImpl() {
        this.commandFactory = new CommandFactoryImpl();
        this.taskRepository = new TaskRepositoryImpl();
    }


    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String inputLine = scanner.nextLine();
                if (inputLine.isBlank()) {
                    print(EMPTY_COMMAND_ERROR);
                    continue;
                }
                if (inputLine.equalsIgnoreCase(TERMINATION_COMMAND)) {
                    break;
                }
                processCommand(inputLine);
            } catch (Exception ex) {
                if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
                    print(ex.getMessage());
                } else {
                    print(ex.toString());
                }
            }
        }
    }


    private void processCommand(String inputLine) {
        String commandName = extractCommandName(inputLine);
        List<String> parameters = extractCommandParameters(inputLine);
        Command command = commandFactory.createCommandFromCommandName(commandName, taskRepository);
        String executionResult = command.execute(parameters);
        print(executionResult);
    }


    private String extractCommandName(String inputLine) {
        return inputLine.split(" ")[0];
    }


    private List<String> extractCommandParameters(String inputLine) {
        String[] commandParts = inputLine.split(" ");
        List<String> parameters = new ArrayList<>();
        for (int i = 1; i < commandParts.length; i++) {
            parameters.add(commandParts[i]);
        }
        return parameters;
    }


    private void print(String result) {
        System.out.println(result);
        System.out.println(REPORT_SEPARATOR);
    }
}
