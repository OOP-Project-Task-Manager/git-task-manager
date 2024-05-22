package commands.add;

import commands.BaseCommand;
import core.contracts.TaskRepository;
import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Team;
import models.tasks.Contracts.Comment;
import models.tasks.Contracts.Task;
import utilities.ParsingHelpers;
import utilities.ValidationHelper;

import java.util.List;

public class AddCommentToTaskCommand extends BaseCommand {

    public static final String COMMENT_CREATED = "Comment with author {%s} created and added to task {%s}";
    public static final int EXPECTED_ARGUMENTS_COUNT = 3;
    public static final String INTEGER_ERR = "Not an integer";
    public static final String AUTHOR_NOT_PART_OF_TEAM = "Author not part of team";

    public AddCommentToTaskCommand(TaskRepository repository) {
        super(repository);
    }


    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT);
        Member author = getRepository().findMemberByName(parameters.get(0));
        String message = parameters.get(1);
        int id = ParsingHelpers.tryParseInteger(parameters.get(2), INTEGER_ERR);

        return createComment(author, message, id);

    }

    private String createComment(Member author, String message, int id) {
        Task task = getRepository().findTaskById(id);
        Board board = getRepository().findBoardByName(task.getBoard());
        Team team = getRepository().findTeamByName(board.getTeam());
        if (!team.getMembers().contains(author)) {
            throw new IllegalArgumentException(AUTHOR_NOT_PART_OF_TEAM);
        }


        Comment comment = getRepository().createComment(author, message);
        addCommentToTask(comment, task);
        return String.format(COMMENT_CREATED, comment.getAuthor().getName(), task.getTitle());
    }


    private void addCommentToTask(Comment comment, Task task) {
        task.addComment(comment);
    }

}
