package models.tasks;

import models.tasks.Contracts.Story;
import models.tasks.enums.Priority;
import models.tasks.enums.Size;
import models.tasks.enums.Status;
import models.contracts.Member;

import java.util.Arrays;

public class StoryImpl extends TaskImpl implements Story {
    public static final String STORY_CONSTRUCTOR_LOG = "Story %s created";
    public static final Status[] STORY_POSSIGLBE_STATUS = {Status.NOT_DONE, Status.IN_PROGRESS, Status.DONE};
    //make arrays of possible statuses for both story nad bug and adjust setters. use common enum for filtering


    //Constants


    //Attributes
    private Priority priority;
    private Size size;
    private Status status;
    private Member assignee;
    private boolean initializing = true;


    //Constructor
    public StoryImpl(int id, String title, String description, Priority priority, Size size, Member assignee) {
        super(id, title, description);
        setPriority(priority);
        setSize(size);
        this.status = Status.NOT_DONE;
        initializing = false;
        addLog(STORY_CONSTRUCTOR_LOG.formatted(title));
        this.assignee = assignee;
    }


    //Methods

    @Override
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        if (!initializing) {
            addLog("Priority of task with id:%s changed from %s Priority to %s Priority".formatted(this.getId(),this.priority, priority));

        }

        this.priority = priority;
    }

    @Override
    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        if (!initializing) {
            addLog("Size of task with id: %s changed from %s to %s".formatted(this.getId(),this.size, size));

        }
        this.size = size;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public Member getAssignee() {
        if (assignee == null) {
            throw new IllegalArgumentException("Not assigned to any member");
        }

        return assignee;
    }

    @Override
    public void setAssignee(Member assignee) {
        if (!initializing) {
            addLog("Assignee of task with id:%s changed from %s to %s".formatted(this.getId(),this.assignee.getName(), assignee.getName()));
        }
        this.assignee = assignee;
    }

    public void setStatus(Status status) {
        if (Arrays.stream(STORY_POSSIGLBE_STATUS).noneMatch(s -> s == status)) {
            throw new IllegalArgumentException("Not a valid status for story");
        }
        if (!initializing) {
            addLog("Status of task with id:%s changed from %s to %s".formatted(this.getId(),this.status, status));
        }
        this.status = status;
    }


}
