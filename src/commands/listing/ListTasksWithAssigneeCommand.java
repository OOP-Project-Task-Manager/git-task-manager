package commands.listing;

import commands.BaseCommand;
import core.contracts.TaskRepository;
import models.contracts.Assignable;
import utilities.ValidationHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListTasksWithAssigneeCommand extends BaseCommand {
    public ListTasksWithAssigneeCommand(TaskRepository repository) {
        super(repository);
    }

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;



    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        List<Assignable> tasks = mergeAssignableLists();
        String paramAssignee = parameters.get(0);
        String paramStatus = parameters.get(1);
        String paramSort = parameters.get(2);

        List<Assignable> filteredTasks = filterTasksWithAssignee(tasks, paramAssignee, paramStatus);

        if (isSortCriteria(paramSort)) {
            filteredTasks = sortTasksByTitle(filteredTasks);
        }

        return formatTasks(filteredTasks);
    }

    //task.getAssignee() != null &&

    private List<Assignable> filterTasksWithAssignee(List<Assignable> tasks, String assignee, String status) {
        return tasks.stream()
                .filter(task -> "no".equalsIgnoreCase(assignee) || (task.getAssignee().getName().equalsIgnoreCase(assignee)))
                .filter(task -> "no".equalsIgnoreCase(status) || task.getStatus().toString().equalsIgnoreCase(status))
                .collect(Collectors.toList());

    }

    private List<Assignable> sortTasksByTitle(List<Assignable> tasks) {
        return tasks.stream()
                .sorted(Comparator.comparing(Assignable::getTitle))
                .collect(Collectors.toList());
    }

    private boolean isSortCriteria(String param) {
        return param != null && param.equalsIgnoreCase("sort");
    }



    private List<Assignable> mergeAssignableLists() {
        List<Assignable> bugsAndStories = new ArrayList<>();
        bugsAndStories.addAll(getRepository().getBugs());
        bugsAndStories.addAll(getRepository().getStories());
        return bugsAndStories;
    }

    private String formatTasks(List<Assignable> tasks) {
        StringBuilder result = new StringBuilder();
        for (Assignable task : tasks) {
            result.append("Task ID: ").append(task.getId())
                    .append(", Title: ").append(task.getTitle())
                    .append(", Description: ").append(task.getDescription())
                    .append(" Status: ").append(task.getStatus())
                    .append(" Assignee: ").append(task.getAssignee().getName())
                    .append("\n");
        }
        return result.toString();
    }
}
