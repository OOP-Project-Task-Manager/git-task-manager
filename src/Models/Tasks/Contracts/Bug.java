package Models.Tasks.Contracts;

import Models.Tasks.enums.Priority;
import Models.Tasks.enums.Severity;
import Models.Tasks.enums.StatusBug;

import java.util.List;

public interface Bug {
    Priority getPriority();

    Severity getSeverity();

    StatusBug getStatus();

    List<String> getStepsToReproduce();

    void addStepToReproduce(String step);

    void removeStepToReproduce(String step);

    void clearStepsToReproduce();
}
