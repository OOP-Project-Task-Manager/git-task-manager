package commands;

import commands.contracts.Command;
import commands.create.CreateNewStoryInBoardCommand;
import core.TaskRepositoryImpl;
import core.contracts.TaskRepository;
import models.BoardImpl;
import models.MemberImpl;
import models.contracts.Board;
import models.contracts.Member;
import models.tasks.Contracts.Story;
import models.tasks.StoryImpl;
import models.tasks.enums.Priority;
import models.tasks.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateNewStoryInBoardCommandTest {
    private Command createNewStoryCommand;
    private TaskRepository repository;
    @BeforeEach
    public void before(){
        repository = new TaskRepositoryImpl();
        createNewStoryCommand = new CreateNewStoryInBoardCommand(repository);
    }
    @Test
    public void execute_Should_AddNewFeedbackToRepository_When_ValidParameters() {
        List<String> params = new ArrayList<>();
        Member member = new MemberImpl("SSSSSSSSSS");
        Story story = new StoryImpl(1,
                "SASASFSSSSS",
                "XXCXCXCXCXC",
                Priority.HIGH,
                Size.LARGE,
                member);
        Board board = new BoardImpl("board1234");
        repository.addBoard(board);
        repository.addMember(member);
        board.addTask(story);
        params.add(story.getTitle());
        params.add(story.getDescription());
        params.add(story.getPriority().toString());
        params.add(String.valueOf(story.getSize()));
        params.add(story.getAssignee().getName());
        params.add(board.getName());
        createNewStoryCommand.execute(params);
        Assertions.assertEquals(1, repository.getStories().size());
    }
    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        List<String> params = new ArrayList<>();
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createNewStoryCommand.execute(params));
    }
}
