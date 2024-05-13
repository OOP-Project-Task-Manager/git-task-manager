package Models.Tasks.Contracts;

import Models.Tasks.enums.Priority;
import Models.Tasks.enums.Size;
import Models.Tasks.enums.StatusStory;

public interface Story {
    Priority getPriority();

    Size getSize();

    StatusStory getStatus();
}
