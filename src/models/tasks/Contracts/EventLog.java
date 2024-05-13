package models.tasks.Contracts;

import java.time.LocalDateTime;

public interface EventLog {
    //Methods
    String getDescription();

    LocalDateTime getTimestamp();
}
