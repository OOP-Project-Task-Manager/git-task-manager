package models.tasks.Contracts;


import models.contracts.Assignable;
import models.tasks.enums.Priority;
import models.tasks.enums.Severity;
import models.tasks.enums.StatusBug;
import models.tasks.enums.StatusBugStoryCombined;

import java.util.List;

public interface Bug extends Task, Assignable {
    Priority getPriority();

    void setStatus(StatusBugStoryCombined status);

    void setSeverity(Severity severity);

    void setPriority(Priority priority);

    Severity getSeverity();

    StatusBugStoryCombined getStatus();

    List<String> getStepsToReproduce();

    void addStepToReproduce(String step);

    void removeStepToReproduce(String step);

    void clearStepsToReproduce();


}
