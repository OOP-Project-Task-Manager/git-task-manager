package commands.listing;

import commands.BaseCommand;
import core.contracts.TaskRepository;
import models.tasks.Contracts.EventLog;
import models.tasks.Contracts.Story;
import models.tasks.enums.Status;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListStoriesCommand extends BaseCommand {
    public static final String INVALID_SORTING_CRITERIA = "Invalid sorting criteria";

    public ListStoriesCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    public String executeCommand(List<String> parameters) {
        List<Story> stories = getRepository().getStories();
        Status statusStory = null;
        String assigneeName = null;
        String sortCriteria = null;
        for (String param : parameters) {
            if (isStatus(param)) {
                statusStory = Status.valueOf(param.toUpperCase());
            } else if (isSortCriteria(param)) {
                sortCriteria = param.toLowerCase();
            } else {
                assigneeName = param;
            }
        }
        if (statusStory != null) {
            Status result = statusStory;
            stories = stories.stream()
                    .filter(story -> story.getStatus() == result)
                    .collect(Collectors.toList());
        }
        if (assigneeName != null) {
            String result = assigneeName;
            stories = stories.stream()
                    .filter(story -> story.getAssignee() != null && story.getAssignee().getName().equalsIgnoreCase(result))
                    .collect(Collectors.toList());
        }
        if (sortCriteria != null) {
            switch (sortCriteria) {
                case "title":
                    stories.sort(Comparator.comparing(Story::getTitle));
                    break;
                case "priority":
                    stories.sort(Comparator.comparing(Story::getPriority));
                    break;
                case "size":
                    stories.sort(Comparator.comparing(Story::getSize));
                    break;
            }
        }
        return formatStories(stories);
    }

    private boolean isStatus(String param) {
        try {
            Status.valueOf(param.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isSortCriteria(String param) {
        if (param == null) {
            return false;
        }
        return param.equalsIgnoreCase("title")
                || param.equalsIgnoreCase("priority")
                || param.equalsIgnoreCase("size");
    }
    private String formatStories(List<Story> stories){
        StringBuilder result = new StringBuilder();
        for (Story story : stories){
            result.append("Story ID: ").append(story.getId())
                    .append(", Title: ").append(story.getTitle())
                    .append(", Status: ").append(story.getStatus())
                    .append(", Assignee: ").append(story.getAssignee() != null ? story.getAssignee().getName() : "Unassigned")
                    .append("\n");

                    for (EventLog log : story.getLogs()){
                        result.append(log);
                    }
                    result.append("\n");
        }
        return result.toString();
    }
}
