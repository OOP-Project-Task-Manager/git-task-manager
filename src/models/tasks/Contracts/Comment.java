package models.tasks.Contracts;

import models.contracts.Member;

public interface Comment {
    Member getAuthor();

    String getMessage();
}
