package models.tasks.Contracts;

import models.tasks.enums.StatusFeedback;

public interface Feedback extends Task {
    int getRating();

    StatusFeedback getStatus();
}
