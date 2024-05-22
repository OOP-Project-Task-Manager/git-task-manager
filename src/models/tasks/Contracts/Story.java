package models.tasks.Contracts;

import models.contracts.Assignable;
import models.tasks.enums.Priority;
import models.tasks.enums.Size;
import models.tasks.enums.Status;

public interface Story extends Task, Assignable {
    Priority getPriority();

    void setPriority(Priority priority);


    Size getSize();

    void setSize(Size size);

    Status getStatus();

    void setStatus(Status status);


}
