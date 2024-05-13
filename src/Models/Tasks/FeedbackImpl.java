package Models.Tasks;

import Models.Tasks.enums.StatusFeedback;
import utilities.ValidationHelper;

public class FeedbackImpl extends TaskImpl implements Models.Tasks.Contracts.Feedback {


    //Constants
    public static final String FEEDBACK_CONSTRUCTOR_LOG = "Feedback %s created";
    public static final String RATING_ERR = "Rating should be between 0 and 10";

    //Attributes
    private int rating;
    private StatusFeedback status;
    private boolean initializing = true;


    //Constructor
    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description);
        this.rating = rating;
        this.status = StatusFeedback.NEW;
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
    public StatusFeedback getStatus() {
        return status;
    }

    public void setStatus(StatusFeedback status) {
        if (!initializing) {
            addLog("Status changed from %s to %s".formatted(this.status, status));

        }

        this.status = status;
    }


}
