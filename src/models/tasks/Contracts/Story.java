package models.tasks.Contracts;

import models.contracts.Member;
import models.tasks.enums.Priority;
import models.tasks.enums.Size;
import models.tasks.enums.StatusStory;

public interface Story extends Task {
    Priority getPriority();

    void setPriority(Priority priority);


    Size getSize();

    void setSize(Size size);

    StatusStory getStatus();

    void setStatus(StatusStory status);

    Member getAssignee();
}
