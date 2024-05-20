package commands.listing;

import commands.BaseCommand;
import core.contracts.TaskRepository;
import models.tasks.Contracts.Bug;
import models.tasks.enums.Status;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListBugsCommand extends BaseCommand {

    public static final String INVALID_SORTING_CRITERIA = "Invalid sorting criteria";

    public ListBugsCommand(TaskRepository repository){
        super(repository);
    }
    @Override
    protected String executeCommand(List<String> parameters) {
        List<Bug> bugs = getRepository().getBugs();
        Status status = null;
        String assigneeName = null;
        String sortCriteria = null;
        for (String param : parameters){
            if (isStatus(param)){
                status = Status.valueOf(param.toUpperCase());
            } else if (isSortCriteria(param)){
                sortCriteria = param.toLowerCase();
            } else{
                assigneeName = param;
            }
        }
        if (status != null){
            Status result = status;
            bugs = bugs.stream()
                    .filter(bug -> bug.getStatus() == result)
                    .collect(Collectors.toList());
        }
        if (assigneeName != null){
            String result = assigneeName;
            bugs = bugs.stream()
                    .filter(bug -> bug.getAssignee() != null && bug.getAssignee().getName().equalsIgnoreCase(result))
                    .collect(Collectors.toList());
        }
        if (sortCriteria != null){
            switch (sortCriteria){
                case "title":
                    bugs.sort(Comparator.comparing(Bug::getTitle));
                    break;
                case "priority":
                    bugs.sort(Comparator.comparing(Bug::getPriority));
                    break;
                case "severity":
                    bugs.sort(Comparator.comparing(Bug::getSeverity));
                    break;
                default:
                    throw new IllegalArgumentException(INVALID_SORTING_CRITERIA);
            }
        }

        return formatBugs(bugs);
    }

    private boolean isStatus(String param){
        try {
            Status.valueOf(param.toUpperCase());
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }

    private boolean isSortCriteria(String param){
        if (param == null){
            return false;
        }
        return param.equalsIgnoreCase("title")
                || param.equalsIgnoreCase("priority")
                || param.equalsIgnoreCase("severity");
    }

    private String formatBugs(List<Bug> bugs){
        StringBuilder result = new StringBuilder();
        for (Bug bug : bugs){
            result.append("Bug ID: ").append(bug.getId())
                    .append(", Title: ").append(bug.getTitle())
                    .append(", Status: ").append(bug.getStatus())
                    .append(", Assignee: ").append(bug.getAssignee() != null ? bug.getAssignee().getName() : "Unassigned")
                    .append("\n");
        }
        return result.toString();
    }
}
