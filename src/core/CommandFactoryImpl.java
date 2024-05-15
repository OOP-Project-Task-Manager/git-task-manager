package core;

import commands.CreateNewTeamCommand;
import commands.contracts.Command;
import commands.enums.CommandType;
import core.contracts.CommandFactory;
import core.contracts.TaskRepository;
import utilities.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {


    public static final String NO_SUCH_COMMAND = "No such command";

    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, TaskRepository taskRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class, NO_SUCH_COMMAND);
        switch (commandType) {
            case CREATENEWTEAM:
                return new CreateNewTeamCommand(taskRepository);
            default:
                throw new IllegalArgumentException(NO_SUCH_COMMAND);


        }
    }
}
