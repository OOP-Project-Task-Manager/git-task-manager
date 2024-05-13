package models.tasks.Contracts;

import models.tasks.enums.StatusFeedback;

public interface Feedback {
    int getRating();

    StatusFeedback getStatus();
}
