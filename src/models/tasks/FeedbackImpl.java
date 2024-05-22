package models.tasks;

import models.tasks.enums.Status;
import utilities.ValidationHelper;

import java.util.Arrays;

public class FeedbackImpl extends TaskImpl implements models.tasks.Contracts.Feedback {


    //Constants
    public static final String FEEDBACK_CONSTRUCTOR_LOG = "Feedback %s created";
    public static final String RATING_ERR = "Rating should be between 0 and 10";
    public static final String NOT_A_VALID_STATUS = "Not a valid status for feedback";

    //Attributes
    private int rating;
    private Status status;
    private boolean initializing = true;
    public static final Status[] FEEDBACK_POSSIGLBE_STATUS = {Status.NEW, Status.UNSCHEDULED, Status.SCHEDULED, Status.DONE};


    //Constructor
    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description);
        setRating(rating);
        this.status = Status.NEW;
        initializing = false;
        addLog(FEEDBACK_CONSTRUCTOR_LOG.formatted(title));

    }

    //Methods


    @Override
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        ValidationHelper.validateValueInRange(rating, 0, 10, RATING_ERR);

        if (!initializing) {
            addLog("Rating changed from %d to %d".formatted(this.rating, rating));

        }
        this.rating = rating;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (Arrays.stream(FEEDBACK_POSSIGLBE_STATUS).noneMatch(s -> s == status)) {
            throw new IllegalArgumentException(NOT_A_VALID_STATUS);
        }
        if (!initializing) {
            addLog("Status changed from %s to %s".formatted(this.status, status));

        }

        this.status = status;
    }


}
