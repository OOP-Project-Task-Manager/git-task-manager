package models.contracts;

import models.tasks.Contracts.Comment;

import java.util.List;

public interface Commentable {

    public void addComment(Comment comment);

    public void removeComment(Comment comment);

    public void clearComments();
    List<Comment> getComments();
}
