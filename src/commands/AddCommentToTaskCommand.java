package commands;

import core.contracts.TaskRepository;
import models.contracts.Board;
import models.contracts.Member;
import models.tasks.Contracts.Comment;
import models.tasks.Contracts.Task;
import utilities.ParsingHelpers;
import utilities.ValidationHelper;

import java.util.List;

public class AddCommentToTaskCommand extends BaseCommand {

    public static final String COMMENT_CREATED = "Comment with author {%s} created and added to task {%s}";
    public static final int EXPECTED_ARGUMENTS_COUNT = 3;
    public static final String INTEGER_ERR = "Not an integer";

    public AddCommentToTaskCommand(TaskRepository repository) {
        super(repository);
    }


    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT);
        Member author = getRepository().findMemberByName(parameters.get(0));
        String message = parameters.get(1);
        String taskTitle = parameters.get(2);

        return createComment(author, message, taskTitle);

    }

    private String createComment(Member author, String message, String taskTitle) {
        Comment comment = getRepository().createComment(author, message);
        Task task = getRepository().findTaskByTitle(taskTitle);
        addCommentToTask(comment, task);
        return String.format(COMMENT_CREATED, comment.getAuthor().getName(), task.getTitle());
    }


    private void addCommentToTask(Comment comment, Task task) {
        task.addComment(comment);
    }

}
