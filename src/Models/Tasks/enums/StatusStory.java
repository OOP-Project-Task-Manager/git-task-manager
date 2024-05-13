package Models.Tasks.enums;

public enum StatusStory {

    NOT_DONE,
    IN_PROGRESS,
    DONE;

    @Override
    public String toString() {
        switch (this) {
            case NOT_DONE:
                return "Not Done";
            case IN_PROGRESS:
                return "In Progress";
            case DONE:
                return "Done";
            default:
                throw new IllegalArgumentException("Unknown status story type");
        }
    }

}
