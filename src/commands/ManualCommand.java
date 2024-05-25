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
                return "CREATENEWFEEDBACKINBOARD {Title 10-100 characters} {Description 10-500} {Rating: Integer} {Board: Existing_Board_Name}";
            case CREATENEWBUGINBOARD:
                return "CREATENEWBUGINBOARD {Title 10-100 characters} {Description 10-500} {Priority: High,Medium,Low} {Severity:Critical,Major,Minor} {Assignee: Existing_Person_Name} {Board: Existing_Board_Name} {Steps_To_Reproduce: Strings terminate with 'exit'}  ";
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
            case CHANGESIZEINSTORY:
                return "CHANGESIZEINSTORY {Task id: Integer} {New_Size: Large,Medium,Small}";
            case CHANGESTATUSINBUG:
                return "CHANGESTATUSINBUG {Task id: Integer} {New_Status: Active,Done}";
            case CHANGESTATUSINFEEDBACK:
                return "CHANGESTATUSINFEEDBACK {Task id: Integer} {New_Status: New,Unscheduled,Scheduled }";
            case CHANGESTATUSINSTORY:
                return "CHANGESTATUSINSTORY {Task id: Integer} {New_Status: Not_Done,In_Progress,Done}";
            case SHOWALLPEOPLE:
                return "SHOWALLPEOPLE";
            case SHOWBOARDSACTIVITY:
                return "SHOWBOARDSACTIVITY {Existing_Board_Name}";
            case SHOWPERSONACTIVITY:
                return "SHOWPERSONACTIVITY {Existing_Person_Name}";
            case SHOWTEAMBOARDS:
                return "SHOWTEAMBOARDS {Existing_Team_Name}";
            case SHOWTEAMMEMBERS:
                return "SHOWTEAMMEMBERS {Existing_Team_Name}";
            case SHOWTEAMSACTIVITY:
                return "SHOWTEAMSACTIVITY {Existing_Team_Name}";
            case SHOWTEAMS:
                return "SHOWTEAMS";
            case UNASSIGNTASKTOPERSON:
                return "UNASSIGNTASKTOPERSON {Task id: Integer} {Assignee_Name}";
            case LISTBUGS:
                return "LISTBUGS {Status: {Active or Done} or Assignee: {Assignee_Name} or SortCriteria: {title, priority, severity} {Max 3 Criteria}}";
            case LISTFEEDBACKS:
                return "LISTFEEDBACKS {Status: {New or Unscheduled or Scheduled} or SortCriteria: {title or rating} {Max 2 Criteria}}";
            case LISTSTORIES:
                return "LISTSTORIES {Status: {Not_done, In_Progress, Done} or Assignee: {Assignee_Name} or SortCriteria: {title, priority, size} {Max 3 Criteria}}}";
            case LISTTASKS:
                return "LISTTASKS {Filter_Titile: String} {Sort_Title: String} {Max 2 Criteria}";
            case LISTTASKSWITHASSIGNEE:
                return "LISTTASKSWITHASSIGNEE {Filter_Assignee: Assignee_Name/no} {Filter_Status: Status/no} {Sort_by_title: String/no}";
            case SHOWBUGSTEPSTOREPRODUCE:
                return "SHOWBUGSTEPSTOREPRODUCE {Bug_id: Integer}";
            default:
                throw new IllegalArgumentException(NO_SUCH_COMMAND);


        }
    }
}
