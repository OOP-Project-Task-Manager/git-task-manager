package commands;

import commands.listing.ListBugsCommand;
import commands.listing.ListFeedbackCommand;
import commands.listing.ListStoriesCommand;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import models.MemberImpl;
import models.tasks.Contracts.Feedback;
import models.tasks.Contracts.Story;
import models.tasks.FeedbackImpl;
import models.tasks.StoryImpl;
import models.tasks.enums.Priority;
import models.tasks.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListFeedbackCommandTest {

    private TaskRepository repository;
    private ListFeedbackCommand listFeedbackCommand;

    @BeforeEach
    public void before() {
        repository = new TaskRepositoryImpl();
        listFeedbackCommand = new ListFeedbackCommand(repository);
        Feedback feedback1 = new FeedbackImpl(1,
                "AAAAAAAAAAAAAAA",
                "XXXXXXXXXXX",
                4);
        Feedback feedback2 = new FeedbackImpl(2,
                "BBBBBBBBBBBBBB",
                "YYYYYYYYYYY", 3);
        Feedback feedback3 = new FeedbackImpl(3,
                "CCCCCCCCCCCCCCCCC",
                "QQQQQQQQQQQ", 2);
        Feedback feedback4 = new FeedbackImpl(4,
                "DDDDDDDDDDDDDDDD",
                "ZZZZZZZZZZZ", 1);
        repository.addFeedback(feedback1);
        repository.addFeedback(feedback2);
        repository.addFeedback(feedback3);
        repository.addFeedback(feedback4);
    }

    @Test
    public void executeCommand_Should_FilterByStatus() {
        List<String> params = new ArrayList<>();
        params.add("New");
        String result = listFeedbackCommand.execute(params);

        String expected = """
                Feedback ID: 1, Title: AAAAAAAAAAAAAAA, Status: New
                Feedback ID: 2, Title: BBBBBBBBBBBBBB, Status: New
                Feedback ID: 3, Title: CCCCCCCCCCCCCCCCC, Status: New
                Feedback ID: 4, Title: DDDDDDDDDDDDDDDD, Status: New
                """;
        Assertions.assertEquals(result, expected);
    }


    @Test
    public void executeCommand_Should_SortByTitle() {
        List<String> params = new ArrayList<>();
        params.add("title");
        String result = listFeedbackCommand.execute(params);
        String expected = """
                Feedback ID: 1, Title: AAAAAAAAAAAAAAA, Status: New
                Feedback ID: 2, Title: BBBBBBBBBBBBBB, Status: New
                Feedback ID: 3, Title: CCCCCCCCCCCCCCCCC, Status: New
                Feedback ID: 4, Title: DDDDDDDDDDDDDDDD, Status: New
                """;
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void executeCommand_Should_SortByRating() {
        List<String> params = new ArrayList<>();
        params.add("rating");
        String result = listFeedbackCommand.execute(params);
        String expected = """
                Feedback ID: 4, Title: DDDDDDDDDDDDDDDD, Status: New
                Feedback ID: 3, Title: CCCCCCCCCCCCCCCCC, Status: New
                Feedback ID: 2, Title: BBBBBBBBBBBBBB, Status: New
                Feedback ID: 1, Title: AAAAAAAAAAAAAAA, Status: New
                """;
        Assertions.assertEquals(expected, result);
    }






}
