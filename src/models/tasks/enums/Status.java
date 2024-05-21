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
                return "Not_Done";
            case IN_PROGRESS:
                return "In_Progress";
            case DONE:
                return "Done";
            default:
                throw new IllegalArgumentException("Unknown status story type");
        }
    }
}
