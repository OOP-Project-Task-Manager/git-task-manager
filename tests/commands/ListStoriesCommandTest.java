package commands;

import commands.listing.ListStoriesCommand;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import models.MemberImpl;
import models.tasks.Contracts.EventLog;
import models.tasks.Contracts.Story;
import models.tasks.StoryImpl;
import models.tasks.enums.Priority;
import models.tasks.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListStoriesCommandTest {
    TaskRepository repository;
    ListStoriesCommand listStoriesCommand;
    @BeforeEach
    public void before(){
        repository = new TaskRepositoryImpl();
        listStoriesCommand = new ListStoriesCommand(repository);
        Story story1 = new StoryImpl(1,
                "AAAAAAAAAAAAAAA",
                "XXXXXXXXXXX",
                Priority.HIGH,
                Size.LARGE,
                new MemberImpl("SSSSSSSS"));
        Story story2 = new StoryImpl(2,
                "BBBBBBBBBBBBBB",
                "YYYYYYYYYYY",
                Priority.HIGH,
                Size.LARGE,
                new MemberImpl("EEEEEEEEEE"));
        Story story3 = new StoryImpl(3,
                "CCCCCCCCCCCCCCCCC",
                "QQQQQQQQQQQ",
                Priority.HIGH,
                Size.LARGE,
                new MemberImpl("PPPPPPPPPP"));
        Story story4 = new StoryImpl(4,
                "DDDDDDDDDDDDDDDD",
                "ZZZZZZZZZZZ",
                Priority.HIGH,
                Size.LARGE,
                new MemberImpl("KKKKKKKKKK"));
        repository.addStory(story1);
        repository.addStory(story2);
        repository.addStory(story3);
        repository.addStory(story4);
    }

    @Test
    public void executeCommand_Should_FilterByStatus(){
        List<String> params = new ArrayList<>();
        params.add("NOT_DONE");

        String result = listStoriesCommand.executeCommand(params);
        String expected = generateExpectedResultForAllStories();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void executeCommand_Should_SortByTitle() {
        List<String> parameters = new ArrayList<>();
        parameters.add("title");

        String result = listStoriesCommand.executeCommand(parameters);
        String expected = generateExpectedResultForAllStoriesSortedByTitle();

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void executeCommand_Should_SortBySize() {
        List<String> parameters = new ArrayList<>();
        parameters.add("size");

        String result = listStoriesCommand.executeCommand(parameters);
        String expected = generateExpectedResultForAllStoriesSortedBySize();

        Assertions.assertEquals(expected, result);
    }
    @Test
    public void executeCommand_Should_SortByPriority() {
        List<String> parameters = new ArrayList<>();
        parameters.add("priority");

        String result = listStoriesCommand.executeCommand(parameters);
        String expected = generateExpectedResultForAllStoriesSortedByPriority();

        Assertions.assertEquals(expected, result);
    }
    @Test
    public void executeCommand_Should_FilterByStatusAndAssignee() {
        List<String> parameters = new ArrayList<>();
        parameters.add("NOT_DONE");
        parameters.add("SSSSSSSS");

        String result = listStoriesCommand.executeCommand(parameters);
        String expected = generateExpectedResultForFilteredByStatusAndAssignee();

        Assertions.assertEquals(expected, result);
    }
    @Test
    public void executeCommand_Should_FilterByStatusAndSortByTitle() {
        List<String> parameters = new ArrayList<>();
        parameters.add("NOT_DONE");
        parameters.add("title");

        String result = listStoriesCommand.executeCommand(parameters);
        String expected = generateExpectedResultForFilteredByStatusAndSortedByTitle();

        Assertions.assertEquals(expected, result);
    }
    @Test
    public void executeCommand_Should_ReturnEmptyList_When_InvalidStatus() {
        List<String> params = new ArrayList<>();
        params.add("INVALID_STATUS");
        String result = listStoriesCommand.executeCommand(params);
        String expected = "";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void executeCommand_Should_ReturnEmptyList_When_InvalidAssignee() {
        List<String> params = new ArrayList<>();
        params.add("NonExistentAssignee");
        String result = listStoriesCommand.executeCommand(params);
        String expected = "";
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void executeCommand_Should_ReturnEmptyList_When_InvalidSortCriteria() {
        List<String> params = new ArrayList<>();
        params.add("INVALID_SORT_CRITERIA");
        String result = listStoriesCommand.executeCommand(params);
        String expected = "";
        Assertions.assertEquals(expected, result);
    }

    private String generateExpectedResultForAllStories() {
        return formatStories(List.of(
                repository.findStoryById(1),
                repository.findStoryById(2),
                repository.findStoryById(3),
                repository.findStoryById(4)
        ));
    }
    private String generateExpectedResultForAllStoriesSortedByTitle() {
            List<Story> stories = new ArrayList<>(List.of(
                    repository.findStoryById(1),
                    repository.findStoryById(2),
                    repository.findStoryById(3),
                    repository.findStoryById(4)
            ));

            stories.sort(Comparator.comparing(Story::getTitle));

            return formatStories(stories);
    }
    private String generateExpectedResultForAllStoriesSortedBySize() {
        List<Story> stories = new ArrayList<>(List.of(
                repository.findStoryById(1),
                repository.findStoryById(2),
                repository.findStoryById(3),
                repository.findStoryById(4)
        ));

        stories.sort(Comparator.comparing(Story::getSize));

        return formatStories(stories);
    }
    private String generateExpectedResultForAllStoriesSortedByPriority() {
        List<Story> stories = new ArrayList<>(List.of(
                repository.findStoryById(1),
                repository.findStoryById(2),
                repository.findStoryById(3),
                repository.findStoryById(4)
        ));

        stories.sort(Comparator.comparing(Story::getPriority));

        return formatStories(stories);
    }

    private String generateExpectedResultForFilteredByStatusAndAssignee() {
        return formatStories(new ArrayList<>(List.of(repository.findStoryById(1))));
    }
    private String generateExpectedResultForFilteredByStatusAndSortedByTitle() {
        List<Story> stories = new ArrayList<>(List.of(
                repository.findStoryById(1),
                repository.findStoryById(2),
                repository.findStoryById(3),
                repository.findStoryById(4)
        ));

        stories.sort(Comparator.comparing(Story::getTitle));

        return formatStories(stories);
    }
    private String formatStories(List<Story> stories) {
        StringBuilder result = new StringBuilder();
        for (Story story : stories) {
            result.append("Story ID: ").append(story.getId())
                    .append(", Title: ").append(story.getTitle())
                    .append(", Status: ").append(story.getStatus())
                    .append(", Assignee: ").append(story.getAssignee() != null ? story.getAssignee().getName() : "Unassigned")
                    .append("\n");

            for (EventLog log : story.getLogs()) {
                result.append(log);
            }
            result.append("\n");
        }
        return result.toString();
    }

}
