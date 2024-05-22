package commands;

import commands.listing.ListStoriesCommand;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import models.MemberImpl;
import models.tasks.Contracts.Story;
import models.tasks.StoryImpl;
import models.tasks.enums.Priority;
import models.tasks.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        String expected = "Story ID: 1, Title: AAAAAAAAAAAAAAA, Status: Not_Done, Assignee: SSSSSSSS\n" +
                "Story ID: 2, Title: BBBBBBBBBBBBBB, Status: Not_Done, Assignee: EEEEEEEEEE\n" +
                "Story ID: 3, Title: CCCCCCCCCCCCCCCCC, Status: Not_Done, Assignee: PPPPPPPPPP\n" +
                "Story ID: 4, Title: DDDDDDDDDDDDDDDD, Status: Not_Done, Assignee: KKKKKKKKKK\n";
        Assertions.assertEquals(result, expected);

    }
    @Test
    public void executeCommand_Should_SortByTitle() {
        List<String> parameters = new ArrayList<>();
        parameters.add("title");
        String result = listStoriesCommand.executeCommand(parameters);
        String expected = "Story ID: 1, Title: AAAAAAAAAAAAAAA, Status: Not_Done, Assignee: SSSSSSSS\n" +
                "Story ID: 2, Title: BBBBBBBBBBBBBB, Status: Not_Done, Assignee: EEEEEEEEEE\n" +
                "Story ID: 3, Title: CCCCCCCCCCCCCCCCC, Status: Not_Done, Assignee: PPPPPPPPPP\n" +
                "Story ID: 4, Title: DDDDDDDDDDDDDDDD, Status: Not_Done, Assignee: KKKKKKKKKK\n";
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void executeCommand_Should_SortBySize() {

        List<String> parameters = new ArrayList<>();
        parameters.add("size");
        String result = listStoriesCommand.executeCommand(parameters);
        String expected = "Story ID: 1, Title: AAAAAAAAAAAAAAA, Status: Not_Done, Assignee: SSSSSSSS\n" +
                "Story ID: 2, Title: BBBBBBBBBBBBBB, Status: Not_Done, Assignee: EEEEEEEEEE\n" +
                "Story ID: 3, Title: CCCCCCCCCCCCCCCCC, Status: Not_Done, Assignee: PPPPPPPPPP\n" +
                "Story ID: 4, Title: DDDDDDDDDDDDDDDD, Status: Not_Done, Assignee: KKKKKKKKKK\n";
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void executeCommand_Should_SortByPriority() {

        List<String> parameters = new ArrayList<>();
        parameters.add("priority");
        String result = listStoriesCommand.executeCommand(parameters);
        String expected = "Story ID: 1, Title: AAAAAAAAAAAAAAA, Status: Not_Done, Assignee: SSSSSSSS\n" +
                "Story ID: 2, Title: BBBBBBBBBBBBBB, Status: Not_Done, Assignee: EEEEEEEEEE\n" +
                "Story ID: 3, Title: CCCCCCCCCCCCCCCCC, Status: Not_Done, Assignee: PPPPPPPPPP\n" +
                "Story ID: 4, Title: DDDDDDDDDDDDDDDD, Status: Not_Done, Assignee: KKKKKKKKKK\n";
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void executeCommand_Should_FilterByStatusAndAssignee() {
        List<String> parameters = new ArrayList<>();
        parameters.add("NOT_DONE");
        parameters.add("SSSSSSSS");
        String result = listStoriesCommand.executeCommand(parameters);
        String expected = "Story ID: 1, Title: AAAAAAAAAAAAAAA, Status: Not_Done, Assignee: SSSSSSSS\n";
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void executeCommand_Should_FilterByStatusAndSortByTitle() {
        List<String> parameters = new ArrayList<>();
        parameters.add("NOT_DONE");
        parameters.add("title");
        String result = listStoriesCommand.executeCommand(parameters);
        String expected = "Story ID: 1, Title: AAAAAAAAAAAAAAA, Status: Not_Done, Assignee: SSSSSSSS\n" +
                "Story ID: 2, Title: BBBBBBBBBBBBBB, Status: Not_Done, Assignee: EEEEEEEEEE\n"+
                "Story ID: 3, Title: CCCCCCCCCCCCCCCCC, Status: Not_Done, Assignee: PPPPPPPPPP\n"+
                "Story ID: 4, Title: DDDDDDDDDDDDDDDD, Status: Not_Done, Assignee: KKKKKKKKKK\n";
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
}
