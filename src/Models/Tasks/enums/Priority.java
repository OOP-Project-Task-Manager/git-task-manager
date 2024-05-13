package Models.Tasks.enums;

public enum Priority {

    HIGH,
    MEDIUM,
    LOW;

    @Override
    public String toString() {
        switch (this) {
            case HIGH:
                return "High Priority";
            case MEDIUM:
                return "Medium Priority";
            case LOW:
                return "Low Priority";
            default:
                throw new IllegalArgumentException("Unknown priority type");
        }
    }
}
