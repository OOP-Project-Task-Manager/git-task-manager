package models.tasks.Contracts;

import models.tasks.enums.Priority;
import models.tasks.enums.Size;
import models.tasks.enums.StatusStory;

public interface Story {
    Priority getPriority();

    Size getSize();

    StatusStory getStatus();
}
