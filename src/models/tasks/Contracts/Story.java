package models.tasks.Contracts;

import models.contracts.Assignable;
import models.tasks.enums.Priority;
import models.tasks.enums.Size;
import models.tasks.enums.StatusBugStoryCombined;
import models.tasks.enums.StatusStory;

public interface Story extends Task, Assignable {
    Priority getPriority();

    void setPriority(Priority priority);


    Size getSize();

    void setSize(Size size);

    StatusBugStoryCombined getStatus();

    void setStatus(StatusBugStoryCombined status);


}
