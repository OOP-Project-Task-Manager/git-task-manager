package Models.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventLogImpl implements Models.Tasks.Contracts.EventLog {

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
        String formattedTimestamp = timestamp.format(formatter);
        return """
                Timestamp:%s
                Description:%s
                """.formatted(timestamp, description);
    }
}
