package models;

import models.tasks.EventLogImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventLogImplTests {


    @Test
    public void should_ThrowException_When_DescriptionIsEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new EventLogImpl(""));
    }


    @Test
    public void should_ThrowException_When_NoDescriptionIsPassed(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new EventLogImpl());
    }

    @Test
    public void should_CreateLog_When_ArgumentsAreValid(){
        Assertions.assertDoesNotThrow(()-> new EventLogImpl("Valid"));
    }
}
