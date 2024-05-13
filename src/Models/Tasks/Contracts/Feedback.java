package Models.Tasks.Contracts;

import Models.Tasks.enums.StatusFeedback;

public interface Feedback {
    int getRating();

    StatusFeedback getStatus();
}
