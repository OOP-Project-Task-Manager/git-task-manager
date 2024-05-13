package Models.Tasks;

import Models.Tasks.Contracts.Bug;
import Models.Tasks.Contracts.Comment;
import Models.Tasks.enums.Priority;
import Models.Tasks.enums.Severity;
import Models.Tasks.enums.StatusBug;
import Models.Team;
import Models.contracts.Member;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl implements Bug {
    public static final String BUG_CONSTRUCTOR_LOG = "Bug %s created";

    //Constants

    //Attrbitutes
    private final List<String> stepsToReproduce;
    private Priority priority;
    private Severity severity;
    private StatusBug status;
    private Member assignee;

    //TODO /*Need to add the member class for assignee:*/
    //Constructor

    public BugImpl(int id, String title, String description, Priority priority, Severity severity, Member assignee) {

        super(id, title, description);
        this.priority = priority;
        this.severity = severity;
        this.status = StatusBug.ACTIVE;
        stepsToReproduce = new ArrayList<>();
        addLog(BUG_CONSTRUCTOR_LOG.formatted(title));

    }

    //Methods


    @Override
    public Priority getPriority() {
        return priority;
    }


    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    @Override
    public StatusBug getStatus() {
        return status;
    }

    public void setStatus(StatusBug status) {
        this.status = status;
    }

    @Override
    public List<String> getStepsToReproduce() {
        return new ArrayList<>(stepsToReproduce);
    }


    @Override
    public void addStepToReproduce(String step) {
        stepsToReproduce.add(step);
    }


    @Override
    public void removeStepToReproduce(String step) {
        stepsToReproduce.remove(step);
    }


    @Override
    public void clearStepsToReproduce() {
        stepsToReproduce.clear();
    }

    @Override
    public Member getAssignee() {
        return assignee;
    }

    public void setAssignee(Member assignee) {

        this.assignee = assignee;
    }
}
