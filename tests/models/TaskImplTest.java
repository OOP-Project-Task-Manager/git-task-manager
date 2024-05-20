package models;

import models.tasks.CommentImpl;
import models.tasks.TaskImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CopyTask extends TaskImpl {
    public CopyTask(int id, String title, String description){
        super(id, title, description);
    }
}
public class TaskImplTest {
    @Test
    public void taskImpl_Should_ImplementTaskInterface() {

        CopyTask task = new CopyTask(1,
                "title11415152",
                "AAAAAAAAAAAAAAAA");

        Assertions.assertTrue(task instanceof TaskImpl);
    }
    @Test
    public void constructor_Should_ThrowException_When_TaskTitleLengthOutOfBounds() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new CopyTask(1,"SSS", "AAAAAAAA"));
    }
    @Test
    public void constructor_Should_ThrowException_When_TaskDescriptionLengthOutOfBounds() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new CopyTask(1,"SSSSSSSSSSS", "AA"));
    }
    @Test
    public void constructor_Should_CreateNewTask_When_ParametersAreCorrect() {
        CopyTask task = new CopyTask(1,
                "title1234412",
                "AAAAAAAAAAAAAAAA");

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, task.getId()),
                () ->Assertions.assertEquals("title1234412", task.getTitle()),
                () ->Assertions.assertEquals("AAAAAAAAAAAAAAAA", task.getDescription())
        );
    }
    @Test
    public void addComment_Should_AddCommentToList() {
        CopyTask task = new CopyTask(1,
                "title1223423523",
                "AAAAAAAAAAAAAAAA");
        CommentImpl comment1 = new CommentImpl(new MemberImpl("SSSSSS"), "Message111");
        task.addComment(comment1);
        Assertions.assertTrue(task.getComments().contains(comment1));
    }

    @Test
    public void removeComment_Should_RemoveCommentToList() {
        CopyTask task = new CopyTask(1,
                "title124325235",
                "AAAAAAAAAAAAAAAA");
        CommentImpl comment1 = new CommentImpl(new MemberImpl("SSSSSS"), "Message111");
        CommentImpl comment2 = new CommentImpl(new MemberImpl("SSSSSSFSFDA"), "Message2211");
        task.addComment(comment1);
        task.addComment(comment2);
        task.removeComment(comment1);
        Assertions.assertEquals(1 ,task.getComments().size());
    }
    @Test
    public void clearComment_Should_RemoveCommentToList() {
        CopyTask task = new CopyTask(1,
                "title1fdksdfnf2",
                "AAAAAAAAAAAAAAAA");
        CommentImpl comment1 = new CommentImpl(new MemberImpl("SSSSSS"), "Message111");
        CommentImpl comment2 = new CommentImpl(new MemberImpl("SSSSSSFSFDA"), "Message2211");
        task.addComment(comment1);
        task.addComment(comment2);
        task.clearComments();
        Assertions.assertEquals(0, task.getComments().size());
    }
    @Test
    public void toString_Should_ReturnFormattedString() {
        CopyTask task = new CopyTask(1,
                "title1fdksdfnf2",
                "AAAAAAAAAAAAAAAA");
        CommentImpl comment1 = new CommentImpl(new MemberImpl("SSSSSS"), "Message111");
        task.addComment(comment1);
        String expectedOutput = "Title: title1fdksdfnf2\nDescription: AAAAAAAAAAAAAAAA\nComments: \n- SSSSSS: Message111\n";
        Assertions.assertEquals(expectedOutput, task.toString());
    }
}
