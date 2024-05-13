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


    //Constructor
    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description);
        this.rating = rating;
        this.status = StatusFeedback.NEW;
        addLog(FEEDBACK_CONSTRUCTOR_LOG.formatted(title));
    }

    //Methods


    @Override
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        ValidationHelper.validateValueInRange(rating, 0, 10, RATING_ERR);
        this.rating = rating;
    }

    @Override
    public StatusFeedback getStatus() {
        return status;
    }

    public void setStatus(StatusFeedback status) {
        this.status = status;
    }


}
