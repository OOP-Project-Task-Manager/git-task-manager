package models.tasks;

import models.contracts.Member;
import models.tasks.Contracts.Comment;
import utilities.ValidationHelper;

public class CommentImpl implements Comment {


    //Constants
    public static final String AUTHOR_EMPTY_ERR = "Author name must not be empty";
    public static final String MESSAGE_EMPTY_ERR = "Message cannot be empty";

    //Attributes
    private Member author;
    private String message;


    //Consturctor

    public CommentImpl(Member author, String message) {
        setAuthor(author);
        setMessage(message);
    }


    //Methods

    @Override
    public Member getAuthor() {
        return author;
    }

    public void setAuthor(Member author) {
//        ValidationHelper.validateStringNotEmpty(author, AUTHOR_EMPTY_ERR);
        this.author = author;

    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        ValidationHelper.validateStringNotEmpty(message, MESSAGE_EMPTY_ERR);
        this.message = message;

    }

    @Override
    public String toString() {
        return """
                Author: %s
                Message: %s
                """.formatted(author.getName(), message);
    }
}
