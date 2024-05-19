package commands.listing;

import commands.BaseCommand;
import core.contracts.TaskRepository;
import models.tasks.Contracts.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListTasksCommand extends BaseCommand {
    public ListTasksCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        List<Task> tasks = getRepository().getTasks();
        String param = null;

        if (!parameters.isEmpty()) {
            param = parameters.get(0);
        }

        if (param != null) {
            if (isSortCriteria(param)) {
                tasks.sort(Comparator.comparing(Task::getTitle));
            } else {
                tasks = filterTasks(tasks, param);
            }
        }

        // Format the filtered and/or sorted tasks for display
        return formatTasks(tasks);
    }

    private List<Task> filterTasks(List<Task> tasks, String filterValue) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(filterValue)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    private boolean isSortCriteria(String param) {
        return param != null && param.equalsIgnoreCase("sort");
    }

    private String formatTasks(List<Task> tasks) {
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            result.append("Task ID: ").append(task.getId())
                    .append(", Title: ").append(task.getTitle())
                    .append(", Description: ").append(task.getDescription())
                    .append("\n");
        }
        return result.toString();
    }
}
