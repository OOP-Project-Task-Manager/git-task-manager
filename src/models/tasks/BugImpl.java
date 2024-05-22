package models.tasks;

import models.tasks.enums.Priority;
import models.tasks.enums.Severity;
import models.contracts.Member;
import models.tasks.enums.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BugImpl extends TaskImpl implements models.tasks.Contracts.Bug {
    public static final String BUG_CONSTRUCTOR_LOG = "Bug %s created";
    public static final String NOT_A_VALID_STATUS = "Not a valid status for Bug";

    //Constants
    public static final Status[] BUG_POSSIGLBE_STATUS = {Status.ACTIVE, Status.DONE};

    //Attrbitutes
    private final List<String> stepsToReproduce;
    private Priority priority;
    private Severity severity;
    private Status status;
    private Member assignee;
    private boolean initializing = true;


    //Constructor

    public BugImpl(int id, String title, String description, Priority priority, Severity severity, Member assignee) {

        super(id, title, description);
        setPriority(priority);
        setSeverity(severity);
        this.status = Status.ACTIVE;
        stepsToReproduce = new ArrayList<>();
        initializing = false;
        this.assignee = assignee;
        addLog(BUG_CONSTRUCTOR_LOG.formatted(title));
    }

    //Methods


    @Override
    public Priority getPriority() {
        return priority;
    }


    public void setPriority(Priority priority) {
        if (!initializing) {
            addLog("Priority changed from %s Priority to %s Priority".formatted(this.priority, priority));

        }
        this.priority = priority;
    }

    @Override
    public Severity getSeverity() {
        return severity;
    }


    public void setSeverity(Severity severity) {
        if (!initializing) {
            addLog("Severity changed from %s to %s".formatted(this.severity, severity));

        }
        this.severity = severity;
    }

    @Override
    public Status getStatus() {
        return status;
    }


    public void setStatus(Status status) {
        if (Arrays.stream(BUG_POSSIGLBE_STATUS).noneMatch(s -> s == status)) {
            throw new IllegalArgumentException(NOT_A_VALID_STATUS);
        }
        if (!initializing) {
            addLog("Status changed from %s to %s".formatted(this.status, status));

        }
        this.status = status;
    }

    @Override
    public List<String> getStepsToReproduce() {
        return new ArrayList<>(stepsToReproduce);
    }


    @Override
    public void addStepToReproduce(String step) {
        addLog("""
                New step to reproduce added
                Step: %s
                """.formatted(step));
        stepsToReproduce.add(step);
    }


    @Override
    public void removeStepToReproduce(String step) {
        addLog("""
                New step to reproduce removed
                Step: %s
                """.formatted(step));
        stepsToReproduce.remove(step);
    }


    @Override
    public void clearStepsToReproduce() {
        addLog("""
                Steps to reduce cleared
                """);
        stepsToReproduce.clear();
    }

    @Override
    public Member getAssignee() {
        return assignee;
    }

    @Override
    public void setAssignee(Member assignee) {
        if (!initializing) {
            addLog("Assignee changed from %s to %s".formatted(this.assignee.getName(), assignee.getName()));
        }
        this.assignee = assignee;
    }
}
