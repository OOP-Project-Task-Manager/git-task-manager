package core;

import commands.AddPersonToTeamCommand;
import commands.CreateNewBoardInTeamCommand;
import commands.CreateNewPersonCommand;
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
            case CREATENEWBOARDINTEAM:
                return new CreateNewBoardInTeamCommand(taskRepository);
            case CREATENEWPERSON:
                return new CreateNewPersonCommand(taskRepository);
            case ADDPERSONTOTEAM:
                return new AddPersonToTeamCommand(taskRepository);
            default:
                throw new IllegalArgumentException(NO_SUCH_COMMAND);


        }
    }
}
