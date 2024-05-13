package Models.Tasks;

import Models.Tasks.Contracts.EventLog;
import Models.Tasks.enums.Priority;
import Models.Tasks.enums.Size;
import Models.Tasks.enums.StatusStory;

import java.util.List;

public class StoryImpl extends TaskImpl implements Models.Tasks.Contracts.Story {
    public static final String STORY_CONSTRUCTOR_LOG = "Story %s created";


    //Constants


    //Attributes
    private Priority priority;
    private Size size;
    private StatusStory status;
    private boolean initializing = true;

    //TODO /*Need to add the member class for assignee:*/
    //TODO Need to add advance/revert methods and put logging logic there as well. Or to add logging loggic in the setters.


    //Constructor
    public StoryImpl(int id, String title, String description, Priority priority, Size size) {
        super(id, title, description);
        setPriority(priority);
        setSize(size);
        this.status = StatusStory.NOT_DONE;
        initializing = false;
        addLog(STORY_CONSTRUCTOR_LOG.formatted(title));

    }


    //Methods

    @Override
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        if(!initializing){
            addLog("Priority changed from %s to %s".formatted(this.priority,priority));

        }

        this.priority = priority;
    }

    @Override
    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        if(!initializing){
            addLog("Size changed from %s to %s".formatted(this.size,size));

        }
        this.size = size;
    }

    @Override
    public StatusStory getStatus() {
        return status;
    }

    public void setStatus(StatusStory status) {
        if(!initializing){
            addLog("Status changed from %s to %s".formatted(this.status,status));

        }
        this.status = status;
    }


}
