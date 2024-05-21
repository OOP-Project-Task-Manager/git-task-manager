package models;

import models.tasks.BugImpl;
import models.tasks.Contracts.Bug;
import models.tasks.StoryImpl;
import models.tasks.enums.Priority;
import models.tasks.enums.Severity;
import models.tasks.enums.Size;
import models.tasks.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BugImplTest {

    public static final Status[] BUG_POSSIGLBE_STATUS = {Status.ACTIVE, Status.DONE};
    public static final String VALID_TITLE = "XXXXXXXXXX";
    public static final String VALID_DESCRIPTION = "CCCCCCCCCC";


    @Test
    public void bugImpl_Should_ImplementBugInterface() {
        BugImpl bug = new BugImpl(1, VALID_TITLE, VALID_DESCRIPTION, Priority.HIGH, Severity.MINOR, new MemberImpl("Johny"));
        Assertions.assertTrue(bug instanceof Bug);
    }





}
