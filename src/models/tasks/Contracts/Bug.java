package models.tasks.Contracts;


import models.tasks.enums.Priority;
import models.tasks.enums.Severity;
import models.tasks.enums.StatusBug;
import models.contracts.Member;

import java.util.List;

public interface Bug {
    Priority getPriority();

    Severity getSeverity();

    StatusBug getStatus();

    List<String> getStepsToReproduce();

    void addStepToReproduce(String step);

    void removeStepToReproduce(String step);

    void clearStepsToReproduce();

    Member getAssignee();
}
