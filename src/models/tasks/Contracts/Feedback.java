package models.tasks.Contracts;

import models.tasks.enums.StatusFeedback;

public interface Feedback extends Task {
    int getRating();

    void setRating(int rating);

    StatusFeedback getStatus();

    void setStatus(StatusFeedback status);

}
