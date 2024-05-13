package Models.Tasks;

import Models.Tasks.Contracts.Comment;
import Models.Tasks.Contracts.EventLog;
import Models.Tasks.Contracts.Task;
import Models.contracts.Commentable;
import Models.contracts.Identifiable;
import Models.contracts.Loggable;
import Models.contracts.Printable;
import utilities.ValidationHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class TaskImpl implements Task, Printable, Commentable, Loggable, Identifiable {


    //Constants
    public static final int TITLE_MIN_LENGTH = 10;
    public static final int TITLE_MAX_LENGTH = 100;
    public static final String TITLE_LENGTH_ERR = "Title is a string between %d and %d symbols";

    public static final int DESCRIPTION_MIN_LENGTH = 10;
    public static final int DESCRIPTION_MAX_LENGTH = 500;
    public static final String DESCRIPTION_LENGTH_ERR = "Description is a string between %d and %d symbols";


    //Attributes
    private int id;
    private String title;
    private String description;
    private final List<Comment> comments;
    private final List<EventLog> activityHistory;

    //Constructor

    public TaskImpl(int id, String title, String description) {
        setTitle(title);
        setDescription(description);
        comments = new ArrayList<>();
        activityHistory = new ArrayList<>();
        this.id = id;

    }


    //Methods

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        ValidationHelper.validateStringLength(title, TITLE_MIN_LENGTH, TITLE_MAX_LENGTH,
                TITLE_LENGTH_ERR.formatted(TITLE_MIN_LENGTH, TITLE_MAX_LENGTH));
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        ValidationHelper.validateStringLength(description, DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH,
                DESCRIPTION_LENGTH_ERR.formatted(TITLE_MIN_LENGTH, TITLE_MAX_LENGTH));
        this.description = description;
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void removeComment(Comment comment) {
        //TODO validation that comment exists.
        comments.remove(comment);
    }

    @Override
    public void clearComments() {
        comments.clear();
    }

    @Override
    public void addLog(String description) {
        activityHistory.add(new EventLogImpl(description));
    }


    @Override
    public List<EventLog> getLogs() {
        return new ArrayList<>(activityHistory);
    }
    @Override
    public int getId(){
        return id;
    }


    @Override
    public String getAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Title: ").append(title).append("\n");
        stringBuilder.append("Description: ").append(description).append("\n");

        if (comments != null && !comments.isEmpty()) {
            stringBuilder.append("Comments: \n");
            for (Comment comment : comments) {
                stringBuilder.append("- ").append(comment.getAuthor()).append(": ").append(comment.getMessage()).append("\n");
            }
        }

        if (activityHistory != null && !activityHistory.isEmpty()) {
            stringBuilder.append("Activity History: \n");
            for (EventLog eventLog : activityHistory) {
                stringBuilder.append("- ").append(eventLog.getDescription()).append(" (").append(eventLog.getTimestamp()).append(")\n");
            }
        }

        return stringBuilder.toString();
    }
}
