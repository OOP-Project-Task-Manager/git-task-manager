package Models.contracts;

import Models.Tasks.Contracts.Comment;

public interface Commentable {

    public void addComment(Comment comment);

    public void removeComment(Comment comment);

    public void clearComments();
}
