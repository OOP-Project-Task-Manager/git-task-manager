package models.contracts;

import models.tasks.Contracts.Task;

public interface Assignable extends Task,Statusable {
    Member getAssignee();
    void setAssignee(Member assignee);

}
