package models.tasks.Contracts;


import models.contracts.Assignable;
import models.tasks.enums.Priority;
import models.tasks.enums.Severity;
import models.tasks.enums.Status;

import java.util.List;

public interface Bug extends Task, Assignable {
    Priority getPriority();

    void setStatus(Status status);

    void setSeverity(Severity severity);

    void setPriority(Priority priority);

    Severity getSeverity();

    Status getStatus();

    List<String> getStepsToReproduce();

    void addStepToReproduce(String step);

    void removeStepToReproduce(String step);

    void clearStepsToReproduce();


}
