package models.tasks.enums;

public enum Status {
    ACTIVE,
    NOT_DONE,
    IN_PROGRESS,
    DONE,
    NEW,
    UNSCHEDULED,
    SCHEDULED;


    @Override
    public String toString() {
        switch (this) {
            case ACTIVE:
                return "Active";
            case NOT_DONE:
                return "Not Done";
            case IN_PROGRESS:
                return "In Progress";
            case DONE:
                return "Done";
            case NEW:
                return "New";
            case UNSCHEDULED:
                return "Unscheduled";
            case SCHEDULED:
                return "Scheduled";
            default:
                throw new IllegalArgumentException("Unknown status: " + this);
        }
    }
}
