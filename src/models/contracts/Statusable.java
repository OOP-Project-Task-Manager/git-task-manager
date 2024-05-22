package models.contracts;

import models.tasks.enums.Status;

public interface Statusable {
    Status getStatus();
    void setStatus(Status status);
}
