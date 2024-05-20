package models.contracts;

import models.contracts.Member;
import models.tasks.Contracts.Task;
import models.tasks.enums.StatusBugStoryCombined;

public interface Assignable extends Task {
    Member getAssignee();
    void setAssignee(Member assignee);
    StatusBugStoryCombined getStatus();
}
