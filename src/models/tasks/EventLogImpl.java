package models.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventLogImpl implements models.tasks.Contracts.EventLog {

    //Constants
    public static final String DESCRIPTION_EMPTY_ERR = "Description cannot be empty";

    //Attributes
    private final String description;
    private final LocalDateTime timestamp;


    //Constructor
    public EventLogImpl(String description) {

        if (description.isEmpty() || description.isBlank()) {
            throw new IllegalArgumentException(DESCRIPTION_EMPTY_ERR);
        }

        this.description = description;
        this.timestamp = LocalDateTime.now();

    }

    public EventLogImpl() {
        throw new IllegalArgumentException(DESCRIPTION_EMPTY_ERR);

    }


    //Methods
    @Override
    public String getDescription() {
        return description;
    }


    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm:ss a");
        String formattedTimestamp = timestamp.format(formatter);
        return """
                Timestamp:%s
                Description:%s
                """.formatted(formattedTimestamp, description);
    }
}
