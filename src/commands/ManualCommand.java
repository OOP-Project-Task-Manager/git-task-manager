package commands;

import commands.add.AddCommentToTaskCommand;
import commands.add.AddPersonToTeamCommand;
import commands.assign_unassign.AssignTaskToPersonCommand;
import commands.assign_unassign.UnassignTaskToPersonCommand;
import commands.change.*;
import commands.create.*;
import commands.enums.CommandType;
import commands.listing.*;
import commands.show.*;
import core.contracts.TaskRepository;
import utilities.ParsingHelpers;

import java.util.List;

public class ManualCommand extends BaseCommand {

    public static final String NO_SUCH_COMMAND = "No such command";

    public ManualCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        String input = parameters.get(0);

        CommandType commandType = ParsingHelpers.tryParseEnum(input, CommandType.class, NO_SUCH_COMMAND);
        switch (commandType) {
            case CREATENEWTEAM:
                return "CREATENEWTEAM {Team_Name 5-15 characters} ";
            case CREATENEWBOARDINTEAM:
                return "CREATENEWBOARDINTEAM {Board_Name 5-10 characters} {Existing_Team_Name}";
            case CREATENEWPERSON:
                return "CREATENEWPERSON {Person_Name 5-15 characters}";
            case ADDPERSONTOTEAM:
                return "ADDPERSONTOTEAM {Existing_Person_Name} {Existing_Team_Name}";
            case CREATENEWFEEDBACKINBOARD:
                return """
                        CREATENEWFEEDBACKINBOARD {Title 10-100 characters} {Description 10-500} {Rating: Integer} {Board: Existing_Board_Name}""";
            case CREATENEWBUGINBOARD:
                return "CREATENEWBUGINBOARD {Title 10-100 characters} {Description 10-500} {Priority: High,Medium,Low} {Severity:Critical,Major,Minor} {Assignee: Existing_Person_Name} {Board: Existing_Board_Name}  ";
            case CREATENEWSTORYINBOARD:
                return "CREATENEWSTORYINBOARD {Title 10-100 characters} {Description 10-500} {Priority: High,Medium,Low} {Size:Large,Medium,Small} {Assignee: Existing_Person_Name} {Board: Existing_Board_Name}";
            case ADDCOMMENTTOTASK:
                return "ADDCOMMENTTOTASK {Author: Existing_Person_Name from the same team} {Message: String} {Task id: Integer}";
            case ASSIGNTASKTOPERSON:
                return "ASSIGNTASKTOPERSON {Task id:Integer} {Existing_Person_Name}";
            case CHANGEPRIORITYINBUG:
                return "CHANGEPRIORITYINBUG {Task id:Integer} {New_Priority: High,Medium,Low}";
            case CHANGEPRIORITYINSTORY:
                return "CHANGEPRIORITYSTORY {Task id:Integer} {New_Priority: High,Medium,Low}";
            case CHANGERATINGINFEEDBACK:
                return "CHANGERATINGINFEEDBACK {Task id:Integer} {New_Rating: Integer}";
            case CHANGESEVERITYINBUG:
                return "CHANGESEVERITYINBUG {Task id:Integer} {New_Severity:Critical,Major,Minor}";
//            case CHANGESIZEINSTORY:
//                return new ChangeSizeInStoryCommand(taskRepository);
//            case CHANGESTATUSINBUG:
//                return new ChangeStatusInBugCommand(taskRepository);
//            case CHANGESTATUSINFEEDBACK:
//                return new ChangeStatusInFeedbackCommand(taskRepository);
//            case CHANGESTATUSINSTORY:
//                return new ChangeStatusInStoryCommand(taskRepository);
//            case SHOWALLPEOPLE:
//                return new ShowAllPeopleCommand(taskRepository);
//            case SHOWBOARDSACTIVITY:
//                return new ShowBoardsActivityCommand(taskRepository);
//            case SHOWPERSONACTIVITY:
//                return new ShowPersonActivityCommand(taskRepository);
//            case SHOWTEAMBOARDS:
//                return new ShowTeamBoardsCommand(taskRepository);
//            case SHOWTEAMMEMBERS:
//                return new ShowTeamMembersCommand(taskRepository);
//            case SHOWTEAMSACTIVITY:
//                return new ShowTeamsActivityCommand(taskRepository);
//            case SHOWTEAMS:
//                return new ShowTeamsCommand(taskRepository);
//            case UNASSIGNTASKTOPERSON:
//                return new UnassignTaskToPersonCommand(taskRepository);
//            case LISTBUGS:
//                return new ListBugsCommand(taskRepository);
//            case LISTFEEDBACKS:
//                return new ListFeedbackCommand(taskRepository);
//            case LISTSTORIES:
//                return new ListStoriesCommand(taskRepository);
//            case LISTTASKS:
//                return new ListTasksCommand(taskRepository);
//            case LISTTASKSWITHASSIGNEE:
//                return new ListTasksWithAssigneeCommand(taskRepository);
            default:
                throw new IllegalArgumentException(NO_SUCH_COMMAND);


        }
    }
}
