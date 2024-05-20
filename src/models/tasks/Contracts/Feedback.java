package models.tasks.Contracts;

import models.contracts.Statusable;

public interface Feedback extends Task, Statusable {
    int getRating();

    void setRating(int rating);



}
